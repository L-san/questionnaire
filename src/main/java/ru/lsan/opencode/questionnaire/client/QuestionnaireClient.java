package ru.lsan.opencode.questionnaire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.List;

@FeignClient(name = "questionnaire", url = "https://localhost:8080/api/questionnaire")
public interface QuestionnaireClient {

    @PutMapping("/create")
    ResponseEntity<QuestionnaireDTO> create(@RequestBody QuestionnaireDTO questionnaireDTO);

    @PutMapping("/update")
    ResponseEntity<QuestionnaireDTO> update(@RequestBody QuestionnaireDTO questionnaireDTO);

    @GetMapping("/all")
    ResponseEntity<List<QuestionnaireDTO>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<QuestionnaireDTO> findById(@PathVariable("id") Long id);

    @GetMapping("/answers/{id}")
    ResponseEntity<Integer> countRightAnswersNumber(@PathVariable("id") Long id);

    @GetMapping("/answers/count/{id}")
    ResponseEntity<Integer> countUserRateOnQuestionnaire(@PathVariable("id") Long id);

    @PostMapping("/all")
    ResponseEntity<List<QuestionnaireDTO>> findAllQuestionnairesByUsername(@RequestBody UserDTO userDTO);

    @PostMapping("/mark")
    ResponseEntity<QuestionnaireDTO> markUserAnswers(@RequestBody UserDTO userDTO, @RequestBody QuestionnaireDTO questionnaireDTO);

}
