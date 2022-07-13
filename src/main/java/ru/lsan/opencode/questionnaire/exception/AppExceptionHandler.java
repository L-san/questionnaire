package ru.lsan.opencode.questionnaire.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice
@SessionAttributes({"userDTO", "questionnaireDTO"})
public class AppExceptionHandler {

    @ExceptionHandler({AuthException.class})
    public String handleAuthException(Model model, AuthException authException) {
        UserDTO user = (UserDTO) model.getAttribute("user");
        model.addAttribute("user", user);
        return "auth";
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public String handleConstraintViolationException(Model model, ConstraintViolationException e) {
        UserDTO user = (UserDTO) model.getAttribute("user");
        QuestionnaireDTO questionnaireDTO = (QuestionnaireDTO) model.getAttribute("questionnaireDTO");
        model.addAttribute("user", user);
        model.addAttribute("questionnaireDTO", questionnaireDTO);
        return "redirect:/questionnaire/view/rate";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public void handleError(HttpServletResponse response) {
        try {
            response.sendRedirect("/questionnaire/all");
        } catch (IOException ignored) {
        }
    }

}
