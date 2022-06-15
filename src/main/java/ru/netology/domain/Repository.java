package ru.netology.domain;


import lombok.NoArgsConstructor;

import java.util.Arrays;

@NoArgsConstructor


public class Repository {
    protected Ticket[] tickets = new Ticket[0];

    public Ticket[] findAllTickets() {
        return tickets;
    }

    public void save(Ticket newTicket) {
        int length = tickets.length + 1;
        Ticket[] tmp = new Ticket[length];
        for (int i = 0; i < tickets.length; i++) {
            tmp[i] = tickets[i];
        }
        int lastIndex = tmp.length - 1;
        tmp[lastIndex] = newTicket;
        tickets = tmp;
    }

    public Ticket[] findAll(String from, String to) {
        Ticket[] result = new Ticket[0];
        for (Ticket ticket : tickets) {
            if (ticket.getAirportFrom() == from && ticket.getAirportTo() == to) {
                Ticket[] tmp = new Ticket[result.length + 1];
                for (int i = 0; i < result.length; i++) {
                    tmp[i] = result[i];
                }
                tmp[tmp.length - 1] = ticket;
                Arrays.sort(tmp);
                result = tmp;
            }
        }
        if (result.length == 0) {
            return null;
        }
        return result;
    }

}
