package pinomaker.mbti.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pinomaker.mbti.repository.AnswerJpaRepository;
import pinomaker.mbti.service.AnswerService;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerJpaRepository answerJpaRepository;
}
