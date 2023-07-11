package org.zerock.j2.repository.search;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.zerock.j2.dto.FileBoardListDTO;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.dto.PageResponseDTO;
import org.zerock.j2.entity.FileBoard;
import org.zerock.j2.entity.QFileBoard;
import org.zerock.j2.entity.QFileBoardImage;

import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileBoardSearchImpl extends QuerydslRepositorySupport implements FileBoardSearch {

    public FileBoardSearchImpl() {
        super(FileBoard.class); // 도메인 이름 넣기
        
    }

    @Override
    public PageResponseDTO<FileBoardListDTO> list(PageRequestDTO pageRequestDTO) {
        
        QFileBoard board = QFileBoard.fileBoard;

        // QFileBoardImage boardImage = QFileBoardImage.fileBoardImage; - testListQuerydsl오류 잡기 1

        JPQLQuery<FileBoard> query = from(board);

        //  query.leftJoin(boardImage); - testListQuerydsl오류 잡기 2

        // query.where(boardImage.ord.eq(0)); - testListQuerydsl오류 잡기 3

        int pageNum = pageRequestDTO.getPage() - 1 < 0 ? 0 : pageRequestDTO.getPage() - 1;

        Pageable pageable = PageRequest.of(
            pageNum,
            pageRequestDTO.getSize(),
            Sort.by("bno").descending());

        this.getQuerydsl().applyPagination(pageable, query);

        List<FileBoard> list = query.fetch();
            
        // log.info(list); - testListQuerydsl오류 잡기 4

        // 추가 - testListQuerydsl오류 잡기 5
        list.forEach(fb -> {
            log.info(fb);
            log.info(fb.getImages());
        });

        return null;
    }
    
}
