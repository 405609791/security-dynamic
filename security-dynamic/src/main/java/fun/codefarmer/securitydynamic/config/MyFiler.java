package fun.codefarmer.securitydynamic.config;

import fun.codefarmer.securitydynamic.pojo.Menu;
import fun.codefarmer.securitydynamic.pojo.Role;
import fun.codefarmer.securitydynamic.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 用于配置动态加载资源的配置
 * 分析出请求的地址
 * @ @ClassName MyFiler
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/10 17:23
 **/
@Component
public class MyFiler implements FilterInvocationSecurityMetadataSource {
    //路径匹配工具
    AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    private MenuService menuService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenu = menuService.getAllMenu();
        for (Menu menu : allMenu) {
            if (pathMatcher.match(menu.getPattern(), requestUrl)) {
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];
                for (int i = 0; i < roles.size(); i++) {
                    // 如果数据库中没有ROLE_ 开头，需要在下面代码加上，否则角色对应不上。
                    /**
                     * 开发时，失误没有添加，导致凡是需要权限才能访问的接口都无法访问
                     * 有接口能访问，有接口不能访问
                     * 后台不报错，页面报403
                     */
                    rolesStr[i] = "ROLE_"+roles.get(i).getName();
                }
                return SecurityConfig.createList(rolesStr);
            }
        }
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
