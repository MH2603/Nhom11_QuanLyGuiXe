package com.example.quanlyguixe.data.repo.local.ticket;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.quanlyguixe.data.model.Tickets;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface TicketDao {

    @Query("SELECT * FROM tickets")
    Single<List<Tickets>> getAllTickets();

    @Query("SELECT * FROM tickets WHERE ticketID = :ticketID")
    Single<Tickets> getTicketByID(int ticketID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertTicket(Tickets tickets);

    @Update
    Single<Integer> updateTicket(Tickets tickets);

    @Delete
    Single<Integer> deleteTicket(Tickets tickets);
}
