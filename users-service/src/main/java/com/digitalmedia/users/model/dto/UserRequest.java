package com.digitalmedia.users.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserRequest {

    @Schema(example = "avatar")
    private String avatar;
    @Schema(example = "id")
    private String id;
    @Schema(example = "username")
    private String username;
    @Schema(example = "email")
    private String email;
    @Schema(example = "firstName")
    private String firstName;
    @Schema(example = "lastName")
    private String lastName;
}
