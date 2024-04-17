package com.codehows.sbd.dto;


import com.codehows.sbd.domain.Article;
import lombok.Getter;


//title과 content 분을 사용
@Getter
public class ArticleResponse {

    private final String title;
    private final String content;

    public ArticleResponse(Article article){
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
