package com.watson.auth.user.dto;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class UserSignupResponse {

    @Id
    private String authId;

    @Column(nullable = false)
    private String authType;

}