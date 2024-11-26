package com.example.quanlyguixe.screen.parking_lots;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.databinding.FragmentParkingLotsBinding;
import com.example.quanlyguixe.screen.main.MainActivity;
import com.example.quanlyguixe.util.Constant;
import com.example.quanlyguixe.util.base.BaseFragment;
import com.example.quanlyguixe.util.dialog.AlertDialogFactory;
import com.example.quanlyguixe.util.interfaces.IUpdateDeleteListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ParkingLotsFragment extends BaseFragment<FragmentParkingLotsBinding> {
    private ParkingLotViewModel parkingLotViewModel;
    private ParkingLotAdapter parkingLotAdapter;

    @Inject
    NavController navController;

    @Override
    public FragmentParkingLotsBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentParkingLotsBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        parkingLotViewModel = new ViewModelProvider(this).get(ParkingLotViewModel.class);
        parkingLotAdapter = new ParkingLotAdapter();
        viewBinding.recyclerViewParkingLots.setAdapter(parkingLotAdapter);
        parkingLotViewModel.getAllParkingLots();
    }

    @Override
    protected void addEvent() {
        viewBinding.buttonAddParkingLot.setOnClickListener(view -> {
            navController.navigate(R.id.action_nav_list_parking_lots_to_nav_add_update_parking_slot);
        });

        viewBinding.recyclerViewParkingLots.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean isScrollDown = dy > 0;

                if (isScrollDown) {
                    viewBinding.buttonAddParkingLot.hide();
                } else {
                    viewBinding.buttonAddParkingLot.show();
                }
            }
        });

        parkingLotAdapter.setOnUpdateDeleteListener(new IUpdateDeleteListener<ParkingLot>() {
            @Override
            public void onUpdate(ParkingLot item) {
                AlertDialogFactory.createNormalMessageDialog(
                        getContext(),
                        "Bạn có muốn cập nhật nhà xe này không?",
                        () -> {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(Constant.KEY_BUNDLE_IS_UPDATE, true);
                            bundle.putParcelable(Constant.KEY_BUNDLE_PARKING_LOT, item);
                            navController.navigate(R.id.action_nav_list_parking_lots_to_nav_add_update_parking_slot, bundle);
                        }).show();
            }

            @Override
            public void onDelete(ParkingLot item, int position) {
                AlertDialogFactory.createNormalMessageDialog(
                        getContext(),
                        "Bạn có muốn xóa nhà xe này không?",
                        () -> {
                            parkingLotViewModel.deleteItem(item);
                            List<ParkingLot> parkingLotList = new ArrayList<>(parkingLotAdapter.getCurrentList());
                            parkingLotList.remove(item);
                            parkingLotAdapter.submitList(parkingLotList);
                            parkingLotAdapter.notifyItemRemoved(position);

                            Toast.makeText(getContext(), "Xóa nhà xe xe thành công", Toast.LENGTH_SHORT).show();
                            if (parkingLotList.size() == 0) {
                                viewBinding.recyclerViewParkingLots.setVisibility(View.GONE);
                                viewBinding.textEmptyParkingLot.setVisibility(View.VISIBLE);
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void bindToViewModel() {
        parkingLotViewModel.getParkingLots().observe(getViewLifecycleOwner(), new Observer<List<ParkingLot>>() {
            @Override
            public void onChanged(List<ParkingLot> parkingLots) {
                parkingLotAdapter.submitList(parkingLots);

                if (parkingLots.isEmpty()) {
                    viewBinding.recyclerViewParkingLots.setVisibility(View.GONE);
                    viewBinding.textEmptyParkingLot.setVisibility(View.VISIBLE);
                } else {
                    viewBinding.recyclerViewParkingLots.setVisibility(View.VISIBLE);
                    viewBinding.textEmptyParkingLot.setVisibility(View.GONE);
                }
            }
        });
    }
}