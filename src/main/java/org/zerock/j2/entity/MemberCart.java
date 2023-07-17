package org.zerock.j2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Table(name = "member_cart", indexes = @Index(columnList = "email, cno"))
public class MemberCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cno;

    private String email;

    private Long pno;

    // 원래는 히스토리이기때문에 등록시간, 수정시간이 있어야함

}
