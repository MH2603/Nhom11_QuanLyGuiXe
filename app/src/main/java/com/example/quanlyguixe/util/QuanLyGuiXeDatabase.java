package com.example.quanlyguixe.util;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.quanlyguixe.data.model.Employees;
import com.example.quanlyguixe.data.model.Tickets;
import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.data.model.typeconverter.DateConverter;
import com.example.quanlyguixe.data.model.Vehicle;
import com.example.quanlyguixe.data.model.typeconverter.TicketConverter;
import com.example.quanlyguixe.data.repo.local.employee.EmployeeDao;
import com.example.quanlyguixe.data.repo.local.parkinglot.ParkingLotDao;
import com.example.quanlyguixe.data.repo.local.ticket.TicketDao;
import com.example.quanlyguixe.data.repo.local.vehicle.VehicleDao;

@Database(entities = {Employees.class, Tickets.class, Vehicle.class, ParkingLot.class}, version = 1)
@TypeConverters({DateConverter.class, TicketConverter.class})
public abstract class QuanLyGuiXeDatabase extends RoomDatabase {
    public abstract EmployeeDao employeeDao();
    public abstract TicketDao ticketDao();

    public abstract ParkingLotDao parkingLotDao();
    public abstract VehicleDao vehicleDao();
}
