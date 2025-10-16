package com.codewithmosh.store.users;

import com.codewithmosh.store.auth.RegisterUserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto userToUserDto(User user);
    User toRegisterUserRequest(RegisterUserRequest request);
    void toUpdateUserRequest(UpdateUserRequest request, @MappingTarget User user );
}
