package ru.lsan.opencode.questionnaire.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.lsan.opencode.questionnaire.database.entity.QuestionEntity;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    Optional<QuestionEntity> findById(Long id);

    @Query("SELECT COUNT(distinct id) from AnswerEntity where isRight = true and question.id=:id")
    Integer countRightAnswersNumber(@Param("id") Long id);

}
