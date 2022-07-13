package ru.lsan.opencode.questionnaire.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum RoleEnum {
    ADMIN(Set.of(PermissionEnum.VIEW_GIVEN_ANSWERS,
            PermissionEnum.VIEW_SPECIAL_RATING,
            PermissionEnum.VIEW_QUESTIONNAIRES,
            PermissionEnum.ANSWER_QUESTIONNAIRES,
            PermissionEnum.VIEW_USER_RATING,
            PermissionEnum.VIEW_USER_LIST,
            PermissionEnum.CREATE_QUESTIONNAIRES
    )),
    USER(Set.of(PermissionEnum.VIEW_QUESTIONNAIRES,
            PermissionEnum.ANSWER_QUESTIONNAIRES,
            PermissionEnum.VIEW_USER_RATING
    ));

    private final Set<PermissionEnum> permissions;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }

}
