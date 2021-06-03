package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private UserDaoService service;

    //생성자를 통한 의존성 주입
    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        //id, name, joinDate.. 값들을 필터로 제어한다
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        //filter를 사용할때 어떤 빈에대해 필터할것인지 명시 "UserInfo"
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return mapping;
    }

    //우리는 id를 숫자로 해도 서버측에 전달 될 경우에는 -> String으로 된다
    //id로 하면 자동으로 원하는 int에 맞게 찾아준다
    //v1/users/
    @GetMapping("v1//users/{id}")
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {

        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found ", id));
        }

        //id, name, joinDate.. 값들을 필터로 제어한다
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "ssn");

        //filter를 사용할때 어떤 빈에대해 필터할것인지 명시 "UserInfo"
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);
        return mapping;

    }

    @GetMapping("v2//users/{id}")
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {

        User user = service.findOne(id);
        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found ", id));
        }

        //User -> userV2로 옮기는 쉬운 방법
        UserV2 userV2 = new UserV2();

        //Beanutils -> 두 프로퍼티 사이에 공통적인 것이 있을 경우에는 copy가능
        //Beanutils -> springframework에서 사용 중인 것이다
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        //id, name, joinDate.. 값들을 필터로 제어한다
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");

        //filter를 사용할때 어떤 빈에대해 필터할것인지 명시 "UserInfo"
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);
        return mapping;

    }
}
