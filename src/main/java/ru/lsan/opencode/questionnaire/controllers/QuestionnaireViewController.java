package ru.lsan.opencode.questionnaire.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.QuestionnaireClient;
import ru.lsan.opencode.questionnaire.client.StatisticsClient;
import ru.lsan.opencode.questionnaire.client.UsersAnswersClient;
import ru.lsan.opencode.questionnaire.dto.*;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
@SessionAttributes({"userDTO", "questionnaireDTO", "qSize", "question"})
public class QuestionnaireViewController {

    private final UsersAnswersClient usersAnswersClient;
    private final StatisticsClient statisticsClient;
    private final QuestionnaireClient questionnaireClient;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('VIEW_QUESTIONNAIRES')")
    public String getAllQuestionnaires(Model model) {
        List<QuestionnaireDTO> questionnaires = questionnaireClient.getAll().getBody();
        model.addAttribute("questionnaires", questionnaires);
        model.addAttribute("questionnaireDTO", QuestionnaireDTO.builder().build());
        return "main";
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('ANSWER_QUESTIONNAIRES')")
    public String viewQuestionnaire(@ModelAttribute("questionnaireDTO") QuestionnaireDTO dto, Model model) {
        UserDTO user = (UserDTO) model.getAttribute("userDTO");
        QuestionnaireDTO questionnaire = questionnaireClient.findById(dto.getId()).getBody();
        statisticsClient.create(user, dto);
        List<QuestionDTO> questionsDTO = questionnaire.getQuestions();
        dto.setQuestions(questionsDTO);
        QuestionDTO questionDTO = questionsDTO.get(0);
        model.addAttribute("question", questionDTO);
        model.addAttribute("qIndex", 1);
        model.addAttribute("qSize", questionsDTO.size());
        model.addAttribute("questionnaireDTO", dto);
        model.addAttribute("userAnswerDTO", new LinkedList<UserAnswersDTO>());
        return "test";
    }

    @GetMapping("/view/{num}")
    @PreAuthorize("hasAuthority('ANSWER_QUESTIONNAIRES')")
    public String viewQuestionnaireIndex(@ModelAttribute("questionnaireDTO") QuestionnaireDTO dto,
                                         @ModelAttribute("userDTO") @Valid UserDTO user,
                                         @PathVariable("num") Integer index,
                                         Model model) {
        List<QuestionDTO> questionsDTO = dto.getQuestions();
        QuestionDTO questionDTO = questionsDTO.get(0);

        QuestionDTO q = (QuestionDTO) model.getAttribute("question");
        questionsDTO.remove(q);

        usersAnswersClient.create(UserAnswersDTO.builder()
                .answerId(dto.getAnswersIds())
                .questionId(questionDTO.getId())
                .username(user.getUsername())
                .build(), user);

        Integer qSize = (Integer) model.getAttribute("qSize");
        model.addAttribute("questionnaireDTO", dto);
        if (index < qSize) {
            QuestionDTO question = questionsDTO.get(0);
            model.addAttribute("question", question);
            model.addAttribute("qIndex", index+1);
            model.addAttribute("userAnswerDTO", new LinkedList<UserAnswersDTO>());
            return "test";
        } else {
            model.addAttribute("qRes", questionnaireClient.countUserRateOnQuestionnaire(dto.getId()).getBody());
            model.addAttribute("qAll", questionnaireClient.countRightAnswersNumber(dto.getId()).getBody());
            return "result";
        }
    }

    @GetMapping("/view/rate")
    @PreAuthorize("hasAuthority('VIEW_USER_RATING')")
    public String viewQuestionnaireRate(@ModelAttribute("questionnaireDTO") QuestionnaireDTO dto,
                                        @ModelAttribute("userDTO") @Valid UserDTO user,
                                        Model model) {

        QuestionnaireDTO questionnaire = questionnaireClient.findById(dto.getId()).getBody();
        model.addAttribute("questionnaireDTO", questionnaire);
        model.addAttribute("qRes", questionnaireClient.countUserRateOnQuestionnaire(dto.getId()).getBody());
        model.addAttribute("qAll", questionnaireClient.countRightAnswersNumber(dto.getId()).getBody());
        return "result";
    }

}
