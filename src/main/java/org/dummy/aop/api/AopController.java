package org.dummy.aop.api;

import lombok.extern.slf4j.Slf4j;
import org.dummy.aop.annotaion.Timer;
import org.dummy.aop.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/aop")
public class AopController {

    @GetMapping("/get")
    public void methodGet(@RequestParam(value = "name") String name
                    , @RequestParam(value = "age") int age) {
    }

    @PostMapping("/post")
    public User methodPost(@RequestBody User user) {
        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {
        Thread.sleep(2000);
    }
}
