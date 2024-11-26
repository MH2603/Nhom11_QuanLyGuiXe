package com.example.quanlyguixe.data.repo.local.ticket;


import com.example.quanlyguixe.data.model.Tickets;
import com.example.quanlyguixe.data.repo.ITicketDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class TicketLocalDataSource implements ITicketDataSource.Local {

    private final TicketDao ticketDao;

    @Inject
    public TicketLocalDataSource(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override
    public Single<List<Tickets>> getAllTickets() {
        return ticketDao.getAllTickets();
    }

    @Override
    public Single<Tickets> getTicketByID(int ticketID) {
        return ticketDao.getTicketByID(ticketID);
    }

    @Override
    public Single<Long> insertTicket(Tickets tickets) {
        return ticketDao.insertTicket(tickets);
    }

    @Override
    public Single<Integer> updateTicket(Tickets tickets) {
        return ticketDao.updateTicket(tickets);
    }

    @Override
    public Single<Integer> deleteTicket(Tickets tickets) {
        return ticketDao.deleteTicket(tickets);
    }
}
