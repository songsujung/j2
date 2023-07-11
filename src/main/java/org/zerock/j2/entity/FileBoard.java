package org.zerock.j2.entity;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.BatchSize;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "images") // 연관 관계는 exclude 해줘라
public class FileBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String content;

    private String writer;

    @BatchSize(size = 20) // 일괄처리 20개까지는 한 번에 처리
    @OneToMany(cascade = {CascadeType.PERSIST , CascadeType.REMOVE} , fetch = FetchType.LAZY)
    @JoinColumn(name = "board")
    @Builder.Default
    private List<FileBoardImage> images = new ArrayList<>();
    
    public void addImage(FileBoardImage boardImage){

        // 처음에는 ArrayList가 비어있으니 사이즈:0
        boardImage.changeOrd(images.size());

        //리스트에 추가하기
        images.add(boardImage);

        //삭제 -> 이미지를 지우는건 - ArrayList를 비워주는 것
        

    }
}