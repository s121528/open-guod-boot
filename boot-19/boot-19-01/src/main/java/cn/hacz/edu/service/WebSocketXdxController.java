package cn.hacz.edu.service;

import org.springframework.web.bind.annotation.GetMapping;

public interface WebSocketXdxController {

    @GetMapping("/xdx/text")
    Object xdxTest(String shipId) throws InterruptedException;
}
