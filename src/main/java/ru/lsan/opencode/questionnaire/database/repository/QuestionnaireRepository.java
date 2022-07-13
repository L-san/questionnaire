package ru.lsan.opencode.questionnaire.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lsan.opencode.questionnaire.database.entity.QuestionnaireEntity;
import ru.lsan.opencode.questionnaire.database.entity.UserAnswersEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionnaireRepository extends JpaRepository<QuestionnaireEntity, Long> {

    Optional<QuestionnaireEntity> findById(Long id);

    @Query("select distinct b.question.questionnaire from UserAnswersEntity b where b.statistics.user.login=:login")
    List<QuestionnaireEntity> findAllQuestionnaires(@Param("login") String login);

    @Query("select b.questionnaire from QuestionEntity b where b.id=:id")
    QuestionnaireEntity findByQuestionId(Long id);

    @Query("SELECT COUNT(distinct id) from UserAnswersEntity where answer.isRight = true and question.questionnaire.id=:id")
    Integer countUsersRightAnswersNumberOnQuestionnaire(@Param("id") Long id);

    @Query("select count(distinct id) from AnswerEntity where question.questionnaire.id=:questionnaire_id and isRight=true")
    Integer countTotalAnswersByQuestionnaireId(@Param("questionnaire_id") Long questionnaireId);

    @Query("select b.userAnswers from StatisticsEntity b where b.user.login=:username and b.questionnaire.id=:q_id")
    List<UserAnswersEntity> getAnswersOnQuestionnaireForUser(@Param("username") String username, @Param("q_id") Long qId);

}
