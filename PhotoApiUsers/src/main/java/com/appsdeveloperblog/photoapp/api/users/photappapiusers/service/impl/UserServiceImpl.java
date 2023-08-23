package com.appsdeveloperblog.photoapp.api.users.photappapiusers.service.impl;

import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.AlbumsResponseDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto.UserDto;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.mapper.UserMapper;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.User;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.repository.UserRepository;
import com.appsdeveloperblog.photoapp.api.users.photappapiusers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RestTemplate restTemplate;

    @Override
    public UserDto createUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        return userMapper.mapToDto(save);
    }


    @Override
    public UserDto getById(Long id) {
        Optional<User> byId = userRepository.findById(id);
        String albumsUrl = String.format("http://ALBUMS-WS/users/%s/albums", id);
        if (byId.isPresent()) {
            User user = byId.get();

            ResponseEntity<List<AlbumsResponseDto>> albumListResponse = restTemplate.exchange(
                    albumsUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<AlbumsResponseDto>>() {
                    }
            );

            List<AlbumsResponseDto> albumsList = albumListResponse.getBody();

            UserDto userDto = userMapper.mapToDto(user);
            userDto.setAlbumsResponseDtoList(albumsList);
            return userDto;
        }
        return null;
    }

}
