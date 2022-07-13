package ru.lsan.opencode.questionnaire.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.lsan.opencode.questionnaire.client.UsersAnswersClient;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;
import ru.lsan.opencode.questionnaire.database.service.QuestionnaireService;
import ru.lsan.opencode.questionnaire.database.service.StatisticsService;
import ru.lsan.opencode.questionnaire.database.service.UserAnswersService;
import ru.lsan.opencode.questionnaire.dto.UserAnswersDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

@RestController
@RequestMapping("/api/usersanswers")
@RequiredArgsConstructor
public class UsersAnswersController implements UsersAnswersClient {

    private final UserAnswersService userAnswersService;
    private final QuestionnaireService questionnaireService;
    private final StatisticsService statisticsService;

    @PutMapping
    public void create(@RequestBody UserAnswersDTO userAnswersDTO,
                       @RequestBody UserDTO userDTO) {
        Long questionId = userAnswersDTO.getQuestionId();
        String username = userAnswersDTO.getUsername();
        QuestionnaireEntity questionnaire = questionnaireService.findByQuestionId(questionId);
        StatisticsEntity statistics = statisticsService.findByUserLoginAndQuestionnaireId(username, questionnaire.getId());
        userAnswersService.createAsList(userAnswersDTO, questionnaire, statistics);
    }

}
