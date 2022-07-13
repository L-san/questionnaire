package ru.lsan.opencode.questionnaire.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lsan.opencode.questionnaire.database.entity.AnswerEntity;

import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {

    Optional<AnswerEntity> findById(Long id);

}
