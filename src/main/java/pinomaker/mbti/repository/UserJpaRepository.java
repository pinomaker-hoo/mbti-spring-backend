package pinomaker.mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pinomaker.mbti.domain.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
