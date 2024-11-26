package com.example.quanlyguixe.data.repo.local.parkinglot;

import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.data.repo.IParkingLotDataSource;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class ParkingLotLocalDataSource implements IParkingLotDataSource.Local {

    private final ParkingLotDao dao;

    @Inject
    public ParkingLotLocalDataSource(ParkingLotDao parkingLotDao) {
        this.dao = parkingLotDao;
    }

    @Override
    public Single<List<ParkingLot>> getAll() {
        return dao.getAll();
    }

    @Override
    public Single<ParkingLot> getItemWithID(int id) {
        return dao.getItemWithID(id);
    }

    @Override
    public Single<Long> insertItem(ParkingLot item) {
        return dao.insertItem(item);
    }

    @Override
    public Single<Integer> updateItem(ParkingLot item) {
        return dao.updateItem(item);
    }

    @Override
    public Single<Integer> deleteItem(ParkingLot item) {
        return dao.deleteItem(item);
    }
}
