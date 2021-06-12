package com.example.restfulwebservice.user;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private UserDaoService service;

    //생성자를 통한 의존성 주입
    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {

        return service.findAll();
    }

    //우리는 id를 숫자로 해도 서버측에 전달 될 경우에는 -> String으로 된다
    //id로 하면 자동으로 원하는 int에 맞게 찾아준다
    //HETAOS를 적용하면 개발자의 양은 많아지지만
    //내가 개발한 것을 보는 사용자입장에서는 더 많은 정보를 알 수 있다
    // 사용자 상세 정보
    @GetMapping("/users/{id}")
    public ResponseEntity<EntityModel<User>> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException("id-" + id);
        }

        EntityModel entityModel = EntityModel.of(user);

        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return ResponseEntity.ok(entityModel);

    }

    //post, put 처럼 데이터 맵핑 할려면 파라미터에 request body로 형식을 적어줘야한다
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User saveUser = service.save(user);

        URI localtion =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getID())
                .toUri();
        return ResponseEntity.created(localtion).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {

        User user = service.deleteById( id);

        if(user == null) {
            throw  new UserNotFoundException(String.format("ID[%s] not found ", id));
        }
    }
}