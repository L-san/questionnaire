package ru.lsan.opencode.questionnaire.database.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "users_answers")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class UserAnswersEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private AnswerEntity answer;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne
    @JoinColumn(name = "statistics_id")
    private StatisticsEntity statistics;

}
