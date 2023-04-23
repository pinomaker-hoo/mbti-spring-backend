package pinomaker.mbti.repository.answer;

import org.springframework.data.jpa.repository.JpaRepository;
import pinomaker.mbti.domain.Answer;
import pinomaker.mbti.domain.User;

import java.util.List;

public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByUser(User user);
}
