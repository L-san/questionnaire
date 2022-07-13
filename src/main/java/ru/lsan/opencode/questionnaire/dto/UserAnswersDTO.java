package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserAnswersDTO {

    Long questionId;

    List<Long> answerId;

    String username;

}
