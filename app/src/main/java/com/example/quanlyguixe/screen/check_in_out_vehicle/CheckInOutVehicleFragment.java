package com.example.quanlyguixe.screen.check_in_out_vehicle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.databinding.FragmentCheckInOutVehicleBinding;
import com.example.quanlyguixe.util.base.BaseFragment;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CheckInOutVehicleFragment extends BaseFragment<FragmentCheckInOutVehicleBinding> {
    @Inject
    NavController navController;
    @Override
    public FragmentCheckInOutVehicleBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentCheckInOutVehicleBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {

    }

    @Override
    protected void addEvent() {
        viewBinding.buttonCheckInVehicle.setOnClickListener(view -> {
            navController.navigate(R.id.action_checkInOutVehicleFragment_to_checkInVehicleFragment);
        });

        viewBinding.buttonCheckOutVehicle.setOnClickListener(view -> {
            navController.navigate(R.id.action_checkInOutVehicleFragment_to_checkOutVehicleFragment);
        });
    }

    @Override
    protected void bindToViewModel() {

    }
}