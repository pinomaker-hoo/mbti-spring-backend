package pinomaker.mbti.dto;

import lombok.Data;

@Data
public class RequestSaveAnswerDto {
    private String name;
    private String mbti;
    private String guestMbti;
    private String text;
}
