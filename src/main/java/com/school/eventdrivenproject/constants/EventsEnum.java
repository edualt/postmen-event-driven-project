package com.school.eventdrivenproject.constants;

public enum EventsEnum {

    CREATE_ORDER("CREATE_ORDER"),
    START_DELIVERY("START_DELIVERY"),
    FINISH_DELIVERY("FINISH_DELIVERY");

    private String eventEnum = "";

    EventsEnum(String event) {
        this.eventEnum = event;
    }

    public String getEventEnum() {
        return eventEnum;
    }
}
