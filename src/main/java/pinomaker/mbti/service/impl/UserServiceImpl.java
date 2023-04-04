package pinomaker.mbti.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pinomaker.mbti.repository.UserJpaRepository;
import pinomaker.mbti.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserJpaRepository userJpaRepository;
}
