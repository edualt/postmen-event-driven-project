package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.entities.Order;

public interface ISNSService {

    void subscribeEmail(String topicArn, String email);

    void sendNotification(Order order, String topicArn);

}
