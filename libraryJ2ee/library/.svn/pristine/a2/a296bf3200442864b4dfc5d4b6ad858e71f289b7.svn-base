package it.univaq.mwt.j2ee.library.business.impl;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.RequestGrid;
import it.univaq.mwt.j2ee.library.business.ResponseGrid;
import it.univaq.mwt.j2ee.library.business.TitleService;
import it.univaq.mwt.j2ee.library.business.model.Title;
import it.univaq.mwt.j2ee.library.business.model.TitleKind;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class JDBCTitleService implements TitleService {

	private DataSource dataSource;

	public JDBCTitleService(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	public void createTitle(Title title) throws BusinessException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dataSource.getConnection();
			
			String sql = "INSERT INTO titles (title_id, name, title_kind_id, description, author, isbn, publication_year, editor) "
					+ "VALUES (TITLES_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title.getName());
			preparedStatement.setLong(2, title.getTitleKind().getId());
			preparedStatement.setString(3, title.getDescription());
			preparedStatement.setString(4, title.getAuthor());
			preparedStatement.setString(5, title.getIsbn());
			preparedStatement.setInt(6, title.getPublicationYear());
			preparedStatement.setString(7, title.getEditor());
			preparedStatement.executeUpdate();

			/*
			 * Statement statement = connection.createStatement(); String sql =
			 * "INSERT INTO titles (title_id, name, title_kind_id, description, author, isbn) "
			 * + "VALUES (TITLES_SEQ.NEXTVAL," + "'" + title.getName() + "'"+
			 * "," + "1" + "," + "'"+ title.getDescription() + "'"+ "," + "'"+
			 * title.getAuthor() + "'"+ "," + "'"+ title.getIsbn() + "'" + ")";
			 * statement.executeUpdate(sql); statement.close();
			 */

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}

	}

	@Override
	public List<Title> findAllTitles() throws BusinessException {
		List<Title> result = new ArrayList<Title>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();
			String sql = "select t.*, tk.name as title_kind_name from titles t, title_kinds tk where t.title_kind_id=tk.title_kind_id order by t.name";
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("title_id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String description = rs.getString("description");
				String isbn = rs.getString("isbn");
				int publicationYear = rs.getInt("publication_Year");
				String editor = rs.getString("editor");
				Long titleKindId = rs.getLong("title_kind_id");
				String titleKindName = rs.getString("title_kind_name");
				TitleKind titleKind = new TitleKind(titleKindId, titleKindName, null);

				Title title = new Title(id, name, author, description, isbn, publicationYear, editor, titleKind);
				result.add(title);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public List<TitleKind> findAllTitleKinds() throws BusinessException {
		List<TitleKind> result = new ArrayList<TitleKind>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			connection = dataSource.getConnection();			String sql = "select *from title_kinds order by name";
			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong("title_kind_id");
				String name = rs.getString("name");
				//String description = rs.getString("description");
				TitleKind titleKind = new TitleKind(id, name, "");
				result.add(titleKind);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new BusinessException();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
				}
			}
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
				}
			}
		}
		return result;
	}

	@Override
	public Title findTitleByPK(Long id) throws BusinessException {
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
				TitleKind titleKind = new TitleKind(titleKindId,titleKindName, null);
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
	public void updateTitle(Title title) throws BusinessException {
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
	public ResponseGrid findAllTitlesPaginated(RequestGrid requestGrid) throws BusinessException {
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
	public void deleteTitle(Title title) throws BusinessException {
		Connection con = null;
		PreparedStatement st = null;
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement("delete from titles where title_id= ?");
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