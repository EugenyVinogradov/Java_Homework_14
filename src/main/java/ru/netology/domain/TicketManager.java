package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor

public class TicketManager {
    protected Repository repository = new Repository();

    public Ticket[] findAllTickets() {
        return repository.findAllTickets();
    }

    public Ticket[] findAll(String from, String to) {
        if (repository.findAll(from, to) == null) {
            throw new NotFoundByAirportFromAndAirportToException("Elements with AirportFrom: " + from + "and AirportTo: " + to + " not found");
        }
        return repository.findAll(from, to);
    }

    public void addNewTicket(Ticket newTicket) {
        if (findById(newTicket.getId()) == null) {
            repository.save(newTicket);
        } else {
            throw new AlreadyExistsException("Ticket with id: " + newTicket.getId() + " already have");
        }
    }

    public Ticket[] findById(int id) {
        Ticket[] tmp = new Ticket[1];
        for (Ticket ticket : repository.findAllTickets()) {
            if (id == ticket.getId()) {
                tmp[0] = ticket;
                return tmp;
            }
        }
        return null;
    }

    public void removedById(int id) {
        if (findById(id) != null) {
            Ticket[] tmp = new Ticket[repository.tickets.length - 1];
            int index = 0;
            for (Ticket ticket : repository.findAllTickets()) {
                if (id != ticket.getId()) {
                    tmp[index] = ticket;
                    index++;
                }
                repository.tickets = tmp;
            }
        } else {
            throw new NotFoundByIdException("Element with id: " + id + " not found");

        }
    }

    public Ticket[] findAllNew(String from, String to, Comparator<Ticket> comparator) {
        if (repository.findAllNew(from, to, comparator) == null) {
            throw new NotFoundByAirportFromAndAirportToException("Elements with AirportFrom: " + from + "and AirportTo: " + to + " not found");
        }
        return repository.findAllNew(from, to, comparator);
    }

}
