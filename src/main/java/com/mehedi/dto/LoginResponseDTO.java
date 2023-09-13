package com.mehedi.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginResponseDTO {
//    private String email;
//    private String bearerToken;
    private Long userId;
    private String email;
    private String AccessToken;
}
