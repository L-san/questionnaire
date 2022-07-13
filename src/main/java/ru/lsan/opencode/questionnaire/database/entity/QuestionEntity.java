package ru.lsan.opencode.questionnaire.database.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.lsan.opencode.questionnaire.dto.QuestionDTO;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "theme")
    private String theme;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<AnswerEntity> answers;

    @Column(name="right_answers_number")
    private Integer rightAnswersNumber;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private QuestionnaireEntity questionnaire;

    @OneToMany(mappedBy = "question")
    private List<UserAnswersEntity> userAnswer;

}
