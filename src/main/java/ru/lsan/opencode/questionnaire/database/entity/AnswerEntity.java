package ru.lsan.opencode.questionnaire.database.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "answers")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "theme")
    private String theme;

    @Column(name = "is_right")
    private Boolean isRight;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @OneToMany(mappedBy = "answer", fetch=FetchType.LAZY)
    private List<UserAnswersEntity> userAnswer;

}
