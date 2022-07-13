package ru.lsan.opencode.questionnaire.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.QuestionnaireClient;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.service.QuestionnaireService;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.List;

@RestController
@RequestMapping("/api/questionnaire")
@RequiredArgsConstructor
public class QuestionnaireController implements QuestionnaireClient {

    private final QuestionnaireService questionnaireService;

    @PutMapping("/create")
    public ResponseEntity<QuestionnaireDTO> create(@RequestBody QuestionnaireDTO questionnaireDTO){
        QuestionnaireEntity questionnaire = questionnaireService.create(questionnaireDTO);
        return ResponseEntity.ok(QuestionnaireDTO.fromQuestionnaireEntity(questionnaire));
    }

    @PutMapping("/update")
    public ResponseEntity<QuestionnaireDTO> update(@RequestBody QuestionnaireDTO questionnaireDTO){
        QuestionnaireEntity questionnaire = questionnaireService.update(questionnaireDTO);
        return ResponseEntity.ok(QuestionnaireDTO.fromQuestionnaireEntity(questionnaire));
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionnaireDTO>> getAll(){
        return ResponseEntity.ok(questionnaireService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionnaireDTO> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(QuestionnaireDTO.fromQuestionnaireEntity(questionnaireService.findById(id)));
    }

    @GetMapping("/answers/{id}")
    public ResponseEntity<Integer> countRightAnswersNumber(@PathVariable("id") Long id){
        return ResponseEntity.ok(questionnaireService.countTotalAnswersByQuestionnaireId(id));
    }

    @GetMapping("/answers/count/{id}")
    public ResponseEntity<Integer> countUserRateOnQuestionnaire(@PathVariable("id") Long id){
        return ResponseEntity.ok(questionnaireService.countUserRateOnQuestionnaire(id));
    }

    @PostMapping("/all")
    public ResponseEntity<List<QuestionnaireDTO>> findAllQuestionnairesByUsername(@RequestBody UserDTO userDTO){
        String username = userDTO.getUsername();
        return ResponseEntity.ok(questionnaireService.findAllQuestionnairesByUsername(username));
    }

    @PostMapping("/mark")
    public ResponseEntity<QuestionnaireDTO> markUserAnswers(@RequestBody UserDTO userDTO, @RequestBody QuestionnaireDTO questionnaireDTO){
        String username = userDTO.getUsername();
        return ResponseEntity.ok(questionnaireService.markUserAnswers(questionnaireDTO,username));
    }

}
