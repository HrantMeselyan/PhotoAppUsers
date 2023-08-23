package com.appsdeveloperblog.photoapp.api.users.photappapiusers.service;

import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.User;

public interface UserService {
    UserDto createUser(User user);

    UserDto getById(Long id);
}
