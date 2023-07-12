package org.zerock.j2.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Embeddable // 엔티티가 아닌 타입을 한 개 이상의 필드와 매핑할 때 사용
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductImage {
    
    private String fname;

    private int ord;
}
