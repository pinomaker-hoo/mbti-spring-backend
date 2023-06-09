package pinomaker.mbti.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.dto.RequestLoginUserDto;
import pinomaker.mbti.dto.RequestSaveUserDto;
import pinomaker.mbti.dto.RequestTokenDto;
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

    @PostMapping("/token")
    public RequestResponseDto<?> token(@RequestBody RequestTokenDto dto) {
        return userService.getTokenByRefreshToken(dto);
    }

    @GetMapping("/{idx}")
    public RequestResponseDto<?> getUser(@PathVariable(name = "idx") Long idx) {
        return userService.findUserById(idx);
    }
}
