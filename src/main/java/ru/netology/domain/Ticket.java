package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Ticket implements Comparable<Ticket>, Comparator<Ticket> {
    protected int id;
    protected int cost;
    protected String airportFrom;
    protected String airportTo;
    protected int flightTime;

    @Override
    public int compareTo(Ticket o) {
        return cost - o.cost;
    }

    @Override
    public int compare(Ticket o1, Ticket o2) {
        return o1.getFlightTime() - o2.getFlightTime();
    }
}
