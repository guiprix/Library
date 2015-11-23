package it.univaq.mwt.j2ee.library.business.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.RequestGrid;
import it.univaq.mwt.j2ee.library.business.ResponseGrid;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

@Service
public class JDBCTitleService implements TitleService{

	@Autowired
	private DataSource dataSource;// vedi in root-context.xml <jndi lookup...>
	
	
	@Override
	public ResponseGrid<Title> findAllTitlesPaginated(RequestGrid requestGrid)
			throws BusinessException {
	/*	
		int iTotalRecords = 100;
		 int iTotalDisplayRecords= 100;
		List<Title> rows = new ArrayList<Title>();
		rows.add(new Title());
		ResponseGrid<Title> responseGrid = new ResponseGrid<Title>(requestGrid.getsEcho(), iTotalRecords, iTotalDisplayRecords, rows);
		return responseGrid;
	*/
		//valori inventati per vedere se funziona
	
		if ("id".equals(requestGrid.getSortCol())) {
			requestGrid.setSortCol("t.title_id");
		} else {
			if ("titleKind.name".equals(requestGrid.getSortCol())) {
				requestGrid.setSortCol("tk.name");
			} else {
				requestGrid.setSortCol("t." + requestGrid.getSortCol());
			}
			
		} 
		String orderBy = (!"".equals(requestGrid.getSortCol()) && !"".equals(requestGrid.getSortDir())) ? "order by " + requestGrid.getSortCol() + " " + requestGrid.getSortDir() : "";
		String baseSearch = "select t.title_id, t.name, t.author, t.description, t.isbn, t.publication_year, t.editor, t.title_kind_id, tk.name as Title_kind_name " +
			 	"from titles t, title_kinds tk " +
			 	"where t.title_kind_id=tk.title_kind_id " + 
			 	((!"".equals(requestGrid.getsSearch())) ? " and t.name like '" + ConversionUtility.addPercentSuffix(requestGrid.getsSearch()) + "'":"");
		
		String sql = "select * from (" +
					 "select rownum as rn, title_id, name, author, description, isbn, publication_year, editor, title_kind_id, Title_kind_name from (" +
					 	baseSearch +
					 	orderBy + 
					 ")" +
					 ")" + 
					 "where rn >= " + (requestGrid.getiDisplayStart() + 1)+ 
					 " and rownum<=" + requestGrid.getiDisplayLength();
		String countSql = "select count(*) from (" + baseSearch + ")";
		long records = 0;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<Title> titles = new ArrayList<Title>();
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			//COUNT ELEMENTS
			rs = st.executeQuery(countSql);
			if (rs.next()) {
				records = rs.getLong(1);
			}
			//EXECUTE SQL LIMITED
			rs = st.executeQuery(sql);
			while (rs.next()) {
				Long id = rs.getLong("title_id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				int publicationYear = rs.getInt("publication_year");
				String editor = rs.getString("editor");
				Long titleKindId = rs.getLong("title_kind_id");
				String titleKindName = rs.getString("Title_kind_name");
				TitleKind titleKind = new TitleKind();
				titleKind.setId(titleKindId);
				titleKind.setName(titleKindName);
				titles.add(new Title(id, name, author, description, isbn, publicationYear, editor, titleKind));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}

		}
		return new ResponseGrid(requestGrid.getsEcho(), records, records, titles);


	}


	@Override
	public List<TitleKind> findAllTitleKinds() throws BusinessException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		List<TitleKind> result = new ArrayList<TitleKind>();
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			rs = st.executeQuery("select * from title_kinds order by name");
			while (rs.next()) {
				Long titleKindId = rs.getLong("title_kind_id");
				String titleKindName = rs.getString("name");
				TitleKind titleKind = new TitleKind();
				titleKind.setId(titleKindId);
				titleKind.setName(titleKindName);
				result.add(titleKind);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}

		}
		return result;
	}


	@Override
	//questo metodo come gli altri sopra  viene chiamato da il controller =>interfaccia titleService => jdbcTitleService e qui faccio la query
	public void create(Title title) throws BusinessException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement("INSERT INTO titles (title_id, name, title_kind_id, description, author, isbn, publication_year, editor) VALUES (TITLES_SEQ.NEXTVAL,?,?,?,?,?,?,?)");
			st.setString(1, title.getName());
			st.setLong(2, title.getTitleKind().getId());
			st.setString(3, title.getDescription());
			st.setString(4, title.getAuthor());
			st.setString(5, title.getIsbn());
			st.setInt(6, title.getPublicationYear());
			st.setString(7, title.getEditor());			
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}

		}
		
	}


	@Override
	public Title findTitleByPk(Long id) throws BusinessException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		Title result = null;
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement("select t.*, tk.name as Title_kind_name from titles t, title_kinds tk where t.title_kind_id=tk.title_kind_id and t.title_id = ?");
			st.setLong(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				String name = rs.getString("name");
				String author = rs.getString("author");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				Long titleKindId = rs.getLong("title_kind_id");
				int publicationYear = rs.getInt("publication_year");
				String editor = rs.getString("editor");
				String titleKindName = rs.getString("Title_kind_name");
				TitleKind titleKind = new TitleKind();
				titleKind.setId(titleKindId);
				titleKind.setName(titleKindName);
				result = new Title(id, name, author, description, isbn, publicationYear, editor,  titleKind);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}

		}
		return result;
	}


	@Override
	public void update(Title title) throws BusinessException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement("update titles set name=?, author=?, description=?, isbn=?, title_kind_id=?, publication_year=?, editor=? where title_id=?");
			st.setString(1, title.getName());
			st.setString(2, title.getAuthor());
			st.setString(3, title.getDescription());
			st.setString(4, title.getIsbn());
			st.setLong(5, title.getTitleKind().getId());
			st.setInt(6, title.getPublicationYear());
			st.setString(7, title.getEditor());
			st.setLong(8, title.getId());
			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new BusinessException(e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
				}
			}

		}
		
	}


	@Override
	public void delete(Title title) throws BusinessException {
	
			Connection con = null;
			PreparedStatement st = null;
			try {
				con = dataSource.getConnection();
				st = con.prepareStatement("delete from titles where title_id=?");
				st.setLong(1, title.getId());
				st.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BusinessException(e);
			} finally {
				if (st != null) {
					try {
						st.close();
					} catch (SQLException e) {
					}
				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
					}
				}

			}
		}
		
	}
