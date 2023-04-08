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

    @PostMapping("/mbti/{idx}")
    private RequestResponseDto<?> saveAnswer(@RequestBody RequestSaveAnswerDto dto, @PathVariable(name = "idx") Long idx){
        return answerService.save(dto, idx);
    }

    @GetMapping("/mbti/{idx}")
    private RequestResponseDto<?> findAnswerListByGuest(@PathVariable(name = "idx") Long idx){
        return answerService.findAllByGuest(idx);
    }

    @GetMapping()
    private RequestResponseDto<?> findAnswerList(){
        return answerService.findAll();
    }
}
