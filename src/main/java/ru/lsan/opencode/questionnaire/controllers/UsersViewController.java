package ru.lsan.opencode.questionnaire.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.lsan.opencode.questionnaire.client.QuestionnaireClient;
import ru.lsan.opencode.questionnaire.client.UsersClient;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
@SessionAttributes({"userDTO", "chosenUser", "questionnaireDTO"})
@RequiredArgsConstructor
public class UsersViewController {

    private final UsersClient usersClient;
    private final QuestionnaireClient questionnaireClient;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('VIEW_USER_LIST')")
    public String getAllUsers(Model model) {
        List<UserDTO> userDTOList = usersClient.findAll().getBody();
        model.addAttribute("userList", userDTOList);
        model.addAttribute("chosenUser", UserDTO.builder().build());
        return "users";
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('VIEW_GIVEN_ANSWERS')")
    public String viewUsersQuestionnaires(@ModelAttribute("chosenUser") UserDTO chosenUser, Model model) {
        List<QuestionnaireDTO> themesList = questionnaireClient.findAllQuestionnairesByUsername(chosenUser).getBody();
        QuestionnaireDTO questionnaireDTO = QuestionnaireDTO.builder().build();
        model.addAttribute("themesList", themesList);
        model.addAttribute("chosenUser", chosenUser);
        model.addAttribute("questionnaireDTO", questionnaireDTO);
        return "users_qs";
    }

    @GetMapping("/answers")
    @PreAuthorize("hasAuthority('VIEW_GIVEN_ANSWERS')")
    public String viewUsersAnswers(@ModelAttribute("questionnaireDTO") QuestionnaireDTO questionnaireDTO,
                                   Model model) {
        UserDTO chosenUser = (UserDTO) model.getAttribute("chosenUser");
        questionnaireDTO = questionnaireClient.findById(questionnaireDTO.getId()).getBody();
        questionnaireDTO = questionnaireClient.markUserAnswers(chosenUser, questionnaireDTO).getBody();
        model.addAttribute("questionnaireDTO", questionnaireDTO);
        model.addAttribute("chosenUser", chosenUser);
        return "answers";
    }

}
