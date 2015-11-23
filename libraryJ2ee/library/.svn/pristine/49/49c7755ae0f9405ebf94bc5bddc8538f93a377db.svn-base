package it.univaq.mwt.j2ee.library.common.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import it.univaq.mwt.j2ee.library.business.BusinessException;
import it.univaq.mwt.j2ee.library.business.LibraryBusinessFactory;
import it.univaq.mwt.j2ee.library.business.SecurityService;
import it.univaq.mwt.j2ee.library.business.model.Role;
import it.univaq.mwt.j2ee.library.business.model.User;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class MyAuthorizingRealm extends AuthorizingRealm {

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		SecurityService service = LibraryBusinessFactory.getInstance().getSecurityService();

		User user = null;
		try {
			user = service.authenticate(upToken.getUsername());
		} catch (BusinessException idEx) {
			throw new AuthenticationException(idEx);
		} 

		if (user == null) {
			throw new AuthenticationException("Login name [" + upToken.getUsername() + "] not found!");
		}

		return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String>			roles			= new HashSet<String>();
		Set<Permission>		permissions		= new HashSet<Permission>();
		Collection<User>	principalsList	= principals.byType(User.class);
		
		if (principalsList.isEmpty()) {
			throw new AuthorizationException("Empty principals list!");
		}
		SecurityService service = LibraryBusinessFactory.getInstance().getSecurityService();
		//LOADING STUFF FOR PRINCIPAL 
		for (User userPrincipal : principalsList) {
			try {
				
				User user = service.authenticate(userPrincipal.getUsername());
				
				Set<Role> userRoles	= user.getRoles();
				for (Role r : userRoles) {
					roles.add(r.getName());
				}
			} catch (BusinessException idEx) { //userManger exceptions
				throw new AuthorizationException(idEx);
			} 
		}
		//THIS IS THE MAIN CODE YOU NEED TO DO !!!!
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		info.setRoles(roles); //fill in roles 
		info.setObjectPermissions(permissions); //add permissions (MUST IMPLEMENT SHIRO PERMISSION INTERFACE)
		
		return info;
	}

}