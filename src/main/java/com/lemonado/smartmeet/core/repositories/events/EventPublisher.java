package com.lemonado.smartmeet.core.repositories.events;

import com.lemonado.smartmeet.core.data.events.EventData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Aspect
@Service
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    public <T> void publishEvent(EventData<T> eventData) {
        publisher.publishEvent(eventData);
    }

    public <T> void onNew(T data) {
        publishEvent(EventData.news(data));
    }

    public <T> void onUpdate(T data) {
        publishEvent(EventData.update(data));
    }

    public <T> void onDelete(T data) {
        publishEvent(EventData.delete(data));
    }


    @Around("@annotation(OnNewEventListening)")
    public Object onNewData(ProceedingJoinPoint joinPoint) throws Throwable {
        return onEvent(joinPoint, this::onNew);
    }

    @Around("@annotation(OnUpdateEventListening)")
    public Object onUpdateData(ProceedingJoinPoint joinPoint) throws Throwable {
        return onEvent(joinPoint, this::onUpdate);
    }

    @Around("@annotation(OnDeleteEventListening)")
    public Object onDeleteData(ProceedingJoinPoint joinPoint) throws Throwable {
        return onEvent(joinPoint, this::onDelete);
    }

    private Object onEvent(ProceedingJoinPoint joinPoint, Consumer<Object> consumer) throws Throwable {
        var data = joinPoint.proceed();
        consumer.accept(data);
        return data;
    }



}
