package com.codehows.sbd.dto;


import com.codehows.sbd.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor  //기본 생성자 추가
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생성자 추가
@Getter
public class AddArticleRequest {
    private String title;
    private String content;

    //Controller -> Service -> Repository 하는 과정에서 DTO 를 이용해서 데이터를 저장함
    // 생성자를 사용해 객체 생성
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
