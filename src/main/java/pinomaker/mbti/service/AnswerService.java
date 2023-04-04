package pinomaker.mbti.service;

import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.dto.RequestSaveAnswerDto;

public interface AnswerService {
    RequestResponseDto<?> save(RequestSaveAnswerDto dto, Long idx);

    RequestResponseDto<?> findAll(Long idx);
}
