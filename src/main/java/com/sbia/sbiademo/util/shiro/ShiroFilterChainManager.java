package com.sbia.sbiademo.util.shiro;

import com.sbia.sbiademo.util.StringUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShiroFilterChainManager {
    @Autowired
    private DefaultFilterChainManager filterChainManager;
    private Map<String, NamedFilterList> defaultFilterChains;
    @PostConstruct
    public void init() {
        defaultFilterChains =
                new HashMap<String, NamedFilterList>(filterChainManager.getFilterChains());
    }
    /*
     * 当添加一个url-过滤器关系的时候，更新每个url会获得的过滤器连(添加、更新一个权限的时候)
     */
    public void initFilterChains(List<UrlFilter> urlFilters) {
        //1、首先删除以前老的filter chain并注册默认的
        filterChainManager.getFilterChains().clear();
        if(defaultFilterChains != null) {
            filterChainManager.getFilterChains().putAll(defaultFilterChains);
        }
        //2、循环URL Filter 注册filter chain
        for (UrlFilter urlFilter : urlFilters) {
            String url = urlFilter.getUrl();
            //注册roles filter
            if (!StringUtils.isEmpty(urlFilter.getRoles())) {
                filterChainManager.addToChain(url, "roles", urlFilter.getRoles());
            }
            //注册perms filter
            if (!StringUtils.isEmpty(urlFilter.getPermissions())) {
                filterChainManager.addToChain(url, "perms", urlFilter.getPermissions());
            }
        }
    }
}
