package org.zerock.j2.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
@Log4j2
public class JWTTests {

    @Autowired
    private JWTUtil jwtUtil;

    @Test
    public void testCreate() {

        Map<String, Object> claim = Map.of("email", "wow_1@nate.com");

        String jwtStr = jwtUtil.generate(claim, 10);

        System.out.println(jwtStr);

    }

    @Test
    public void testToken() {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Indvd18xQG5hdGUuY29tIiwiaWF0IjoxNjg5NzQ0Mzk3LCJleHAiOjE2ODk3NDQ5OTd9.119y9kUggDBSqSFNCMExWP43iZAmhuPCqjL_UI_dQ2U";

        try {

            jwtUtil.validateToken(token);
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
