package com.example.restfulwebservice.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"})
@NoArgsConstructor
//@JsonFilter("UserInfo")
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer ID;

    @Size(min = 2, message = "2글자 이상으로 적어주세요")
    @ApiModelProperty(notes = "사용자 이름을 입력주세요 ")
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해주세요")
    private Date joinData;

    //client가 받아 볼때는 jsonignore가 붙은것이 보이지 않는다
   // @JsonIgnore
    @ApiModelProperty(notes = "사용자 비밀번호를 입력해주세요 ")
    private String password;

  //  @JsonIgnore
  @ApiModelProperty(notes = "사용자 ssn를 입력해주세요 ")
    private String ssn;


}
