package pinomaker.mbti.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.dto.RequestLoginUserDto;
import pinomaker.mbti.dto.RequestSaveUserDto;
import pinomaker.mbti.service.UserService;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public RequestResponseDto<?> saveUser(@RequestBody RequestSaveUserDto dto) {
        return userService.save(dto);
    }

    @PostMapping("/login")
    public RequestResponseDto<?> login(@RequestBody RequestLoginUserDto dto) {
        return userService.login(dto);
    }
}
