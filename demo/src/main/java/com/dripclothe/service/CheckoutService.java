package com.dripclothe.service;

import com.dripclothe.dto.PaymentInfo;
import com.dripclothe.dto.Purchase;
import com.dripclothe.dto.PurchaseResponse;
import com.dripclothe.model.*;
import com.dripclothe.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutService {

    private CustomerRepo customerRepo;
    private AddrressRepo addrressRepo;
    private OrderRepo orderRepo;
    private generateReceipt generateReceipt;
    private SizeRepo sizeRepo;
    private ColorRepo colorRepo;
    private ProductManagementRepo productManagementRepo;


    public CheckoutService(CustomerRepo customerRepo, AddrressRepo addrressRepo, OrderRepo orderRepo, com.dripclothe.service.generateReceipt generateReceipt, ProductRepo productRepo, SizeRepo sizeRepo, ColorRepo colorRepo, @Value("${stripe.key.secret}") String secretKey, ProductManagementRepo productManagementRepo) {
        this.customerRepo = customerRepo;
        this.addrressRepo = addrressRepo;
        this.orderRepo = orderRepo;
        this.generateReceipt = generateReceipt;
        this.sizeRepo = sizeRepo;
        this.colorRepo = colorRepo;
        this.productManagementRepo = productManagementRepo;
        Stripe.apiKey = secretKey;
    }


    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {
        Order order = purchase.getOrder();
        order.setStatus("created");
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        order.setCustomer(purchase.getCustomer());
        Optional<Customer> existingCustomer = customerRepo.findAll().stream().filter(x -> x.getEmail().equals(purchase.getCustomer().getEmail()))
                .filter(x -> x.getFirstName().equals(purchase.getCustomer().getFirstName()))
                .filter(x -> x.getLastName().equals(purchase.getCustomer().getLastName()))
                .findFirst();

        existingCustomer.ifPresent(c -> order.setCustomer(c));

        order.setShippingAddress(purchase.getShippingAddress());
        Optional<Address> existingAddress = addrressRepo.findAll().stream().filter(x -> x.getCity().equals(purchase.getShippingAddress().getCity())).filter(x -> x.getCountry().equals(purchase.getShippingAddress().getCountry())).filter(x -> x.getStreet().equals(purchase.getShippingAddress().getStreet())).filter(x -> x.getZipCode().equals(purchase.getShippingAddress().getZipCode())).findFirst();

        existingAddress.ifPresent(c -> order.setShippingAddress(c));

        orderRepo.save(order);

        return new PurchaseResponse(orderTrackingNumber);
    }

    public PaymentIntent createPaymentIntent(PaymentInfo paymentInfo) throws StripeException{
        
        String description1 = "";
        int i = 0;
        for (OrderItem orderItem : paymentInfo.getOrderItems()) {
            i++;
            ProductManagement productManagement =productManagementRepo.findByProduct_Id(orderItem.getProductId());
            description1 = description1.concat("Product "+ i + " id : " + orderItem.getProductId().toString()+ "\n");
            description1 = description1.concat("Product "+ i + " Size : " + orderItem.getSize() + "\n");
            description1 = description1.concat("Product "+ i + "Color : "+ orderItem.getColor() + "\n");
            description1 = description1.concat("Product "+ i + " Origin : "+ productManagement.getOrigin() + "\n");
        }

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(paymentInfo.getAmount().longValue())  // amount in the smallest unit, e.g., cents
                .setCurrency(paymentInfo.getCurrency())
                .addPaymentMethodType("card")
                .addPaymentMethodType("paypal")
                .putMetadata("order_Number", paymentInfo.getOrderNumber())
                .putMetadata("Last Name", paymentInfo.getCustomer().getLastName())
                .putMetadata("First Name", paymentInfo.getCustomer().getFirstName())
                .putMetadata("Email", paymentInfo.getCustomer().getEmail())
                .putMetadata("Country", paymentInfo.getAddress().getCountry())
                .putMetadata("City", paymentInfo.getAddress().getCity())
                .putMetadata("Street", paymentInfo.getAddress().getStreet())
                .putMetadata("ZipCode", paymentInfo.getAddress().getZipCode())
                .setDescription(description1)
                .build();

        return PaymentIntent.create(params);
    }

    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }

    public void markOrderAsPaid(String orderTrackingNumber) {
        // Fetch the order by its ID
        Order order = orderRepo.findByorderTrackingNumber(orderTrackingNumber).orElseThrow(() -> {
            try {
                this.generateReceipt.sendAdminEmailErrorOrder(orderTrackingNumber);
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            return new RuntimeException("Order not found");
        });
        order.setStatus("Paid");
        orderRepo.save(order);
    }
}
/*List<String> paymentMethodTypes = new ArrayList<>();
        paymentMethodTypes.add("card");
        paymentMethodTypes.add("paypal");
        Map<String, Object> params = new HashMap<>();
        params.put("payment_method_types", paymentMethodTypes);
        Map<String, Object> description = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        params.put("amount", paymentInfo.getAmount());
        params.put("currency", paymentInfo.getCurrency());
        params.put("customer_email", paymentInfo.getCustomer().getEmail());

        Map<String, String> metadata = new HashMap<>();
        metadata.put("order_Number", paymentInfo.getOrderNumber());
        params.put("metadata", metadata);

        description.put("Last Name", paymentInfo.getCustomer().getLastName());
        description.put("First Name", paymentInfo.getCustomer().getFirstName());
        description.put("Country", paymentInfo.getAddress().getCountry());
        description.put("City", paymentInfo.getAddress().getCity());
        description.put("Street", paymentInfo.getAddress().getStreet());
        description.put("ZipCode", paymentInfo.getAddress().getZipCode());

        for (OrderItem orderItem : paymentInfo.getOrderItems()) {
            Product product = productRepo.findById(orderItem.getProductId()).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + orderItem.getProductId()));
            Size size = sizeRepo.findById(orderItem.getSizeId()).orElseThrow(() -> new EntityNotFoundException("Size not found with ID: " + orderItem.getSizeId()));
            HashSet<Size> sizes = new HashSet<>();
            sizes.add(size);
            product.setSizes(sizes);
            Color color = colorRepo.findById(orderItem.getColorId()).orElseThrow(() -> new EntityNotFoundException("Color not found with ID: " + orderItem.getColorId()));
            HashSet<Color> colors = new HashSet<>();
            colors.add(color);
            product.setColors(colors);
            String jsonProduct = objectMapper.writeValueAsString(product);
            //description.put("Product", jsonProduct);
        }

        String description1 = objectMapper.writeValueAsString(description);
        params.put("description", description1);
         */