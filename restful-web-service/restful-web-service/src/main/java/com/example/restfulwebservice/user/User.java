package com.example.restfulwebservice.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"})
@NoArgsConstructor
//@JsonFilter("UserInfo")
public class User {

    private Integer ID;

    @Size(min = 2, message = "2글자 이상으로 적어주세요")
    private String name;

    @Past
    private Date joinData;

    //client가 받아 볼때는 jsonignore가 붙은것이 보이지 않는다
   // @JsonIgnore
    private String password;

  //  @JsonIgnore
    private String ssn;


}
