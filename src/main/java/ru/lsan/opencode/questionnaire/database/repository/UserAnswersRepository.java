package ru.lsan.opencode.questionnaire.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.lsan.opencode.questionnaire.database.entity.UserAnswersEntity;

@Repository
public interface UserAnswersRepository extends JpaRepository<UserAnswersEntity, Long> {
}
