package com.school.eventdrivenproject.services;

import com.school.eventdrivenproject.constants.OrderStatusesEnum;
import com.school.eventdrivenproject.dtos.requests.CreateOrderRequest;
import com.school.eventdrivenproject.dtos.requests.CreatePackageRequest;
import com.school.eventdrivenproject.dtos.responses.BaseResponse;
import com.school.eventdrivenproject.entities.Order;
import com.school.eventdrivenproject.entities.OrderStatus;
import com.school.eventdrivenproject.repositories.IOrderRepository;
import com.school.eventdrivenproject.services.interfaces.*;
import com.school.eventdrivenproject.utils.PDFGenerator;
import com.school.eventdrivenproject.utils.TrackingIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderRepository repository;

    @Autowired
    private IOrderStatusService orderStatusService;

    @Autowired
    private IPackageService packageService;

    @Autowired
    private ISNSService snsService;

    @Autowired
    private IFileService fileService;

    @Override
    public Order create(CreateOrderRequest orderRequest, CreatePackageRequest packageRequest) throws IOException {
        Order order = from(orderRequest);

        order.setTrackingId(generateUniqueTrackingId());
        order.setOrderPackage(packageService.create(packageRequest));
        order.setStatus(getReceivedStatus());

        ByteArrayOutputStream pdfBytes = PDFGenerator.generate(order);
        String fileUrl = fileService.upload(pdfBytes);

        order.setOrderGuideUrl(fileUrl);

        snsService.subscribeEmail("arn:aws:sns:us-east-1:626350110357:PostmenNotifications", order.getAssociatedEmail());
        return repository.save(order);
    }

    @Override
    public BaseResponse list() {
        return null;
    }

    @Override
    public Order get(Long id) {
        return findOneAndEnsureExistsById(id);
    }

    @Override
    public Order updateStatusToInProgress(String trackingId) {
        Order order = setStatus(OrderStatusesEnum.IN_PROGRESS, trackingId);
        snsService.sendNotification(order, "arn:aws:sns:us-east-1:626350110357:PostmenNotifications");
        return repository.save(order);
    }

    @Override
    public Order updateStatusToDelivered(String trackingId) {
        Order order = setStatus(OrderStatusesEnum.DELIVERED, trackingId);
        snsService.sendNotification(order, "arn:aws:sns:us-east-1:626350110357:PostmenNotifications");
        return repository.save(order);
    }

    @Override
    public BaseResponse delete(Long id) {
        return null;
    }

    @Override
    public Order findOneAndEnsureExistsById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Override
    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("asd"));
    }

    private Order from(CreateOrderRequest request){
        Order order = new Order();
        order.setAddress(request.getAddress());
        order.setCity(request.getCity());
        order.setState(request.getState());
        order.setPostalCode(request.getPostalCode());
        order.setShipTo(request.getShipTo());
        order.setAssociatedEmail(request.getAssociatedEmail());

        OrderStatus status = orderStatusService.findOneByName("RECEIVED");
        order.setStatus(status);
        return order;
    }

    private Order setStatus(OrderStatusesEnum orderStatusEnum, String trackingId){
        Order order = repository.findOneByTrackingId(trackingId);
        String newStatus = orderStatusEnum.getOrderStatusEnum();
        order.setStatus(orderStatusService.findOneByNameOrCreate(newStatus));
        return order;
    }

    private String generateUniqueTrackingId(){
        String trackingId;
        do {
            trackingId = TrackingIdGenerator.generateTrackingId();
        } while (repository.existsOrderByTrackingId(trackingId));
        return trackingId;
    }

    private OrderStatus getReceivedStatus() {
        return orderStatusService.findOneByNameOrCreate(OrderStatusesEnum.RECEIVED.getOrderStatusEnum());
    }
}
