package ru.lsan.opencode.questionnaire.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lsan.opencode.questionnaire.dto.UserAnswersDTO;
import ru.lsan.opencode.questionnaire.dto.UserDTO;

@FeignClient(name = "usersanswers", url = "https://localhost:8080/api/usersanswers")
public interface UsersAnswersClient {

    @PutMapping
    void create(@RequestBody UserAnswersDTO userAnswersDTO,
                @RequestBody UserDTO userDTO);

}
