package org.zerock.j2.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long pno;

    private String pname;

    private String pdesc;

    private int price;

    @Builder.Default
    private List<String> images = new ArrayList<>();

    // 등록, 수정 업로드된 파일 데이터를 수집하는 용도
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();
    
}
