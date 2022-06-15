package ru.netology.domain;

public class NotFoundByIdException extends RuntimeException {

    public NotFoundByIdException(String mes) {
        super(mes);
    }

    public NotFoundByIdException() {
        super();
    }
}
