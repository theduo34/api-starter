package com.codewithmosh.store.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class UserDto {
    //@JsonProperty(value = "user_id")
    private Long id;
    private String name;
    private String email;
    private String role;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime createdAt;
}
