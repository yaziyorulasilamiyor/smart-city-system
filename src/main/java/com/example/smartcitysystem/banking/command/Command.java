package com.example.smartcitysystem.banking.command;

public interface Command<T> {
    T execute();
}
