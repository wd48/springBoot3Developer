package org.xyd.springbootdeveloper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    /**
     * 엔티티에 있는 데이터들을 조회, 저장, 변경, 삭제할 때 사용하는 인터페이스
     * Jpa 제공하는 JpaRepository 클래스를 상속받아 간단하게 구현 가능
     */
}
