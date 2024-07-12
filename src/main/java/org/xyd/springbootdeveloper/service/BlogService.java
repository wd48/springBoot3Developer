package org.xyd.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xyd.springbootdeveloper.domain.Article;
import org.xyd.springbootdeveloper.dto.AddArticleRequest;
import org.xyd.springbootdeveloper.dto.UpdateArticleRequest;
import org.xyd.springbootdeveloper.repository.BlogRepository;

import java.util.List;

/**
 * packageName    : org.xyd.springbootdeveloper.service
 * fileName       : BlogService
 * author         : user
 * date           : 2024-07-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-11        user       최초 생성
 */
@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service    // Bean 등록
public class BlogService {

    private final BlogRepository blogRepository;

    // 블로그 글 추가 메서드
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 블로그 전체 글 목록 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    // 블로그 글 삭제
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    // 블로그 글 수정
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found:" + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
