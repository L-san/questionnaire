package ru.lsan.opencode.questionnaire.api;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

class AuthViewControllerTest {

    private static final Base64.Encoder base64Encoder = Base64.getEncoder();
    private static final Base64.Decoder base64Decoder = Base64.getDecoder();

    @Test
    void register() {
        String ref = "abcd";
        String link = base64Encoder.encodeToString(ref.getBytes(StandardCharsets.UTF_8));
        String link2 = String.valueOf(base64Decoder.decode(link));
        System.out.println(link);
        System.out.println(link2);
    }
}