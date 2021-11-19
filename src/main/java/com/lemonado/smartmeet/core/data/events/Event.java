package com.lemonado.smartmeet.core.data.events;

public record  Event<T>(
        T data,
        EventType type){

    public static <T> Event<T> news(T data) {
        return new Event<>(data, EventType.NEW);
    }

    public static <T> Event<T> update(T data) {
        return new Event<>(data, EventType.UPDATE);
    }

    public static <T> Event<T> delete(T data) {
        return new Event<>(data, EventType.DELETE);
    }

}
