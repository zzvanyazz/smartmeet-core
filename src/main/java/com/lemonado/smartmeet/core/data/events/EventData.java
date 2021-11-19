package com.lemonado.smartmeet.core.data.events;

public record EventData<T>(
        T data,
        EventType type){

    public static <T> EventData<T> news(T data) {
        return new EventData<>(data, EventType.NEW);
    }

    public static <T> EventData<T> update(T data) {
        return new EventData<>(data, EventType.UPDATE);
    }

    public static <T> EventData<T> delete(T data) {
        return new EventData<>(data, EventType.DELETE);
    }

}
