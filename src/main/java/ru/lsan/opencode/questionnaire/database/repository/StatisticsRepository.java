package ru.lsan.opencode.questionnaire.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lsan.opencode.questionnaire.database.entity.StatisticsEntity;

import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<StatisticsEntity, Long> {

    @Query("select count(distinct id) from UserAnswersEntity where answer.isRight=true and statistics.id=:statistics_id")
    Integer countRightAnswersNumber(@Param("statistics_id") Long statisticsId);

    @Query("select b from StatisticsEntity b where b.user.login=:username and b.questionnaire.id=:id")
    StatisticsEntity findByUserLoginAndQuestionnaireId(@Param("username") String username, @Param("id") Long id);

    @Query("select b from StatisticsEntity b where b.questionnaire.id=:questionnaire_id order by b.rate DESC")
    List<StatisticsEntity> findStatisticsEntitiesByQuestionnaireId(@Param("questionnaire_id") Long questionnaireId);

    @Query("select b from StatisticsEntity b where b.questionnaire.id=:questionnaire_id and b.user.login=:username")
    StatisticsEntity findStatisticsEntitiesByQuestionnaireIdAndUserId(@Param("questionnaire_id") Long questionnaireId, @Param("username")  String username);

}
