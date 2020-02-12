package fun.codefarmer.securitydynamic.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @ @ClassName MyAccessDecisionManager
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/11 14:32
 **/
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    /**
     *
     * @param authentication 存登录后的用户
     * @param o 获得请求对象 ，与 myFilter 中的Object一样
     * @param collection myFilter 中返回的角色
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     * ctr + H 查看继承关系
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : collection) {
            // ROLE_login 登录就可以访问，
            if ("ROLE_login".equals(configAttribute.getAttribute())) {//需要登录访问
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new AccessDeniedException("非法请求");
                } else {//如果登录了 ，代表任何路径都可以访问
                    /**
                     * **此处开发时else写错位置，导致任何路径都可以访问的接口无法访问
                     */
                    return;
                }
            }
            //  需要正规角色
            // 在登录用户中获取 拥有的角色
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                if (authority.getAuthority().equals(configAttribute.getAttribute())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("非法请求");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
