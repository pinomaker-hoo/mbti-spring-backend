package pinomaker.mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pinomaker.mbti.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByAuthKeyAndType(String authKey,String type);
}
