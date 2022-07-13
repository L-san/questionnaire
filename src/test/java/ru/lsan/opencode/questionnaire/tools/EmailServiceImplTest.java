package ru.lsan.opencode.questionnaire.tools;

class EmailServiceImplTest {

    private EmailService emailService;

    @Deprecated
    void sendSimpleMessage() {
        emailService.sendSimpleMessage("mail2@gmail.com","text","text");
    }
}