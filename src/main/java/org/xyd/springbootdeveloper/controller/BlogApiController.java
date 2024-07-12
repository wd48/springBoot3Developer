package org.xyd.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xyd.springbootdeveloper.domain.Article;
import org.xyd.springbootdeveloper.dto.AddArticleRequest;
import org.xyd.springbootdeveloper.dto.ArticleResponse;
import org.xyd.springbootdeveloper.dto.UpdateArticleRequest;
import org.xyd.springbootdeveloper.service.BlogService;

import java.util.List;

/**
 * packageName    : org.xyd.springbootdeveloper.controller
 * fileName       : BlogApiController
 * author         : user
 * date           : 2024-07-12
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-12        user       최초 생성
 */
@RequiredArgsConstructor
@RestController // HTTP response body에 객체 데이터를 JSON 형식으로 반환하는 컨트롤러
public class BlogApiController {

    private final BlogService blogService;

    /**
     * save article
     * /api/articles 로 POST request가 오면,
     * BlogService 의 save() 메소드 호출하고 생성된 블로그 글을 반환하는 작업
      */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        // 요청한 자원시 생성되었음 & 저장된 블로그 글 정보를 response 객체에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    /**
     * list all articles
     * /api/articles GET request
     */
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = blogService.findAll()  // 1. 글 전체를 조회하는 findAll() 메서드 호출
                .stream()   // 스트림 적용
                .map(ArticleResponse::new) // 2. 응답용 객체인 ArticleResponse로 파싱
                .toList();

        return ResponseEntity.ok().body(articles);    // 3. body에 담아 클라이언트에 전송
    }

    /**
     * find an article by id
     */
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = blogService.findById(id);

        return ResponseEntity.ok().body(new ArticleResponse(article));
    }

    /**
     * delete an article by id
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        blogService.delete(id);

        return ResponseEntity.ok().build();
    }

    /**
     * update an article
     */
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id, @RequestBody UpdateArticleRequest request) {
        Article updatedArticle = blogService.update(id, request);

        return ResponseEntity.ok().body(updatedArticle);
    }
}
