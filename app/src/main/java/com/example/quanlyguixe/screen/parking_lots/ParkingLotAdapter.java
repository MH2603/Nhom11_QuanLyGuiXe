package com.example.quanlyguixe.screen.parking_lots;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.databinding.ItemParkingLotsLayoutBinding;
import com.example.quanlyguixe.util.base.BaseRecyclerViewAdapter;
import com.example.quanlyguixe.util.base.BaseViewHolder;
import com.example.quanlyguixe.util.interfaces.IUpdateDeleteListener;

public class ParkingLotAdapter extends BaseRecyclerViewAdapter<ParkingLot, ItemParkingLotsLayoutBinding, ParkingLotAdapter.ViewHolder> {

    private IUpdateDeleteListener<ParkingLot> listener;

    protected ParkingLotAdapter() {
        super(ParkingLot.getDiffCallback());
    }

    public void setOnUpdateDeleteListener(IUpdateDeleteListener<ParkingLot> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemParkingLotsLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    public class ViewHolder extends BaseViewHolder<ParkingLot, ItemParkingLotsLayoutBinding> {
        public ViewHolder(@NonNull ItemParkingLotsLayoutBinding binding) {
            super(binding);
        }

        @Override
        public void bindItem(ParkingLot item) {
            super.bindItem(item);

            Resources resources = viewBinding.getRoot().getContext().getResources();
            viewBinding.textParkingLotId.setText(resources.getString(R.string.text_temp_parking_lot_id, item.getId()));
            viewBinding.textParkingLotName.setText(resources.getString(R.string.text_temp_parking_lot_name, item.getName()));
            viewBinding.textParkingSlotAvailable.setText(resources.getString(R.string.text_temp_parking_slot_available, item.getAvailabelSlot()));
            viewBinding.textParkingSlotMax.setText(resources.getString(R.string.text_temp_parking_slot_max, item.getSlotMax()));

            viewBinding.buttonUpdateParkingLot.setOnClickListener(view -> {
                listener.onUpdate(item);
            });

            viewBinding.buttonDeleteParkingLot.setOnClickListener(view -> {
                listener.onDelete(item, getAdapterPosition());
            });
        }
    }
}
