package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Ticket implements Comparable<Ticket>{
    protected int id;
    protected int cost;
    protected String airportFrom;
    protected String airportTo;
    protected int flightTime;

    @Override
    public int compareTo(Ticket o) {
        return cost - o.cost;
    }
}
