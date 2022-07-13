package ru.lsan.opencode.questionnaire.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select b from UserEntity b where b.login=:login")
    UserEntity findByLogin(@Param("login") String login);

}
