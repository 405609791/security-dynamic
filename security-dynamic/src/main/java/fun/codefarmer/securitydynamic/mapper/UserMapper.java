package fun.codefarmer.securitydynamic.mapper;

import fun.codefarmer.securitydynamic.pojo.Role;
import fun.codefarmer.securitydynamic.pojo.User;

import java.util.List;

public interface UserMapper {

    User getUerByUsername(String username);

    List<Role> getRolesByUserId(Integer id);
}
