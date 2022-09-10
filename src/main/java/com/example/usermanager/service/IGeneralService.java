package com.example.usermanager.service;

import java.util.Optional;

public interface IGeneralService <T>{

    Iterable<T> findAll();

    T save(T t);

    Optional<T> findById(Long id);

    void remove(Long id);

}
