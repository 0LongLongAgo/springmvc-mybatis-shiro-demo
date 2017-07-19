package com.lq.shiro;
import java.util.Set;

import com.lq.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import com.lq.dao.UserDao;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    @Override
    public String getName() {
        return "UserRealm";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }


    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher(credentialsMatcher);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        Set<String> roles = userDao.findRoles(username);
        authorizationInfo.setRoles(roles);

        Set<String> permissions = userDao.findPermissions(username);
        authorizationInfo.setStringPermissions(permissions);

        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

//        UsernamePasswordToken login_token = (UsernamePasswordToken) token;
//
//        //校验码判断逻辑
//        //取得用户输入的校验码
//        String userInputValidCode = login_token.getValidCode();
//
//        //取得真实的正确校验码
//        String realRightValidCode = (String) SecurityUtils.getSubject().getSession().getAttribute("rand");
//
//        if (null == userInputValidCode || !userInputValidCode.equalsIgnoreCase(realRightValidCode)) {
//            throw new ValidCodeException("验证码输入不正确");
//        }

        //以上校验码验证通过以后,查数据库
        String username = (String) token.getPrincipal();
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }

        if (Boolean.TRUE.equals(user.getLocked())) {
            throw new LockedAccountException(); //帐号锁定
        }

        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getUsername(), //用户名
                user.getPassword(), //密码
                ByteSource.Util.bytes(user.getCredentialsSalt()),//盐
                getName()  //realm name
        );
        return authenticationInfo;
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}