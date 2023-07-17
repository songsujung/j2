package org.zerock.j2.service;

import jakarta.transaction.Transactional;
import org.zerock.j2.dto.MemberCartDTO;
import org.zerock.j2.entity.MemberCart;

import java.util.List;

@Transactional
public interface MemberCartService {

    List<MemberCartDTO> addCart(MemberCartDTO memberCartDTO);

    List<MemberCartDTO> getCart(String email);

    // dto -> Entity 로 변환
    default MemberCart dtoToEntity(MemberCartDTO dto) {
        MemberCart entity = MemberCart.builder()
                .cno(dto.getCno())
                .email(dto.getEmail())
                .pno(dto.getPno())
                .build();
        return entity;
    }

    // Entity -> dto 로 변환
    default MemberCartDTO entityToDTO(MemberCart entity){

        MemberCartDTO dto = MemberCartDTO.builder()
                .cno(entity.getCno())
                .email(entity.getEmail())
                .pno(entity.getPno())
                .build();

        return dto;
    }
}
