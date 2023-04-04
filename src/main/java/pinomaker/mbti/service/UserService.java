package pinomaker.mbti.service;

import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.dto.RequestLoginUserDto;
import pinomaker.mbti.dto.RequestSaveUserDto;
import pinomaker.mbti.dto.RequestTokenDto;

public interface UserService {
    RequestResponseDto<?> save(RequestSaveUserDto dto);

    RequestResponseDto<?> login(RequestLoginUserDto dto);

    RequestResponseDto<?> getTokenByRefreshToken(RequestTokenDto dto);
}
