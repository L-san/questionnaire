package ru.lsan.opencode.questionnaire.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.lsan.opencode.questionnaire.client.QuestionnaireClient;
import ru.lsan.opencode.questionnaire.client.StatisticsClient;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.StatisticsDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import java.util.List;

@Controller
@RequestMapping("/statistics")
@SessionAttributes(types = UserDTO.class)
@RequiredArgsConstructor
public class StatisticsViewController {

    private final StatisticsClient statisticsClient;
    private final QuestionnaireClient questionnaireClient;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('VIEW_USER_RATING')")
    public String index(@ModelAttribute("questionnaireDTO") QuestionnaireDTO dto, Model model) {
        List<StatisticsDTO> statisticsDTOList = statisticsClient.findStatisticsByQuestionnaireId(dto.getId()).getBody();
        dto = questionnaireClient.findById(dto.getId()).getBody();
        model.addAttribute("statistics", statisticsDTOList);
        model.addAttribute("questionnaireDTO", dto);
        return "statistics";
    }

}
