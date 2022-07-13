package ru.lsan.opencode.questionnaire.database.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Table(name = "statistics")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class StatisticsEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "attempt")
    @Max(2)
    private Integer attempt;

    @Column(name = "rate")
    private Integer rate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "questionnaire_id")
    private QuestionnaireEntity questionnaire;

    @OneToMany(mappedBy = "statistics", fetch = FetchType.LAZY)
    private List<UserAnswersEntity> userAnswers;

}
