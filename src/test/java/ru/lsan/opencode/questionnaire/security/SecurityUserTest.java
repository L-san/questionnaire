package ru.lsan.opencode.questionnaire.security;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;


class SecurityUserTest {

    /*@Autowired
    private UserService userService;

    @Test
    void fromUser() {
        UserEntity user = userService.findByLogin("admin@app.com");
        assertNotNull(userService.findByLogin("admin@app.com"));
    }
*/
    @Test
    void collTest(){
        LinkedHashSet<Integer> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add(1);  linkedHashSet.add(2); linkedHashSet.add(3);
        System.out.println(linkedHashSet.iterator().next());
        System.out.println(linkedHashSet.iterator().next());
        System.out.println(linkedHashSet.iterator().next());
    }

}