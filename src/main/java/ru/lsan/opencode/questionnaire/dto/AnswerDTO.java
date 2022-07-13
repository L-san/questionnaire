package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;
import ru.lsan.opencode.questionnaire.database.entity.AnswerEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class AnswerDTO {

    Long id;

    String answer;

    Boolean isRight;

    Boolean isChosen;

    public static AnswerDTO of(String answer, Boolean isRight) {
        return AnswerDTO.builder()
                .answer(answer)
                .isRight(isRight)
                .build();
    }

    public static List<AnswerDTO> ofList(List<String> answers, List<Integer> rightIndexes) {
        List<AnswerDTO> answersList = new ArrayList<>(answers.size());
        int count = 0;
        for (String theme : answers) {
            if (rightIndexes.contains(count)) {
                answersList.add(of(theme,true));
            } else {
                answersList.add(of(theme,false));
            }
            count++;
        }
        return answersList;
    }

    public static AnswerDTO fromAnswerEntity(AnswerEntity answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .answer(answer.getTheme())
                .isRight(answer.getIsRight())
                .build();
    }
}
