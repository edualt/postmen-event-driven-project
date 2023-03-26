package com.school.eventdrivenproject.services;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.services.interfaces.ISNSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SNSServiceImpl implements ISNSService {

    @Autowired
    private AmazonSNSClient amazonSNSClient;

    @Override
    public void subscribeEmail(String topicArn, String email) {
        String filterPolicy = "{\"associatedEmail\":[\"equals\",\"" + email + "\"]}";
        SubscribeRequest request = new SubscribeRequest(topicArn, "email", email)
                .withProtocol("email")
                .withReturnSubscriptionArn(true)
                .withAttributes(Collections.singletonMap("FilterPolicy", filterPolicy));
        amazonSNSClient.subscribe(request);
    }

    @Override
    public void sendNotification(Order order, String topicArn) {
        String message = "";
        String orderStatus = order.getStatus().getName();
        System.out.println(orderStatus);

        switch (orderStatus){
            case "IN_PROGRESS":
                message = "Tu paquete " + order.getTrackingId() + " ha empezado su recorrido hacia su destino.";
                break;
            case "DELIVERED":
                message = "Tu paquete " + order.getTrackingId() + " ha sido entregado directamente a " + order.getShipTo();
                break;
        }

        System.out.println(message);

        PublishRequest publishRequest = new PublishRequest()
                .withTopicArn(topicArn)
                .withMessage(message)
                .withSubject("Actualizacion paquete " + order.getTrackingId())
                .withMessageAttributes(generateEmailAttribute(order.getAssociatedEmail()));
        amazonSNSClient.publish(publishRequest);
    }

    private Map<String, MessageAttributeValue> generateEmailAttribute(String email){
        Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
        messageAttributes.put("associatedEmail", new MessageAttributeValue().withDataType("String").withStringValue(String.valueOf(email)));
        return messageAttributes;
    }

}
