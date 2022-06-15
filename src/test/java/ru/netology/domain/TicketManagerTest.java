package ru.netology.domain;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Comparator;


@Data

public class TicketManagerTest {
    protected Repository repository = new Repository();
    protected TicketManager manager = new TicketManager();
    protected Ticket ticket = new Ticket();

    public TicketManagerTest() {
        this.repository = getRepository();
        this.manager = getManager();
    }

    Ticket ticket1 = new Ticket(1, 5_000, "SVO", "LED", 95);
    Ticket ticket2 = new Ticket(2, 4_500, "DME", "LED", 115);
    Ticket ticket3 = new Ticket(3, 4_000, "SVO", "LED", 120);
    Ticket ticket4 = new Ticket(4, 6_000, "LED", "SVO", 95);
    Ticket ticket5 = new Ticket(5, 5_500, "SVO", "LED", 110);
    Ticket ticket6 = new Ticket(6, 3_000, "SVO", "LED", 150);
    Ticket newTicket = new Ticket(7, 10_000, "LED", "LED", 150);


    @BeforeEach
    public void setUp() {
        manager.addNewTicket(ticket1);
        manager.addNewTicket(ticket2);
        manager.addNewTicket(ticket3);
        manager.addNewTicket(ticket4);
        manager.addNewTicket(ticket5);
        manager.addNewTicket(ticket6);
    }

    @Test
    public void checkFindById() {
        Ticket[] actual = manager.findById(3);
        Ticket[] expected = new Ticket[]{ticket3};
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void checkFindByIdNotHave() {
        Ticket[] actual = manager.findById(11);
        Ticket[] expected = null;
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void checkFindAll() {
        Ticket[] actual = manager.findAll("SVO", "LED");
        Ticket[] expected = new Ticket[]{ticket6, ticket3, ticket1, ticket5};
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void checkNotFoundTicketsFrom() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAll("SVP", "LED"));
    }
    @Test
    public void checkNotFoundTicketsTo() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAll("SVO", "LEF"));
    }
    @Test
    public void checkNotFoundTicketsFromAndTo() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAll("SVP", "LEF"));
    }
    @Test
    public void checkNotFoundTicketsWithoutParameters() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAll(null, null));
    }
    @Test
    public void checkRemoveById() {
        manager.removedById(2);
        Ticket[] actual = manager.findAllTickets();
        Ticket[] expected = new Ticket[]{ticket1, ticket3, ticket4, ticket5, ticket6};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void checkNotFoundRemovedTickets() {
        Assertions.assertThrows(NotFoundByIdException.class, () -> manager.removedById(8));
    }
    @Test
    public void checkAddingNewTicket() {
        manager.addNewTicket(newTicket);
        Ticket[] actual = manager.findAllTickets();
        Ticket[] expected = new Ticket[]{ticket1, ticket2, ticket3, ticket4, ticket5, ticket6, newTicket};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void checkAlreadyExistException() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> manager.addNewTicket(ticket3));
    }

    @Test
    public void checkFindAllNew() {
        Ticket[] actual = manager.findAllNew("SVO", "LED", ticket1);
        Ticket[] expected = new Ticket[]{ticket1, ticket5, ticket3, ticket6};
        Assertions.assertArrayEquals(expected, actual);
    }
    @Test
    public void checkNotFoundTicketsByNewMethodFrom() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAllNew("SVP", "LED", ticket1));
    }
    @Test
    public void checkNotFoundTicketsByNewMethodTo() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAllNew("SVO", "LEF", ticket1));
    }
    @Test
    public void checkNotFoundTicketsFromAndByNewMethodTo() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAllNew("SVP", "LEF", ticket1));
    }
    @Test
    public void checkNotFoundTicketsByNewMethodWithoutParameters() {
        TicketManager manager = new TicketManager(repository);
        Assertions.assertThrows(NotFoundByAirportFromAndAirportToException.class, () -> manager.findAllNew(null, null, ticket1));
    }
}
