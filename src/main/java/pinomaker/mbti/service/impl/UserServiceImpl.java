package pinomaker.mbti.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.common.dto.UserAuthority;
import pinomaker.mbti.domain.User;
import pinomaker.mbti.dto.RequestLoginUserDto;
import pinomaker.mbti.dto.RequestSaveUserDto;
import pinomaker.mbti.jwt.TokenProvider;
import pinomaker.mbti.repository.UserJpaRepository;
import pinomaker.mbti.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public RequestResponseDto<?> save(RequestSaveUserDto dto) {
        try {
            Optional<User> findUser = userJpaRepository.findUserById(dto.getId());
            if (findUser.isPresent()) {
                return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "이미 존재 하는 계정 입니다.", false);
            }

            User saveUser = User.builder()
                    .id(dto.getId())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .mbti(dto.getMbti())
                    .name(dto.getName())
                    .authority(UserAuthority.ROLE_USER)
                    .build();

            return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "회원가입 성공", userJpaRepository.save(saveUser));
        } catch (Exception e) {
            logger.info("ERROR :" + e);
            return RequestResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, RequestResponseDto.Code.FAILED, e.getMessage(), null);
        }
    }

    @Override
    public RequestResponseDto<?> login(RequestLoginUserDto dto) {
        try {
            Optional<User> findUser = userJpaRepository.findUserById(dto.getId());

            if (findUser.isEmpty()) {
                return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "사용자를 찾을 수 없습니다.", false);
            }

            if (!passwordEncoder.matches(dto.getPassword(), findUser.get().getPassword())) {
                return RequestResponseDto.of(HttpStatus.BAD_REQUEST, RequestResponseDto.Code.FAILED, "비밀번호가 같지 앖습니다.", false);
            }
            UsernamePasswordAuthenticationToken authenticationToken = dto.toAuthentication();
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, String> response = new HashMap<>();
            response.put("token", tokenProvider.createToken(authentication));
            return RequestResponseDto.of(HttpStatus.OK, RequestResponseDto.Code.SUCCESS, "로그인 성공 하였습니다.", response);
        } catch (Exception e) {
            logger.info("ERROR :" + e);
            return RequestResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, RequestResponseDto.Code.FAILED, e.getMessage(), null);
        }
    }
}
