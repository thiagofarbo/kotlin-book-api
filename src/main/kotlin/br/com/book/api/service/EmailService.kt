package br.com.book.api.service

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

@Service
class EmailService(
    private val mailSender: JavaMailSender,
    private val templateEngine: TemplateEngine
) {
    //mailtrap.io
    fun send(to: String, subject: String, templateName: String, variables: Map<String, Any>) {
        val context = Context()
        context.setVariables(variables)

        val htmlContent = templateEngine.process(templateName, context)
        val message = mailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true)

        try {
            helper.setTo(to)
            helper.setSubject(subject)
            helper.setText(htmlContent, true) // 'true' habilita o modo HTML
            helper.setFrom("authmail@yopmail.com    ")

            mailSender.send(message)
            println("Email has been sent successfully.")
        } catch (e: Exception) {
            println("Error to send email: ${e.message}")
        }
    }
}