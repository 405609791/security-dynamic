package fun.codefarmer.securitydynamic;

import fun.codefarmer.securitydynamic.service.MenuService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SecurityDynamicApplicationTests {
    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {
        System.out.println(menuService.getAllMenu());
    }

}
