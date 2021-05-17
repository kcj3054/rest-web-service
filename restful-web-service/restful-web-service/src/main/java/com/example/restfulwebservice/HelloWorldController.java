package com.example.restfulwebservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController //자바 빈형태로 반환해도 자동으로 json형식으로 된다
public class HelloWorldController {
    //GET

    //hello-world -> endpoint
    //@RequestMapping(method = RequesstMethod.GET...)
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
            return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean("Hello World");
    }
}
