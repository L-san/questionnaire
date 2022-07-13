package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;
import ru.lsan.opencode.questionnaire.database.entity.AnswerEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;

import java.util.*;

@Data
@Builder
public class QuestionDTO {

    Long id;

    String value;

    Set<AnswerDTO> answers;

    Integer rightAnswersNumber;

    @Override
    public boolean equals(Object obj) {
        QuestionDTO question = (QuestionDTO) obj;
        return Objects.equals(this.id, question.getId());
    }

    @Override
    public int hashCode() {
        return Math.toIntExact(this.id);
    }

    public static QuestionDTO fromQuestionEntity(QuestionEntity entity) {
        Set<AnswerDTO> answers = new HashSet<>();
        for (AnswerEntity answer : entity.getAnswers()) {
            answers.add(AnswerDTO.fromAnswerEntity(answer));
        }
        return QuestionDTO.builder()
                .id(entity.getId())
                .value(entity.getTheme())
                .answers(answers)
                .rightAnswersNumber(entity.getRightAnswersNumber())
                .build();
    }

}
