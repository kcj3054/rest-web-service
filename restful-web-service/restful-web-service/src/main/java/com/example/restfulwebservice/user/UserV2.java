package com.example.restfulwebservice.user;


import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value = {"password"})
@JsonFilter("UserInfoV2")
@NoArgsConstructor
public class UserV2 extends  User{

   private String grade;


}
