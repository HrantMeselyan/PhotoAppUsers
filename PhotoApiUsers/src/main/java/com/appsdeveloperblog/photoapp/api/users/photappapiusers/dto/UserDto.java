package com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto;

import com.appsdeveloperblog.photoapp.api.users.photappapiusers.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    @NotNull(message = "not null")
    @Size(min = 2, max = 10)
    private String firstName;
    @NotNull(message = "not null")
    @Size(min = 2, max = 10)
    private String lastName;
    @NotNull
    private Role role;
    @Email
    private String email;
    private List<AlbumsResponseDto> albumsResponseDtoList;
}
