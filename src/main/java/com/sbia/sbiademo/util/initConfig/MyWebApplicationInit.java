package com.sbia.sbiademo.util.initConfig;

import com.sbia.sbiademo.util.shiro.MyRealm;
import com.sbia.sbiademo.util.shiro.MyRolePermissionResolver;
import com.sbia.sbiademo.util.shiro.MySessionListener;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.*;

/**
 * 自定义的web容器初始化;shiro相关配置
 */
@Configuration
public class MyWebApplicationInit {
    /*
     * 通过spring容器来管理filter的生命周期
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        DelegatingFilterProxy delegatingFilterProxy=new DelegatingFilterProxy("shiroFilter");
        delegatingFilterProxy.setTargetFilterLifecycle(true);
        filterRegistration.setFilter(delegatingFilterProxy);
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*");
        return filterRegistration;
    }
    //shiroFilter;ShiroFilter是整个Shiro的入口点，用于拦截需要安全控制的请求进行处理
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        //设定未登录跳转页面
        bean.setLoginUrl("/sbiademo/login");
        //设定未授权跳转页面
        bean.setUnauthorizedUrl("/sbiademo/unauthor");
        //设置自定义过滤器
        Map<String, Filter> filters = new HashMap<>();
        //filters.put("anon", new AnonymousFilter());
        //filters.put("authc", new FormAuthenticationFilter());
        //new PermissionsAuthorizationFilter();
        bean.setFilters(filters);
        //ini文件中url部分
        Map<String, String> chains = new HashMap<>();
        //chains.put("/sbiademo/getPermissionPage", "perms[1]");
        bean.setFilterChainDefinitionMap(chains);

        //改变springshirofilter的filterChainResolver，上边全没用了- -
        try {
            ((AbstractShiroFilter)bean.getObject()).setFilterChainResolver(filterChainResolver());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }
    /*
     * 授权器
     */
    /*
     *  @Bean
     *  public ModularRealmAuthorizer modularRealmAuthorizer() {
     *      ModularRealmAuthorizer modularRealmAuthorizer=new ModularRealmAuthorizer();
     *      modularRealmAuthorizer.setRolePermissionResolver(rolePermissionResolver());
     *      return modularRealmAuthorizer;
     *  }
     */
    /*
     * 自定义角色权限解析器
     */
    /*
     * @Bean
     *  public RolePermissionResolver rolePermissionResolver() {
     *      return new MyRolePermissionResolver();
     *  }
     */
    /**
     * 凭证匹配器
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }
    /*
     * shiro自定义域
     */
    @Bean
    @DependsOn(value="lifecycleBeanPostProcessor")
    public MyRealm myShiroRealm() {
        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myRealm;
    }
    /*
     * 会话管理
     */
    @Bean
    public MySessionListener mySessionListener() {
        return new MySessionListener();
    }
    @Bean
    public SimpleCookie sessionIdCookie() {
        SimpleCookie simpleCookie=new SimpleCookie();
        //设置Cookie名字，默认为JSESSIONID
        simpleCookie.setName("sid");
        //设置Cookie的域名，默认空，即当前访问的域名
        //simpleCookie.setDomain("localhost");
        //设置Cookie的过期时间，秒为单位，默认-1表示关闭浏览器时过期Cookie
        simpleCookie.setMaxAge(1800);
        return simpleCookie;
    }
    /*
     * 会话持久化
     */
    @Bean
    public SessionDAO mySessionDAO(){
        return new EnterpriseCacheSessionDAO();
    }
    /*
     * 会话定期验证,会话验证调度器
     */
    @Bean
    public ExecutorServiceSessionValidationScheduler sessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler
                executorServiceSessionValidationScheduler=new ExecutorServiceSessionValidationScheduler();
        //设置调度时间间隔，单位毫秒，默认就是1小时
        //executorServiceSessionValidationScheduler.setInterval(1000);
        //设置会话验证调度器进行会话验证时的会话管理器
        executorServiceSessionValidationScheduler.setSessionManager((ValidatingSessionManager) sessionManager());
        return executorServiceSessionValidationScheduler;
    }
    @Bean
    public SessionManager sessionManager() {
        SessionManager sessionManager=new DefaultWebSessionManager();
        //设置会话的全局过期时间（毫秒为单位），默认30分钟
        ((DefaultWebSessionManager) sessionManager).setGlobalSessionTimeout(1800000);
        List<SessionListener> sessionListeners=new ArrayList<>();
        sessionListeners.add(mySessionListener());
        ((DefaultSessionManager) sessionManager).setSessionListeners(sessionListeners);
        //是否启用/禁用Session Id Cookie，默认是启用的
        ((DefaultWebSessionManager) sessionManager).setSessionIdCookieEnabled(true);
        ((DefaultWebSessionManager) sessionManager).setSessionIdCookie(sessionIdCookie());
        //设置会话持久化
        ((DefaultWebSessionManager) sessionManager).setSessionDAO(mySessionDAO());
        return sessionManager;
    }
    /*
     * shiro安全管理
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        //securityManager.setCacheManager(cacheManager());
        return securityManager;
    }
    //shiro
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    //返回一个新的DefaultFilterChainManager，同时将其设置给FilterChainResolver
    @Bean
    public DefaultFilterChainManager defaultFilterChainManager() {
        DefaultFilterChainManager defaultFilterChainManager=new DefaultFilterChainManager();
        PermissionsAuthorizationFilter permissionsAuthorizationFilter=new PermissionsAuthorizationFilter();
        permissionsAuthorizationFilter.setLoginUrl("/sbiademo/login");
        permissionsAuthorizationFilter.setUnauthorizedUrl("/sbiademo/unauthor");
        defaultFilterChainManager.addFilter("perms",permissionsAuthorizationFilter);
        return defaultFilterChainManager;
    }
    @Bean
    public FilterChainResolver filterChainResolver() {
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(defaultFilterChainManager());
        return chainResolver;
    }

}
