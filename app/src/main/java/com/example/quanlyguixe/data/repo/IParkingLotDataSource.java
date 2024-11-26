package com.example.quanlyguixe.data.repo;


import com.example.quanlyguixe.data.model.ParkingLot;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface IParkingLotDataSource {
    interface Local {
        Single<List<ParkingLot>> getAll();

        Single<ParkingLot> getItemWithID(int id);

        Single<Long> insertItem(ParkingLot item);

        Single<Integer> updateItem(ParkingLot item);

        Single<Integer> deleteItem(ParkingLot item);
    }
}
