package org.zerock.j2.repository;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.entity.FileBoard;
import org.zerock.j2.entity.FileBoardImage;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class RepositoryTest {

    @Autowired
    FileBoardRepository repository;

    // 등록
    @Test
    public void insert(){

        for(int i = 1; i < 100; i++) {
        
        FileBoard fileBoard = FileBoard.builder()
        .title("AA")
        .content("aaa")
        .writer("user")
        .build();
        
        FileBoardImage img1 = FileBoardImage.builder()
        .uuid(UUID.randomUUID().toString())
        .fname("aaa.jpg")
        .build();

        FileBoardImage img2 = FileBoardImage.builder()
        .uuid(UUID.randomUUID().toString())
        .fname("bbb.jpg")
        .build();


        fileBoard.addImage(img1);
        fileBoard.addImage(img2);

        repository.save(fileBoard);
        }// end for
        
    }

    // 삭제
    @Test
    @Commit
    @Transactional
    public void testRemove() {

        Long bno = 2L;

        repository.deleteById(bno);
    }

    // 조회
    @Transactional
    @Test
    public void testRead() {

        Long bno = 99L;

        Optional<FileBoard> result = repository.findById(bno);

        FileBoard board = result.orElseThrow();

        System.out.println(board);
    }

    // 목록
    @Test
    @Transactional
    public void testList() {

        Pageable pageable = PageRequest.of(0, 10);

        Page<FileBoard> result = repository.findAll(pageable);

        // System.out.println(result);

        result.get().forEach(board -> { 
            System.out.println(board);
            System.out.println(board.getImages());
        
        });


    }
    
    // 목록 조회 검색 (Querydsl)
    @Test
    @Transactional
    public void testListQuerydsl() {

        PageRequestDTO requestDTO = new PageRequestDTO();

        System.out.println(repository.list(requestDTO));
    }

    @Test
    public void testSelectOne(){

        Long bno = 99L;

        FileBoard board = repository.selectOne(bno);

        System.out.println(board);
        System.out.println(board.getImages());


    }
    
    // 삭제
    @Test
    @Commit
    @Transactional
    public void testDelete(){

        Long bno = 90L;

        repository.deleteById(bno);
    }

    // 수정
    @Test
    @Commit
    @Transactional
    public void testUpdate(){

        Optional<FileBoard> result = repository.findById(20L);

        FileBoard board = result.orElseThrow();

        board.cheanImages();

        FileBoardImage img1 = FileBoardImage.builder()
            .uuid(UUID.randomUUID().toString())
            .fname("zzz.jpg")
            .build();
        
        board.addImage(img1);

        repository.save(board);
    }




}
