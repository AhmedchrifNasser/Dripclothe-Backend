package com.dripclothe.service;

import com.dripclothe.config.MailConfig;
import com.dripclothe.dto.Purchase;
import com.lowagie.text.DocumentException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.util.HashMap;
import java.util.Map;

@Service
public class generateReceipt {

    private final MailConfig mailConfig;
    @Autowired
    @Qualifier("freeMarkerConfiguration")
    private final Configuration configuration;

    public generateReceipt(MailConfig mailConfig, @Qualifier("freeMarkerConfiguration") Configuration configuration) {
        this.mailConfig = mailConfig;
        this.configuration = configuration;
    }

    public ByteArrayOutputStream generatePdfFromHtml(String html) throws IOException, DocumentException {
        ByteArrayOutputStream receipt = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        String baseUrl = FileSystems
                .getDefault()
                .getPath("src", "main", "resources")
                .toUri()
                .toURL()
                .toString();
        renderer.setDocumentFromString(html, baseUrl);
        renderer.layout();
        renderer.createPDF(receipt);
        return receipt;
    }

    public String parseThymeleafTemplate(Purchase data){
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("HTML");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setOrder(1);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        Context context = new Context();
        context.setVariable("order", data);
        return templateEngine.process("receipt-template", context);
    }

    public void sendEmail(Purchase data, ByteArrayOutputStream pdf) throws IOException, TemplateException, MessagingException {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("order", data);
        InputStreamSource fileStreamSource = new ByteArrayResource(pdf.toByteArray());
        JavaMailSender sender = mailConfig.javaMailSender();
        MimeMessage message = sender.createMimeMessage();
        Template t = configuration.getTemplate("email-template.ftlh");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(data.getCustomer().getEmail());
        helper.setText(html, true);
        helper.setSubject("subject");
        helper.setFrom("support@dripclothe.com");
        helper.addAttachment("receipt",fileStreamSource,"application/pdf");
        sender.send(message);
    }

    public void sendEmailTracking(Purchase data) throws IOException, TemplateException, MessagingException {
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("order", data);
        JavaMailSender sender = mailConfig.javaMailSender();
        MimeMessage message = sender.createMimeMessage();
        Template t = configuration.getTemplate("email-tracking-template.ftlh");
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo(data.getCustomer().getEmail());
        helper.setText(html, true);
        helper.setSubject("subject");
        helper.setFrom("support@dripclothe.com");
        sender.send(message);
    }

    public void sendAdminEmail(Purchase purchase) throws MessagingException {
        JavaMailSender sender = mailConfig.javaMailSender();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo("ahmed.chrif.nasr@gmail.com");
        helper.setText("there was an error in order id ".concat(purchase.getOrder().getId().toString()
                .concat(purchase.getCustomer().getEmail())), false);
        helper.setSubject("error in sending email");
        helper.setFrom("support@dripclothe.com");
        sender.send(message);
    }

    public void sendAdminEmailErrorOrder(String orderNumber) throws MessagingException {
        JavaMailSender sender = mailConfig.javaMailSender();
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        helper.setTo("ahmed.chrif.nasr@gmail.com");
        helper.setText("there was an error in order number ".concat(orderNumber), false);
        helper.setSubject("error in changing status to paid for this order");
        helper.setFrom("support@dripclothe.com");
        sender.send(message);
    }
}

