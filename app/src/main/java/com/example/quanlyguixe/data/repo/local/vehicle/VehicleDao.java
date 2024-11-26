package com.example.quanlyguixe.data.repo.local.vehicle;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;


import com.example.quanlyguixe.data.model.Vehicle;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface VehicleDao {

    @Query("SELECT * FROM vehicle")
    Single<List<Vehicle>> getAllVehicles();

    @Query("SELECT * FROM vehicle WHERE vehicleControlID = :vehicleID")
    Single<Vehicle> getVehicleByID(int vehicleID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertVehicle(Vehicle vehicle);

    @Update
    Single<Integer> updateVehicle(Vehicle vehicle);

    @Delete
    Single<Integer> deleteVehicle(List<Vehicle> vehicle);
}
