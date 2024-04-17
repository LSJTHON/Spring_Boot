package com.codehows.sbd.service;


import com.codehows.sbd.domain.Article;
import com.codehows.sbd.dto.AddArticleRequest;
import com.codehows.sbd.dto.UpdateArticleRequest;
import com.codehows.sbd.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


//final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
//필수 인자를 가진 생성자를 만들겠다.
@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request){
        //blogRepository에 저장하게 되면 request에 있는 content 와 title 두 값과 id가 함께 저장된다.
        return blogRepository.save(request.toEntity());
    }

    //블로그의 모든 글 가져오는 용도?
    public List<Article> findAll(){
        return blogRepository.findAll();
    }

    //특정 id를 가진 블로그 게시물 가져오는 용도
    //없는 게시물의 아이디를 가져온다면 특정 문구를 띄워줌
    public Article findById(long id){
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));
    }

    //특정 아이디를 삭제하는 메소드
    public void delete(long id){
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request){

        //해당하는 아이디를 찾음.
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: "+id));

        article.update(request.getTitle(),request.getContent());

        return article;
    }
}
