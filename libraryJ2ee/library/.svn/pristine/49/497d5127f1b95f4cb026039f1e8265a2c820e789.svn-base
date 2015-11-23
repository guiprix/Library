package it.univaq.mwt.j2ee.library.business.impl;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.SecurityService;
import it.univaq.mwt.j2ee.library.business.model.Role;
import it.univaq.mwt.j2ee.library.business.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

public class JDBCSecurityService implements SecurityService {
	
	private DataSource dataSource;
	
	public JDBCSecurityService(DataSource dataSource) {
		super();
		this.dataSource = dataSource; 
	}

	@Override
	public User authenticate(String u) throws BusinessException {
		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		User result = null;
		try {
			con = dataSource.getConnection();
			st = con.prepareStatement("select * from users u where u.username = ?");
			st.setString(1, u);
			rs = st.executeQuery();
			if (rs.next()) {
				String username = rs.getString("USERNAME");
				String password = rs.getString("PASSWORD");
				String firstName = rs.getString("FIRST_NAME");
				String lastName = rs.getString("LAST_NAME");
				String address = rs.getString("ADDRESS");
				String city = rs.getString("CITY");
				String state = rs.getString("STATE");
				String zipCode = rs.getString("ZIPCODE");
				String phone = rs.getString("PHONE");
				Set<Role> roles = findRoles(u, con);
				result = new User(username, password, firstName, lastName, address, city, state, zipCode, phone, roles);
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



	private Set<Role> findRoles(String u, Connection con) throws BusinessException {
		PreparedStatement st = null;
		ResultSet rs = null;
		Set<Role> result = new HashSet<Role>();
		try {
			st = con.prepareStatement("select r.* from user_roles ur, roles r where ur.username = ? and ur.role_name = r.role_name");
			st.setString(1, u);
			rs = st.executeQuery();
			while (rs.next()) {
				String roleName = rs.getString("ROLE_NAME");
				String description = rs.getString("DESCRIPTION");
				result.add(new Role(roleName, description));
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
		}
		return result;
	}

	

}
