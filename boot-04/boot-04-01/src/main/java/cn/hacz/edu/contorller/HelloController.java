package cn.hacz.edu.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dong
 * @version 1.0
 */
@RestController
public class HelloController {
    @GetMapping(value = "/sayHello")
    public String sayHello(String name) {
        System.out.println("name" + name);
        return "hello" + name;
    }
}
