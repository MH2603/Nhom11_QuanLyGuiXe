package com.example.quanlyguixe.screen.check_in_out_vehicle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import com.example.quanlyguixe.data.model.Vehicle;
import com.example.quanlyguixe.databinding.FragmentCheckOutVehicleBinding;
import com.example.quanlyguixe.util.base.BaseFragment;


@AndroidEntryPoint
public class CheckOutVehicleFragment extends BaseFragment<FragmentCheckOutVehicleBinding> {

    @Inject
    NavController navController;

    CheckInCheckOutVehicleViewModel viewModel;

    private VehicleAdapter vehicleAdapter;

    private final ArrayList<Vehicle> listSelectedVehicle = new ArrayList<>();

    @Override
    public FragmentCheckOutVehicleBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentCheckOutVehicleBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        viewModel = new ViewModelProvider(this).get(CheckInCheckOutVehicleViewModel.class);
        vehicleAdapter = new VehicleAdapter();
        viewBinding.recyclerViewVehicle.setAdapter(vehicleAdapter);
        viewModel.getAllVehicles();
    }

    @Override
    protected void addEvent() {
        viewBinding.recyclerViewVehicle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean isScrollDown = dy > 0;

                if(isScrollDown) {
                    viewBinding.buttonCheckOut.hide();
                } else {
                    viewBinding.buttonCheckOut.show();
                }
            }
        });

        vehicleAdapter.setStateChangeListener((item, position, isChecked) -> {
            if (isChecked) {
                listSelectedVehicle.add(item);
            } else {
                listSelectedVehicle.remove(item);
            }
        });

        viewBinding.buttonCheckOut.setOnClickListener(v -> {
            viewModel.deleteVehicle(listSelectedVehicle);
        });
    }

    @Override
    protected void bindToViewModel() {
        viewModel.getVehicles().observe(getViewLifecycleOwner(), vehicles -> {
            vehicleAdapter.submitList(vehicles);
        });

        viewModel.isCheckOutComplete().observe(getViewLifecycleOwner(), isCheckOutComplete -> {
            if (isCheckOutComplete) {
                Toast.makeText(getContext(), "Check-out thành công", Toast.LENGTH_SHORT).show();
                navController.navigateUp();
            }
        });
    }

    @Override
    public void onDestroy() {
        viewModel.resetCompleteState();
        super.onDestroy();
    }
}
