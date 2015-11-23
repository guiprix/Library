package it.univaq.mwt.j2ee.library1.business.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import it.univaq.mwt.j2ee.library1.business.BusinessException;
import it.univaq.mwt.j2ee.library1.business.TitleService;
import it.univaq.mwt.j2ee.library1.business.model.Title;

public class JDBCTitleService {

	public void createTitle(Title title) throws BusinessException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			
			
			connection = DriverManager.getConnection
					("jdbc:oracle:thin:@192.168.56.101:1521:XE", "mwt_guido","aleinad");
		String sql = "INSERT INTO titles (title_id, name, title_kind_id, author, description, isbn, publication_year, editor)"
				+ "VALUES (TITLES_SEQ.NEXTVAL,?,?,?,?,?,?,?) ";
		
			
	
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, title.getName());
			preparedStatement.setLong(2, title.getTitleKind().getId());
			
			preparedStatement.setString(4, title.getAuthor());
			preparedStatement.setString(3, title.getDescription());
			preparedStatement.setString(5, title.getIsbn());
			preparedStatement.setInt(6, title.getPublicationYear());
			preparedStatement.setString(7, title.getEditor());
			preparedStatement.executeUpdate();

			
			
		/*	
			
			preparedStatement.setString(1,title.getName());
			preparedStatement.setLong(2, title.getTitleKind().getId());
			preparedStatement.setString(3, title.getAuthor());
			preparedStatement.setString(4, title.getDescription());
			preparedStatement.setString(5, title.getIsbn());
			preparedStatement.setInt(6, title.getPublicationYear());
			preparedStatement.setString(7, title.getEditor());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			/*	
			Statement statement = connection.createStatement();
			String sql = "INSERT INTO titles (title_id, name, title_kind_id, author, description, isbn, publication_year, editor)"
						+ "VALUES (TITLES_SEQ.NEXTVAL,  "
					+"'"+ title.getName()+"'"+ " ,"
					+ "1" + "'"+
					 "'" +  title.getAuthor()+"'"+" ,"
					+"'" + title.getDescription()+"'"+" ,"
					+"'"+ title.getIsbn()+"'"
					+"'" + title.getEditor()+"'"+" )";

		
		statement.executeUpdate(sql);
		statement.close();
	connection.close();
	*/
			
			
		
		
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 throw new BusinessException();
		 
			
		} finally {
			if (preparedStatement != null){
			
				try {preparedStatement.close();
				
				} catch (SQLException e){
				
				}
				if(connection != null){
					
				try { connection.close();
				} catch (SQLException e){
				}
				
				
				
				
			
		}
		
		
	}

	
		}
	}
}
	

