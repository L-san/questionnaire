package ru.lsan.opencode.questionnaire.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.StatisticsClient;
import ru.lsan.opencode.questionnaire.client.UsersClient;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;
import ru.lsan.opencode.questionnaire.database.service.QuestionnaireService;
import ru.lsan.opencode.questionnaire.database.service.StatisticsService;
import ru.lsan.opencode.questionnaire.database.service.UsersService;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.StatisticsDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class StatisticsController implements StatisticsClient {

    private final StatisticsService statisticsService;
    private final UsersService usersService;
    private final QuestionnaireService questionnaireService;

    @PutMapping
    public void create(@RequestBody UserDTO userDTO, @RequestBody QuestionnaireDTO questionnaireDTO) {
        UserEntity user = usersService.findByLogin(userDTO.getUsername());
        QuestionnaireEntity questionnaire = questionnaireService.findById(questionnaireDTO.getId());
        StatisticsEntity statistics = statisticsService.findByUserLoginAndQuestionnaireId(user.getLogin(), questionnaire.getId());
        if (statistics == null) {
            statisticsService.create(user, questionnaire);
        }else{
            statisticsService.update(statistics);
        }
    }

    @PostMapping
    public ResponseEntity<StatisticsDTO> get(@RequestBody UserDTO userDTO, @RequestBody QuestionnaireDTO questionnaireDTO) {
        StatisticsEntity statistics = statisticsService.findByUserLoginAndQuestionnaireId(userDTO.getUsername(), questionnaireDTO.getId());
        return ResponseEntity.ok(StatisticsDTO.fromStatisticsEntity(statistics));
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<List<StatisticsDTO>> findStatisticsByQuestionnaireId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(statisticsService.findStatisticsByQuestionnaireId(id));
    }

}

