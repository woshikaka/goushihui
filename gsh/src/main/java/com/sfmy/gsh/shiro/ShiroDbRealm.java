package com.sfmy.gsh.shiro;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.sfmy.gsh.bean.ShiroUser;
import com.sfmy.gsh.entity.User;
import com.sfmy.gsh.service.UserService;

public class ShiroDbRealm extends AuthorizingRealm {

	@Resource(name = "userService")
	protected UserService userService;
	
	/**
	 * 认证回调函数,登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String name = token.getUsername();
		User user = userService.findUserByName(name);
		if (user != null) {
			ShiroUser shiroUser = new ShiroUser(user.getId(), user.getName());
			return new SimpleAuthenticationInfo(shiroUser,user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		String username = shiroUser.getName();
		
		//授权
		if ("admin".equals(username)) {
			info.addRole("admin");
		}
		return info;
	}

	/**
	 * 指定凭证匹配规则
	 */
	public void initCredentialsMatcher() {
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Sha256Hash.ALGORITHM_NAME);
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
		matcher.setHashIterations(1024);
		setCredentialsMatcher(matcher);
	}
}
