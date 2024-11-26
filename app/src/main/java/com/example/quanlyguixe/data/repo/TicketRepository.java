package com.example.quanlyguixe.data.repo;


import com.example.quanlyguixe.data.model.Tickets;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class TicketRepository implements ITicketDataSource.Local {

    private final ITicketDataSource.Local local;

    @Inject
    public TicketRepository(ITicketDataSource.Local local) {
        this.local = local;
    }

    @Override
    public Single<List<Tickets>> getAllTickets() {
        return local.getAllTickets();
    }

    @Override
    public Single<Tickets> getTicketByID(int ticketID) {
        return local.getTicketByID(ticketID);
    }

    @Override
    public Single<Long> insertTicket(Tickets tickets) {
        return local.insertTicket(tickets);
    }

    @Override
    public Single<Integer> updateTicket(Tickets tickets) {
        return local.updateTicket(tickets);
    }

    @Override
    public Single<Integer> deleteTicket(Tickets tickets) {
        return local.deleteTicket(tickets);
    }
}
