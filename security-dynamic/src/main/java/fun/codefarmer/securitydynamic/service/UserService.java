package fun.codefarmer.securitydynamic.service;

import fun.codefarmer.securitydynamic.mapper.UserMapper;
import fun.codefarmer.securitydynamic.pojo.Role;
import fun.codefarmer.securitydynamic.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @ @ClassName UserService
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/10 15:06
 **/
@Service
public class UserService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUerByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名，不存在");
        }
        List<Role> roles = userMapper.getRolesByUserId(user.getId());
        user.setRoles(roles);
        return user;
    }
}
