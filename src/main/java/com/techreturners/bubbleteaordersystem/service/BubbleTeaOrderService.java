package com.techreturners.bubbleteaordersystem.service;

import com.techreturners.bubbleteaordersystem.model.BubbleTeaOrderRequest;
import com.techreturners.bubbleteaordersystem.model.BubbleTeaRequest;
import com.techreturners.bubbleteaordersystem.model.SimpleLogger;

public class BubbleTeaOrderService {

    private SimpleLogger simpleLogger;
    private BubbleTeaMessenger bubbleTeaMessenger;

    public BubbleTeaOrderService(SimpleLogger simpleLogger, BubbleTeaMessenger bubbleTeaMessenger) {
        this.simpleLogger = simpleLogger;
        this.bubbleTeaMessenger = bubbleTeaMessenger;
    }

    public BubbleTeaOrderRequest createOrderRequest(BubbleTeaRequest bubbleTeaRequest) {

        simpleLogger.addLoggingText("Creating an Order Request for the " +
                "following Bubble Tea Request " + bubbleTeaRequest.toString());

        var orderRequest = new BubbleTeaOrderRequest(
                bubbleTeaRequest.getPaymentDetails().getName(),
                bubbleTeaRequest.getPaymentDetails().getAddress(),
                bubbleTeaRequest.getPaymentDetails().getDebitCard().getDigits(),
                bubbleTeaRequest.getBubbleTea().getBubbleTeaType()
        );

        simpleLogger.addLoggingText("The following Bubble Tea Order Request has been created "
                + orderRequest.toString());

        sendEmail(orderRequest);

        return orderRequest;

    }

    private void sendEmail(BubbleTeaOrderRequest bubbleTeaOrderRequest) {
        bubbleTeaMessenger.sendBubbleTeaOrderRequestEmail(bubbleTeaOrderRequest);
    }

}
