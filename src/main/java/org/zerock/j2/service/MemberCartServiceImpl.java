package org.zerock.j2.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.j2.dto.MemberCartDTO;
import org.zerock.j2.entity.MemberCart;
import org.zerock.j2.repository.MemberCartRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberCartServiceImpl implements MemberCartService{

    private final MemberCartRepository cartRepository;

    // 카트 추가
    @Override
    public List<MemberCartDTO> addCart(MemberCartDTO memberCartDTO) {

        MemberCart cart = dtoToEntity(memberCartDTO);

        cartRepository.save(cart);

        List<MemberCart> cartList = cartRepository.selectCart(memberCartDTO.getEmail());

        return cartList.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
    }

    @Override
    public List<MemberCartDTO> getCart(String email) {
        List<MemberCart> cartList = cartRepository.selectCart(email);

        return cartList.stream().map(entity -> entityToDTO(entity)).collect(Collectors.toList());
    }
}