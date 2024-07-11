package org.xyd.springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xyd.springbootdeveloper.domain.Article;

/**
 * packageName    : org.xyd.springbootdeveloper.repository
 * fileName       : BlogRepository
 * author         : user
 * date           : 2024-07-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-07-11        user       최초 생성
 */
public interface BlogRepository extends JpaRepository<Article, Long> {
}
