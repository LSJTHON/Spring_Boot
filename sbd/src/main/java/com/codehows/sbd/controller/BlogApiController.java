package com.codehows.sbd.controller;


import com.codehows.sbd.domain.Article;
import com.codehows.sbd.dto.AddArticleRequest;
import com.codehows.sbd.dto.ArticleResponse;
import com.codehows.sbd.dto.UpdateArticleRequest;
import com.codehows.sbd.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //여기서 필수적인 인자는
@RestController //Http Response Body에 객체 데이터를 JSON 형태로 반환하는 컨트롤러
public class BlogApiController {
    private final BlogService blogService; //Autowired를 사용해서 만들어도 무방하다.

    //메소드가 POST일 때 요청받는 URI와 동일한 메소드와 매핑
    @PostMapping("/api/articles")
    //Article을 응답하여 보내준다.
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        //받은 request의 값들을 Article의 savedArticle 로 받는다.
        Article savedArticle = blogService.save(request);

        // 저장된 블로그 글 정보를 응답 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles(){
        //blogService 에 있는 JSON형태의 정보들을 모두 articles에 선언한다.
        //ArticleResponse 는 컬럼이 title과 content만 있고 blogService 는 id도 포함하고 있기 때문에
        //mapping을 시켜서 articleResponse에 담아준다.
        //마지막으로 List형태로 담아준다.
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        //최종적으로 JSON 형태로 body부분에 articles가 들어가게 된다.
        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    //api/articles/{id} 에 있는 {id} 값을 long 타입의 id 으로 가져온다
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id){
        //하나의 blogService의 id, title, content 한줄을 가져오고 Article 객체인 article에 저장한다.
        Article article = blogService.findById(id);

        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    //글 삭제하는 부분
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id){
        //delete(id) 가 실행되었을떄 repository안에 있는 아이디와 매칭되는 부분을 삭제시킨다.
        blogService.delete(id);

        //응답에 대한 부분은 정상적으로 실행되었을떄
        return ResponseEntity.ok()
                //ok 만 보내려는 용도 바디가 없으므로 build만 사용해서 ok만 넘김
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request){
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }

}
