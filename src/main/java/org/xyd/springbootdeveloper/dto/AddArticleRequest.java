package org.xyd.springbootdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.xyd.springbootdeveloper.domain.Article;

/**
 * packageName    : org.xyd.springbootdeveloper.dto
 * fileName       : AddArticleRequest
 * author         : user
 * date           : 2024-07-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-11        user       최초 생성
 */
@NoArgsConstructor
@AllArgsConstructor // 모든 필드값을 파라미터로 받는 생성자
@Getter
public class AddArticleRequest {

    private String title;
    private String content;

    // 빌더 패턴을 사용하여 DTO -> Entity 해주는 메서드
    // 블로그 글을 추가할 때 저장할 엔티티로 변환하는 용도
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
