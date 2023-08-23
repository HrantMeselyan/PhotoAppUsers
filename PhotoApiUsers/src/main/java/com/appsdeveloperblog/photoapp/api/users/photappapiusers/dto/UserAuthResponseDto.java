package com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthResponseDto {

    private String token;
}