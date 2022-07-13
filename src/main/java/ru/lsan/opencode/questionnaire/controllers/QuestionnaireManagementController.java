package ru.lsan.opencode.questionnaire.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.QuestionnaireClient;
import ru.lsan.opencode.questionnaire.dto.AnswerDTO;
import ru.lsan.opencode.questionnaire.dto.FormDTO;
import ru.lsan.opencode.questionnaire.dto.QuestionDTO;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/manage")
@SessionAttributes({"userDTO", "formDTO"})
@RequiredArgsConstructor
public class QuestionnaireManagementController {

    private final QuestionnaireClient questionnaireClient;

    @GetMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_QUESTIONNAIRES')")
    public String create(Model model) {
        FormDTO formDTO = FormDTO.builder()
                .answers(new LinkedList<>())
                .build();
        QuestionnaireDTO questionnaireDTO = QuestionnaireDTO.builder().build();
        model.addAttribute("formDTO", formDTO);
        model.addAttribute("questionnaireDTO", questionnaireDTO);
        return "add";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE_QUESTIONNAIRES')")
    public String createPost(@ModelAttribute("formDTO") FormDTO formDTO, Model model) {
        String theme = formDTO.getQuestionnaireTheme();
        Long id = formDTO.getId();
        QuestionnaireDTO questionnaireDTO;

        Set<AnswerDTO> answers = Set.copyOf(AnswerDTO.ofList(formDTO.getAnswers(),formDTO.getChosenAnswers()));
        QuestionDTO questionDTO = QuestionDTO.builder()
                .value(formDTO.getValue())
                .answers(answers)
                .build();
        questionnaireDTO = QuestionnaireDTO.builder()
                .theme(theme)
                .questions(List.of(questionDTO))
                .build();
        if(id!=null){
            questionnaireDTO.setId(id);
            questionnaireDTO = questionnaireClient.update(questionnaireDTO).getBody();

        }else{
            questionnaireDTO = questionnaireClient.create(questionnaireDTO).getBody();
        }

        formDTO = FormDTO.builder()
                .id(questionnaireDTO.getId())
                .questionnaireTheme(questionnaireDTO.getTheme())
                .answers(new LinkedList<>())
                .build();

        model.addAttribute("formDTO", formDTO);
        model.addAttribute("questionnaireDTO", questionnaireDTO);
        return "add";
    }

}
