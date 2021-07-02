package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class post {
    @Id
    @GeneratedValue
    private int id;

    private String description;

    //user : post -> 1 : n( 0 ~ n) : main : sub -> parent : child
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)   // lazy -> post데이터가 로딩될때 필요한 사용자를 가져오겠다는 뜻
    private User user;
}
