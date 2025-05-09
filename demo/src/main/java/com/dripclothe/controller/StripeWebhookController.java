package com.dripclothe.controller;

import com.dripclothe.service.CheckoutService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.model.*;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StripeWebhookController {
    private final CheckoutService checkoutService;

    @Value("${stripeApiKey}")
    String endpointSecret = "";

    public StripeWebhookController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/backend/webhook")
    public ResponseEntity<String> handleStripeEvent(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        Event event;

        try {
            event = Webhook.constructEvent(
                    payload, sigHeader, endpointSecret
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Webhook error: " + e.getMessage());
        }

        // Handle the event
        if ("charge.succeeded".equals(event.getType())) {
            EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
            StripeObject stripeObject = null;
            if (dataObjectDeserializer.getObject().isPresent()) {
                stripeObject = dataObjectDeserializer.getObject().get();
                ObjectMapper m = new ObjectMapper();

                @SuppressWarnings("unchecked")
                Map<String, Object> objectMapper = m.convertValue(stripeObject, Map.class);

                Object metadata = objectMapper.get("metadata");

                @SuppressWarnings("unchecked")
                Map<String, String> objectMapper1 = m.convertValue(metadata, Map.class);

                String order_Number = objectMapper1.get("order_Number");

                checkoutService.markOrderAsPaid(order_Number);
            } else {
                // Deserialization failed, probably due to an API version mismatch.
            }
        }
        return ResponseEntity.ok("");
    }
}
