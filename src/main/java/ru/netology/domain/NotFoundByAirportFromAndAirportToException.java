package ru.netology.domain;

public class NotFoundByAirportFromAndAirportToException extends RuntimeException {

    public NotFoundByAirportFromAndAirportToException(String mes) {
        super(mes);
    }

    public NotFoundByAirportFromAndAirportToException() {
        super();
    }
}
