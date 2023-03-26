package com.school.eventdrivenproject.utils;

import java.util.UUID;

public class TrackingIdGenerator {

    public static String generateTrackingId(){
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        String trackingId = "PM" + randomUUIDString.replaceAll("-", "").substring(0, 12);
        return trackingId.toUpperCase();
    }

}
