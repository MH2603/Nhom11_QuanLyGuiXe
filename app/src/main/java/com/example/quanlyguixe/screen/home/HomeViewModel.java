package com.example.quanlyguixe.screen.home;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quanlyguixe.R;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final List<ScreenEntry> listScreenEntries = Arrays.asList(
//            new ScreenEntry(R.drawable.ic_employee, R.string.nav_list_employee_title, ScreenCategoryEnum.EMPLOYEE_SCREEN),
            new ScreenEntry(R.drawable.ic_parkinglots, R.string.nav_list_parking_lots_title, ScreenCategoryEnum.PARKING_LOTS_SCREEN),
            new ScreenEntry(R.drawable.ic_report, R.string.nav_reports_detail_title, ScreenCategoryEnum.REPORT_SCREEN),
            new ScreenEntry(R.drawable.ic_ticket, R.string.nav_list_ticket_title, ScreenCategoryEnum.TICKET_SCREEN),
            new ScreenEntry(R.drawable.ic_parking_lots, R.string.nav_check_in_out_vehicle_title, ScreenCategoryEnum.CHECK_IN_OUT_VEHICLE)
    );

    private final MutableLiveData<List<ScreenEntry>> screenEntries = new MutableLiveData<>();

    @Inject
    public HomeViewModel() {
        screenEntries.setValue(listScreenEntries);
    }

    public MutableLiveData<List<ScreenEntry>> getListScreenEntries() {
        return screenEntries;
    }
}