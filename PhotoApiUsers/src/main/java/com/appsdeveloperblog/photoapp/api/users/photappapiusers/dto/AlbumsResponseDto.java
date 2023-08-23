package com.appsdeveloperblog.photoapp.api.users.photappapiusers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumsResponseDto {
    private long id;
    private String albumId;
    private String userId;
    private String name;
    private String description;

}
