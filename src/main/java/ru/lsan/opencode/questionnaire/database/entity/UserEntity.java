package ru.lsan.opencode.questionnaire.database.entity;

import lombok.*;
import ru.lsan.opencode.questionnaire.enums.RoleEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder(toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<StatisticsEntity> statistics;

}
