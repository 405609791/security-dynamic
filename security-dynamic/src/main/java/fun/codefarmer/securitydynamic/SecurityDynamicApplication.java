package fun.codefarmer.securitydynamic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "fun.codefarmer.securitydynamic.mapper")
public class SecurityDynamicApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityDynamicApplication.class, args);
    }

}
