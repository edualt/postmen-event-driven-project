package com.school.eventdrivenproject.services.interfaces;

import com.school.eventdrivenproject.entities.Order;
import jakarta.servlet.http.HttpServletResponse;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface IPDFGenService {

    ByteArrayOutputStream generate(Order order);
}

