package ru.lsan.opencode.questionnaire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.StatisticsDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.List;

@FeignClient(name = "statistics",url="https://localhost:8080/api/statistics")
public interface StatisticsClient {

    @PutMapping
    void create(@RequestBody UserDTO userDTO, @RequestBody QuestionnaireDTO questionnaireDTO);

    @PostMapping
    ResponseEntity<StatisticsDTO> get(@RequestBody UserDTO userDTO, @RequestBody QuestionnaireDTO questionnaireDTO);

    @GetMapping("/all/{id}")
    ResponseEntity<List<StatisticsDTO>> findStatisticsByQuestionnaireId(@PathVariable("id") Long id);

}
