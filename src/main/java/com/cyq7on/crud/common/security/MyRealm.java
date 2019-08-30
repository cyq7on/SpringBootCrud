package com.cyq7on.crud.common.security;

import com.cyq7on.crud.common.constant.UserType;
import com.cyq7on.crud.common.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


@Component
public class MyRealm extends AuthorizingRealm {


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UserToken;
    }

    /**
     * 用户鉴权，只有当需要检测用户权限的时候才会调用此方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String)principals.getPrimaryPrincipal();
        Integer userType = JWTUtil.getUserType(token);
        if(UserType.Admin.getType() == userType) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("admin");
            return simpleAuthorizationInfo;
        }
        return null;
    }

    /**
     * 用户验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }

}
