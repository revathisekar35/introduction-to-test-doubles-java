package com.techreturners.bubbleteaordersystem.service;

import com.techreturners.bubbleteaordersystem.model.BubbleTeaOrderRequest;
import com.techreturners.bubbleteaordersystem.model.SimpleLogger;

public class BubbleTeaMessenger {

    private SimpleLogger simpleLogger;

    public BubbleTeaMessenger(SimpleLogger simpleLogger) {
        this.simpleLogger = simpleLogger;
    }

    public void sendBubbleTeaOrderRequestEmail(BubbleTeaOrderRequest bubbleTeaOrderRequest) {
        //Do something fancy here/use another service/method to send an email over SMTP

        simpleLogger.addLoggingText("Yay! I just sent the Order Request email for the " +
                "following Bubble Tea Order Request " + bubbleTeaOrderRequest.toString());
    }
    
}
