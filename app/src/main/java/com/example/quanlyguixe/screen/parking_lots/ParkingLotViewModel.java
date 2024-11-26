package com.example.quanlyguixe.screen.parking_lots;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.data.repo.ParkingLotRepository;
import com.example.quanlyguixe.di.feature.parkinglot.ParkingLotFeatureModule;
import com.example.quanlyguixe.util.base.BaseViewModel;
import com.example.quanlyguixe.util.interfaces.IResultListener;

import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ParkingLotViewModel extends BaseViewModel {

    private final MutableLiveData<List<ParkingLot>> parkinglots = new MutableLiveData<>();

    public LiveData<List<ParkingLot>> getParkingLots() {
        return parkinglots;
    }

    private final ParkingLotRepository parkingLotRepository;

    @Inject
    public ParkingLotViewModel(ParkingLotRepository parkingLotRepository) {
        this.parkingLotRepository = parkingLotRepository;
        getAllParkingLots();
    }

    public void getAllParkingLots() {
        registerDisposable(
                executeTaskWithLoading(
                        parkingLotRepository.getAll(),
                        new IResultListener<List<ParkingLot>>() {
                            @Override
                            public void onSuccess(List<ParkingLot> data) {
                                parkinglots.setValue(data);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.postValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void insertItem(ParkingLot item) {
        registerDisposable(
                executeTaskWithLoading(
                        parkingLotRepository.insertItem(item),
                        new IResultListener<Long>() {
                            @Override
                            public void onSuccess(Long data) {
                                _backToPreviousScreen.setValue(true);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.setValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void updateItem(ParkingLot item) {
        registerDisposable(
                executeTaskWithLoading(
                        parkingLotRepository.updateItem(item), new IResultListener<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {
                                _backToPreviousScreen.setValue(true);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.setValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void deleteItem(ParkingLot item) {
        registerDisposable(
                executeTaskWithLoading(
                        parkingLotRepository.deleteItem(item),
                        new IResultListener<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {
                                getAllParkingLots();
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.setValue(throwable.getMessage());
                            }
                        }
                )
        );
    }
}
