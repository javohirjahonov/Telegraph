package com.example.telegraphback.service;

import java.util.UUID;

public interface BaseService<T, E> {
    T add(E e);

    T update(E e, UUID id);

    void delete(UUID id);

    void getById(UUID id);
}
