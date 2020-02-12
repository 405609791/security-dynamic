package fun.codefarmer.securitydynamic.service;

import fun.codefarmer.securitydynamic.mapper.MenuMapper;
import fun.codefarmer.securitydynamic.pojo.Menu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ @ClassName MenuService
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/10 17:30
 **/
@Service
public class MenuService {

    @Resource
    MenuMapper menuMapper;
    public List<Menu> getAllMenu() {
        return menuMapper.getAllMenu();
    }
}
