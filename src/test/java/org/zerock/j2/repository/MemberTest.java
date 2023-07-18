package org.zerock.j2.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.j2.dto.MemberDTO;
import org.zerock.j2.entity.Member;

import java.util.Optional;

@SpringBootTest
@Log4j2
public class MemberTest {

    @Autowired
    MemberRepository memberRepository;

    // 회원 데이터 추가
    @Test
    public void testInsert(){

        Member member = Member.builder()
                .email("wow_1@nate.com")
                .pw("1111")
                .nickname("sujung")
                .build();

        memberRepository.save(member);
    }


    // 조회
    @Test
    public void testRead(){
        String email = "sujung@gmail.com";

        Optional<Member> result = memberRepository.findById(email);

        Member member = result.orElseThrow();

        log.info(member);
    }

}
