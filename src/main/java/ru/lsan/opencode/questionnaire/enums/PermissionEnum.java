package ru.lsan.opencode.questionnaire.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PermissionEnum {
    VIEW_GIVEN_ANSWERS("VIEW_GIVEN_ANSWERS"),
    VIEW_SPECIAL_RATING("VIEW_SPECIAL_RATING"),
    VIEW_USER_LIST("VIEW_USER_LIST"),
    VIEW_QUESTIONNAIRES("VIEW_QUESTIONNAIRES"),
    ANSWER_QUESTIONNAIRES("ANSWER_QUESTIONNAIRES"),
    CREATE_QUESTIONNAIRES("CREATE_QUESTIONNAIRES"),
    VIEW_USER_RATING("VIEW_USER_RATING");

    private final String permission;

}
