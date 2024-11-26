package com.example.quanlyguixe.data.model.typeconverter;

import androidx.room.TypeConverter;

import com.example.quanlyguixe.data.model.Tickets;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class TicketConverter {

    @TypeConverter
    public Tickets toTickets(String json) {
        Type typeToken = new TypeToken<Tickets>() {}.getType();
        return new Gson().fromJson(json, typeToken);
    }

    @TypeConverter
    public String fromTickets(Tickets tickets) {
        return new Gson().toJson(tickets);
    }
}
