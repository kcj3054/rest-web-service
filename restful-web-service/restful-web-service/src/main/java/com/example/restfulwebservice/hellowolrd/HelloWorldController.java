package com.example.restfulwebservice.hellowolrd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController //자바 빈형태로 반환해도 자동으로 json형식으로 된다
public class HelloWorldController {

    //springframework에서 같은 타입이 이면 이쪽에 의존성 주입 해준다
    @Autowired
    private MessageSource messageSource;
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
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

    //Accept-Language이 포함되어있지 않으면 korean이 된다
    //message를 반환하기 위해서 messagesource를 사용한다
    @GetMapping("/hello-world-internationalized")
    public String helloworldinternationalized
            (@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);

    }
}
