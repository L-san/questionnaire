package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FormDTO {

    Long id;

    String value;

    List<String> answers;

    List<Integer> chosenAnswers;

    Integer rightAnswersNumber;

    String questionnaireTheme;

}
