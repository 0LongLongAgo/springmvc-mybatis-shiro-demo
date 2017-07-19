package com.lq.shiro;

import com.lq.utils.LogUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    //集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");

    }


    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager,String hashAlgorithm,int hashIterations,boolean hashSalted,boolean storedCredentialsHexEncoded) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
        setHashAlgorithmName(hashAlgorithm);
        setHashIterations(hashIterations);
        setHashSalted(hashSalted);
        setStoredCredentialsHexEncoded(storedCredentialsHexEncoded);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String)token.getPrincipal();
        //retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if(null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        LogUtils.log("尝试次数:"+retryCount.incrementAndGet());
        if(retryCount.incrementAndGet() > 5) {
            LogUtils.log("username: " + username + " tried to login more than 5 times in period");
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period"
            ); }
        boolean matches = super.doCredentialsMatch(token, info);
        if(matches) {
            //clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }

    public Cache<String, AtomicInteger> getPasswordRetryCache() {
        return passwordRetryCache;
    }

    public void setPasswordRetryCache(Cache<String, AtomicInteger> passwordRetryCache) {
        this.passwordRetryCache = passwordRetryCache;
    }
}
