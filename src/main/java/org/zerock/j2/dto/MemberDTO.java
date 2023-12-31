package org.zerock.j2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private String email;

    private String pw;

    private String nickname;

    private boolean admin;

    private String accessToken;

    private String refreshToken;
}
