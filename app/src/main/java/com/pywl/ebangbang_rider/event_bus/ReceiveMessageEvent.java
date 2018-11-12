package com.pywl.ebangbang_rider.event_bus;

/**
 * Created by Harry on 2018/11/12.
 */
public class ReceiveMessageEvent {

    private String message;

    public ReceiveMessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
