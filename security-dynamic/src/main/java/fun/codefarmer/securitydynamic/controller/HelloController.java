package fun.codefarmer.securitydynamic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ @ClassName HelloController
 * @ Descriotion TODO
 * @ Author admin
 * @ Date 2020/2/10 16:44
 **/
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/db/hello")
    public String db() {
        return "db/hello";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin/hello";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user/hello";
    }
}
