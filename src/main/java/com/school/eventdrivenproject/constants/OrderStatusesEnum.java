package com.school.eventdrivenproject.constants;

public enum OrderStatusesEnum {

    RECEIVED("RECEIVED"),

    IN_PROGRESS("IN_PROGRESS"),

    DELIVERED("DELIVERED");

    private String orderStatusEnum = "";
    static public final OrderStatusesEnum[] values = values();

    OrderStatusesEnum(String orderStatus) {
        this.orderStatusEnum = orderStatus;
    }

    public String getOrderStatusEnum() {
        return orderStatusEnum;
    }
}
