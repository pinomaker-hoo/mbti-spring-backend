package pinomaker.mbti.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pinomaker.mbti.common.dto.RequestResponseDto;
import pinomaker.mbti.dto.RequestSaveAnswerDto;
import pinomaker.mbti.service.AnswerService;

@RestController
@RequestMapping("/api/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping("/{id}")
    private RequestResponseDto<?> saveAnswer(@RequestBody RequestSaveAnswerDto dto, @PathVariable(name = "id") Long id ){
        return answerService.save(dto, id);
    }

    @GetMapping("/{id}")
    private RequestResponseDto<?> findAnswerList(@PathVariable(name = "id") Long id ){
        return answerService.findAll(id);
    }
}
