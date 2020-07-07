package com.readapp.backend.utils.interfaces;

public interface Transformable<T, TD> {
    T transform(TD td);
}
