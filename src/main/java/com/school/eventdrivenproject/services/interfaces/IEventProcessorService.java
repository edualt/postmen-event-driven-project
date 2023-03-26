package com.school.eventdrivenproject.services.interfaces;

import java.io.IOException;

public interface IEventProcessorService {

    void processEvent(Object event) throws IOException;

}
