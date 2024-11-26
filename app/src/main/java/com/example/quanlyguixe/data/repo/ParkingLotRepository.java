package com.example.quanlyguixe.data.repo;


import com.example.quanlyguixe.data.model.ParkingLot;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class ParkingLotRepository implements IParkingLotDataSource.Local {

    private final IParkingLotDataSource.Local local;

    @Inject
    public ParkingLotRepository(IParkingLotDataSource.Local local) {
        this.local = local;
    }

    @Override
    public Single<List<ParkingLot>> getAll() {
        return local.getAll();
    }

    @Override
    public Single<ParkingLot> getItemWithID(int id) {
        return local.getItemWithID(id);
    }

    @Override
    public Single<Long> insertItem(ParkingLot item) {
        return local.insertItem(item);
    }

    @Override
    public Single<Integer> updateItem(ParkingLot item) {
        return local.updateItem(item);
    }

    @Override
    public Single<Integer> deleteItem(ParkingLot item) {
        return local.deleteItem(item);
    }
}
