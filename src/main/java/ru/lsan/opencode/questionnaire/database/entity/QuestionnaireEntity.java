package ru.lsan.opencode.questionnaire.database.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import ru.lsan.opencode.questionnaire.dto.QuestionDTO;
import ru.lsan.opencode.questionnaire.dto.QuestionnaireDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questionnaire")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class QuestionnaireEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "theme")
    private String theme;

    @OneToMany(mappedBy = "questionnaire", fetch = FetchType.EAGER)
    private Set<QuestionEntity> questions;

    @OneToMany(mappedBy = "questionnaire", fetch = FetchType.EAGER)
    private Set<StatisticsEntity> statistics;

}
