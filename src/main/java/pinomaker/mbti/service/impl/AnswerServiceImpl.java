package pinomaker.mbti.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.domain.Answer;
import pinomaker.mbti.domain.User;
import pinomaker.mbti.dto.RequestSaveAnswerDto;
import pinomaker.mbti.repository.AnswerJpaRepository;
import pinomaker.mbti.repository.UserJpaRepository;
import pinomaker.mbti.service.AnswerService;
import pinomaker.mbti.util.SecurityUtil;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    private final AnswerJpaRepository answerJpaRepository;
    private final UserJpaRepository userJpaRepository;

    @Override
    public RequestResponseDto<?> save(RequestSaveAnswerDto dto, Long idx) {
        Optional<User> findUser = userJpaRepository.findUserByIdx(idx);

        if (findUser.isEmpty()) {
            return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "존재하지 않는 계정 입니다.", false);
        }

        Answer saveAnswer = Answer.builder()
                .name(dto.getName())
                .guestMbti(dto.getGusetMbti())
                .mbti(dto.getMbti())
                .text(dto.getText())
                .user(findUser.get()).build();

        return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "응답 생성 성공", answerJpaRepository.save(saveAnswer));
    }

    @Override
    public RequestResponseDto<?> findAll() {
        String id = SecurityUtil.getCurrentUserId();

        Optional<User> findUser = userJpaRepository.findUserById(id);

        if (findUser.isEmpty()) {
            return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "존재하지 않는 계정 입니다.", false);
        }

        return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "응답 조회 성공", answerJpaRepository.findAllByUser(findUser.get()));
    }
}
