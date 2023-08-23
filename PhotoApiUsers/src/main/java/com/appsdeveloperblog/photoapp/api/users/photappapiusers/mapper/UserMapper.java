package com.appsdeveloperblog.photoapp.api.users.photappapiusers.mapper;

import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User map(UserDto dto);

    UserDto mapToDto(User entity);

}

