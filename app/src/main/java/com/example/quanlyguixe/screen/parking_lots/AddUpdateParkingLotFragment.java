package com.example.quanlyguixe.screen.parking_lots;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.databinding.FragmentAddUpdateParkingLotsBinding;
import com.example.quanlyguixe.screen.main.MainActivity;
import com.example.quanlyguixe.util.Constant;
import com.example.quanlyguixe.util.Validator;
import com.example.quanlyguixe.util.base.BaseFragment;
import com.example.quanlyguixe.util.dialog.AlertDialogFactory;
import com.example.quanlyguixe.util.interfaces.IUpdateDeleteListener;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddUpdateParkingLotFragment extends BaseFragment<FragmentAddUpdateParkingLotsBinding> {

    private ParkingLotViewModel parkingLotViewModel;
    private boolean isUpdate = false;
    private ParkingLot parkinglots = null;

    @Inject
    NavController navController;

    @Override
    public FragmentAddUpdateParkingLotsBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentAddUpdateParkingLotsBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        if (getArguments() != null) {
            isUpdate = getArguments().getBoolean(Constant.KEY_BUNDLE_IS_UPDATE, false);
            parkinglots = getArguments().getParcelable(Constant.KEY_BUNDLE_PARKING_LOT);
        }

        parkingLotViewModel = new ViewModelProvider(this).get(ParkingLotViewModel.class);

        MainActivity mainActivity = (MainActivity) getActivity();

        if (isUpdate && parkinglots != null) {
            Objects.requireNonNull(mainActivity).updateTitleToolBar("Cập nhật nhà xe");
            viewBinding.buttonAddParkingLot.setVisibility(View.GONE);
            viewBinding.buttonUpdateParkingLot.setVisibility(View.VISIBLE);
            viewBinding.textInputParkingLotName.getEditText().setText(parkinglots.getName());
            viewBinding.textInputParkingSlotMax.getEditText().setText(String.valueOf(parkinglots.getSlotMax()));
        } else {
            Objects.requireNonNull(mainActivity).updateTitleToolBar("Thêm nhà xe");
            viewBinding.buttonAddParkingLot.setVisibility(View.VISIBLE);
            viewBinding.buttonUpdateParkingLot.setVisibility(View.GONE);
        }
    }

    @Override
    protected void addEvent() {
        viewBinding.buttonClearAllInfo.setOnClickListener(view -> clearAllInputs());
        viewBinding.buttonAddParkingLot.setOnClickListener(view -> handleAddOrUpdateParkingLot(false));
        viewBinding.buttonUpdateParkingLot.setOnClickListener(view -> handleAddOrUpdateParkingLot(true));

        handleFocusChangeForEditText(viewBinding.textInputParkingLotName.getEditText(), "Vui lòng nhập tên nhà xe");
        handleFocusChangeForEditText(viewBinding.textInputParkingSlotMax.getEditText(), "Vui lòng nhập số vị trí tối đa");
    }

    private void clearAllInputs() {
        AlertDialogFactory.createClearAllInfoDialog(getContext(), () -> {
            viewBinding.textInputParkingLotName.getEditText().setText("");
            viewBinding.textInputParkingSlotMax.getEditText().setText("");
            viewBinding.textInputParkingLotName.getEditText().requestFocus();
            Toast.makeText(getContext(), "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
        }).show();
    }

    private void handleAddOrUpdateParkingLot(boolean isUpdate) {
        List<EditText> editTexts = Arrays.asList(
                viewBinding.textInputParkingLotName.getEditText(),
                viewBinding.textInputParkingSlotMax.getEditText()
        );

        if (editTexts.stream().anyMatch(Validator::isEmptyEditText)) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }

        Long slotMax = Long.valueOf(viewBinding.textInputParkingSlotMax.getEditText().getText().toString());

        if (isUpdate && parkinglots != null) {
            parkinglots.setName(viewBinding.textInputParkingLotName.getEditText().getText().toString());
            parkinglots.setSlotMax(slotMax);
            parkingLotViewModel.updateItem(parkinglots);
        } else {
            ParkingLot newParkingLot = new ParkingLot();
            newParkingLot.setId(0); // Set ID as 0 or any default value
            newParkingLot.setName(viewBinding.textInputParkingLotName.getEditText().getText().toString());
            newParkingLot.setAvailabelSlot(slotMax);
            newParkingLot.setSlotMax(slotMax);
            parkingLotViewModel.insertItem(newParkingLot);
        }
    }

    private void handleFocusChangeForEditText(EditText editText, String helperText) {
        editText.setOnFocusChangeListener((view, isFocus) -> {
            if (!isFocus) {
                boolean isEmpty = editText.getText().toString().isEmpty();
                editText.setError(isEmpty ? helperText : null);
            }
        });
    }

    @Override
    protected void bindToViewModel() {
        parkingLotViewModel.checkHasError().observe(getViewLifecycleOwner(), hasError -> {
            Toast.makeText(getContext(), hasError, Toast.LENGTH_SHORT).show();
        });

        parkingLotViewModel.getBackToPreviousScreen().observe(getViewLifecycleOwner(), event -> {
            if (event) {
                String displayText = isUpdate ? "Sửa dữ liệu thành công" : "Thêm dữ liệu thành công";
                Toast.makeText(getContext(), displayText, Toast.LENGTH_SHORT).show();
                navController.navigateUp();
            }
        });
    }

    @Override
    public void onDestroyView() {
        parkingLotViewModel.resetBackToPreviousScreenState();
        parkinglots = null;
        super.onDestroyView();
    }
}
