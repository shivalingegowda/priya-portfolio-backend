
package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*")  // Allow all origins (like GitHub Pages)
@RestController
@RequestMapping("/api/contact")
public class ContactFormController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public String submitContactForm(@RequestBody ContactForm contactForm) {
        // Compose the email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("shivalinge038@gmail.com"); // Priya's inbox
        message.setSubject("New Contact Form Message from " + contactForm.getName());
        message.setText(
            "Name: " + contactForm.getName() + "\n" +
            "Email: " + contactForm.getEmail() + "\n" +
            "Phone: " + contactForm.getPhone() + "\n" +
            "Message: " + contactForm.getMessage()
        );

        // Send the email
        mailSender.send(message);

        return "Thank you for your message, " + contactForm.getName() + "! We've emailed your message to Priya.";
    }
}
