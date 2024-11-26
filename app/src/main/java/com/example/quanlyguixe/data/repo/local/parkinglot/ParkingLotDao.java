package com.example.quanlyguixe.data.repo.local.parkinglot;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.quanlyguixe.data.model.ParkingLot;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

@Dao
public interface ParkingLotDao {

    @Query("SELECT * FROM ParkingLot")
    Single<List<ParkingLot>> getAll();

    @Query("SELECT * FROM ParkingLot WHERE id = :parkinglotID")
    Single<ParkingLot> getItemWithID(int parkinglotID);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<Long> insertItem(ParkingLot item);

    @Update
    Single<Integer> updateItem(ParkingLot item);

    @Delete
    Single<Integer> deleteItem(ParkingLot item);
}
