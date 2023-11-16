package com.example.apiuplay.models.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModifyPasswordDTO {

    private String email;
    private Long questionId;
    private String answer;
    private String newPassword;
}
