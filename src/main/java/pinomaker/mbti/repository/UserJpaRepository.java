package pinomaker.mbti.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pinomaker.mbti.domain.User;

import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findUserById(String id);

    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesById(String name);
}
