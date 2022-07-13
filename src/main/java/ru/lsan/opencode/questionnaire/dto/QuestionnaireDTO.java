package ru.lsan.opencode.questionnaire.dto;

import lombok.Builder;
import lombok.Data;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;

import java.util.*;

@Data
@Builder
public class QuestionnaireDTO {

    Long id;

    String theme;

    List<QuestionDTO> questions;

    List<Long> answersIds;

    public static QuestionnaireDTO fromQuestionnaireEntity(QuestionnaireEntity entity){
        List<QuestionDTO> questionDTOList = new LinkedList<>();
        for(QuestionEntity question : entity.getQuestions()){
            questionDTOList.add(QuestionDTO.fromQuestionEntity(question));
        }
        return QuestionnaireDTO.builder()
                .id(entity.getId())
                .theme(entity.getTheme())
                .questions(questionDTOList)
                .build();
    }

}
