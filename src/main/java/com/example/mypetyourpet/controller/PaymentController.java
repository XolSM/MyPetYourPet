package com.example.mypetyourpet.controller;

import com.example.mypetyourpet.dto.TransactionRequest;
import com.example.mypetyourpet.model.TransactionType;
import com.stripe.Stripe;
import com.example.mypetyourpet.dto.CheckoutRequest;
import com.example.mypetyourpet.service.ReservationService;
import com.example.mypetyourpet.service.TransactionService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")

public class PaymentController {

    private final String successUrl;
    private final String cancelUrl;
    private final ReservationService reservationService;
    private final TransactionService transactionService;

    public PaymentController(
            @Value("${stripe.secret-key}") String secretKey,
            @Value("${stripe.success-url}") String successUrl,
            @Value("${stripe.cancel-url}") String cancelUrl, ReservationService reservationService, TransactionService transactionService
    ) {
        this.reservationService = reservationService;
        this.transactionService = transactionService;
        Stripe.apiKey = secretKey;
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
    }

    @PostMapping("/create-checkout-session")
    public ResponseEntity<Map<String, String>> createCheckoutSession(
            @RequestBody CheckoutRequest request) throws StripeException {

        long amount = request.getAmountCents() != null ? request.getAmountCents() : 2000L;
        String description = request.getDescription() != null ? request.getDescription() : "Pet reservation";

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .setSuccessUrl(successUrl)
                        .setCancelUrl(cancelUrl)
                        .putMetadata("reservationId", request.getReservationId().toString())
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setQuantity(1L)
                                        .setPriceData(
                                                SessionCreateParams.LineItem.PriceData.builder()
                                                        .setCurrency("cad")
                                                        .setUnitAmount(amount)
                                                        .setProductData(
                                                                SessionCreateParams.LineItem.PriceData.ProductData
                                                                        .builder()
                                                                        .setName(description)
                                                                        .build()
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();

        Session session = Session.create(params);

        Map<String, String> responseData = new HashMap<>();
        responseData.put("url", session.getUrl());

        return ResponseEntity.ok(responseData);
    }

    @PostMapping("/confirm-payment")
    public ResponseEntity<String> confirmPayment(@RequestParam("session_id") String sessionId) throws StripeException {

        Session session = Session.retrieve(sessionId);

        boolean isPaid =
                "paid".equals(session.getPaymentStatus()) ||
                        "complete".equals(session.getStatus());

        if (isPaid) {

            int reservationId = Integer.valueOf(session.getMetadata().get("reservationId"));
            Long amountPaid = session.getAmountTotal();

            reservationService.updateReservationStatus(reservationId, "Confirmed - Paid");

            //create transaction object
            int customerId = reservationService.getCustomerId(reservationId);

            TransactionRequest tr = new TransactionRequest();
            tr.setPetResId(reservationId);
            tr.setCustomerId(customerId);
            tr.setClaimId(0);
            tr.setAmount(amountPaid);

            // enum values → send as string
            tr.setTransactionType(TransactionType.SEEKER_TO_US.name());
            tr.setPaymentMethod("STRIPE");

            tr.setNote("Stripe payment for reservation " + reservationId);

            transactionService.createTransaction(tr);
            // transactionService.createTransaction(...);

            return ResponseEntity.ok("Payment confirmed");
        }

        return ResponseEntity.badRequest().body("Payment not completed");
    }
}
