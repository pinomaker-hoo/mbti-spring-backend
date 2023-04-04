package pinomaker.mbti.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pinomaker.mbti.domain.Answer;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {

}
