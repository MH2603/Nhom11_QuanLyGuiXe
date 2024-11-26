package com.example.quanlyguixe.di.feature.parkinglot;

import com.example.quanlyguixe.data.repo.IParkingLotDataSource;
import com.example.quanlyguixe.data.repo.ParkingLotRepository;
import com.example.quanlyguixe.data.repo.local.parkinglot.ParkingLotDao;
import com.example.quanlyguixe.data.repo.local.parkinglot.ParkingLotLocalDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ParkingLotFeatureModule {

    @Singleton
    @Provides
    public IParkingLotDataSource.Local provideParkingLotLocalDataSource(ParkingLotDao parkingLotDao) {
        return new ParkingLotLocalDataSource(parkingLotDao);
    }

    @Singleton
    @Provides
    public ParkingLotRepository provideParkingLotRepository(IParkingLotDataSource.Local parkingLocalDataSource) {
        return new ParkingLotRepository(parkingLocalDataSource);
    }
}
