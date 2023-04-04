package pinomaker.mbti.dto;

import lombok.Data;

@Data
public class RequestSaveUserDto {
    private String id;
    private String password;
    private String mbti;
    private String name;
}