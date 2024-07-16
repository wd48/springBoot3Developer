package org.xyd.springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.xyd.springbootdeveloper.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
