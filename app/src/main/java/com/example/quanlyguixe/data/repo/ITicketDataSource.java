package com.example.quanlyguixe.data.repo;


import com.example.quanlyguixe.data.model.Tickets;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface ITicketDataSource {
    interface Local {
        Single<List<Tickets>> getAllTickets();

        Single<Tickets> getTicketByID(int ticketID);

        Single<Long> insertTicket(Tickets tickets);

        Single<Integer> updateTicket(Tickets tickets);

        Single<Integer> deleteTicket(Tickets tickets);
    }
}
