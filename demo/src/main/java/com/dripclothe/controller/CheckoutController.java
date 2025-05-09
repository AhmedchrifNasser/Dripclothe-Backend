package com.dripclothe.controller;

import com.dripclothe.dto.PaymentInfo;
import com.dripclothe.dto.Purchase;
import com.dripclothe.dto.PurchaseResponse;
import com.dripclothe.model.Artiste;
import com.dripclothe.model.Token;
import com.dripclothe.service.CheckoutService;
import com.dripclothe.service.generateReceipt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lowagie.text.DocumentException;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import freemarker.template.TemplateException;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/backend/checkout")
public class CheckoutController {
    private CheckoutService checkoutService;
    private generateReceipt generateReceipt;

    public CheckoutController(CheckoutService checkoutService, com.dripclothe.service.generateReceipt generateReceipt,@Value("${stripe.key.secret}") String secretKey) {
        this.checkoutService = checkoutService;
        this.generateReceipt = generateReceipt;
    }

    @PostMapping("/purchase")
    public ResponseEntity<PurchaseResponse> placeOrder(@RequestBody Purchase purchase) {
        PurchaseResponse purchaseResponse = checkoutService.placeOrder(purchase);
        return new ResponseEntity<>(purchaseResponse, HttpStatus.OK);
    }

    @PostMapping("/purchase/receipt")
    public ResponseEntity<byte[]>  generatePDF(@RequestBody Purchase purchase) throws DocumentException, IOException {
        String html = this.generateReceipt.parseThymeleafTemplate(purchase);
        HttpHeaders headers = new HttpHeaders();
        ByteArrayOutputStream pdfStream = this.generateReceipt.generatePdfFromHtml(html);
        try{
            generateReceipt.sendEmail(purchase,pdfStream);
            generateReceipt.sendEmailTracking(purchase);
        } catch (IOException | TemplateException | MessagingException e){
            //message = message.concat(e.toString());
            generateReceipt.sendAdminEmail(purchase);
        }finally {
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=query_results.pdf");
            headers.setContentLength(pdfStream.size());
            return new ResponseEntity<>(pdfStream.toByteArray(), headers, HttpStatus.OK);
        }
    }

    @PostMapping("/payment-intent")
    public ResponseEntity<Token> createPaymentIntent(@RequestBody PaymentInfo paymentInfo) throws StripeException, JsonProcessingException {
        PaymentIntent paymentIntent = checkoutService.createPaymentIntent(paymentInfo);
        //String paymentStr = paymentIntent.toJson();
        return new ResponseEntity<Token>(new Token(paymentIntent.getClientSecret()), HttpStatus.OK);
    }

}
