package com.lemonado.smartmeet.core.services.impl;

import com.lemonado.smartmeet.core.data.events.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public <T> void publishEvent(Event<T> event) {
        publisher.publishEvent(event);
    }

    public <T> void onNew(T data) {
        publishEvent(Event.news(data));
    }

    public <T> void onUpdate(T data) {
        publishEvent(Event.update(data));
    }

    public <T> void onDelete(T data) {
        publishEvent(Event.delete(data));
    }






}
