package org.zerock.j2.repository.search;

import org.springframework.data.domain.jaxb.SpringDataJaxb.PageRequestDto;
import org.zerock.j2.dto.FileBoardListDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;

public interface FileBoardSearch {
    
    PageResponseDTO<FileBoardListDTO> list (PageRequestDTO pageRequestDTO);

}
