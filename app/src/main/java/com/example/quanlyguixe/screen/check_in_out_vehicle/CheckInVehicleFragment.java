package com.example.quanlyguixe.screen.check_in_out_vehicle;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.quanlyguixe.data.model.ParkingLot;
import com.example.quanlyguixe.data.model.Tickets;
import com.example.quanlyguixe.databinding.FragmentCheckInVehicleBinding;
import com.example.quanlyguixe.util.Constant;
import com.example.quanlyguixe.util.Validator;
import com.example.quanlyguixe.util.base.BaseFragment;
import com.example.quanlyguixe.util.dialog.AlertDialogFactory;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import com.example.quanlyguixe.data.model.Vehicle;


@AndroidEntryPoint
public class CheckInVehicleFragment extends BaseFragment<FragmentCheckInVehicleBinding>
        implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    @Inject
    NavController navController;

    CheckInCheckOutVehicleViewModel viewModel;

    private DatePickerDialog datePickerDialog;

    private TimePickerDialog timePickerDialog;

    private Date selectedDate = Calendar.getInstance().getTime();
    private Tickets selectTicket;


    @Override
    public FragmentCheckInVehicleBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentCheckInVehicleBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        viewModel = new ViewModelProvider(this).get(CheckInCheckOutVehicleViewModel.class);
        datePickerDialog = new DatePickerDialog(getContext());
        timePickerDialog = new TimePickerDialog(getContext(), this, 0, 0, false);

        String date = Constant.YEAR_FORMAT.format(selectedDate);
        String time = Constant.TIME_FORMAT.format(selectedDate);

        viewBinding.textInputDateCheckIn.getEditText().setText(date);
        viewBinding.textInputTimeCheckIn.getEditText().setText(time);

        viewModel.getAllTickets();
    }

    @Override
    protected void addEvent() {
        datePickerDialog.setOnDateSetListener(this);

        viewBinding.filledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof Tickets) {
                    selectTicket = (Tickets) item;
                    // do something with the studentInfo object
                }
            }
        });
        viewBinding.filledExposedDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Tickets tickets = (Tickets) adapterView.getItemAtPosition(i);
                selectTicket = tickets;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        viewBinding.textInputDateCheckIn.setOnClickListener(v -> {
            showDatePickerDialog();
        });

        viewBinding.textInputDateCheckIn.getEditText().setOnClickListener(v -> {
            showDatePickerDialog();
        });

        viewBinding.textInputTimeCheckIn.getEditText().setOnClickListener(v -> {
            showTimePickerDialog();
        });

        viewBinding.buttonClearAllInfo.setOnClickListener(view -> {
            AlertDialogFactory.createClearAllInfoDialog(getContext(), () -> {
                selectedDate = Calendar.getInstance().getTime();
                viewBinding.textInputVehicleNumber.getEditText().setText("");
                viewBinding.textInputDateCheckIn.getEditText().setText(Constant.YEAR_FORMAT.format(selectedDate));
                viewBinding.textInputTimeCheckIn.getEditText().setText(Constant.TIME_FORMAT.format(selectedDate));
                viewBinding.filledExposedDropdown.setSelection(0);
                viewBinding.textInputVehicleNumber.getEditText().requestFocus();

                Toast.makeText(getContext(), "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
            }).show();
        });

        viewBinding.buttonAddVehicle.setOnClickListener(view -> {
            List<EditText> editTexts = Arrays.asList(
                    viewBinding.textInputVehicleNumber.getEditText()
            );

            if(editTexts.stream().anyMatch(Validator::isEmptyEditText)
                    && viewBinding.filledExposedDropdown.getText().toString().isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                Vehicle vehicle = new Vehicle();
                vehicle.setVehicleControlID(0);
                vehicle.setVehicleNumber(viewBinding.textInputVehicleNumber.getEditText().getText().toString());
                vehicle.setCustomerName(viewBinding.textInputCustomerName.getEditText().getText().toString());
                vehicle.setTicket(selectTicket);
                vehicle.setDateTimeIn(selectedDate);

                viewModel.insertVehicle(vehicle);
            }
        });
    }


    @Override
    protected void bindToViewModel() {

        // old
//        viewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
//            ArrayAdapter<Tickets> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, tickets);
//            viewBinding.filledExposedDropdown.setAdapter(adapter);
//        });

        // new to testing
        viewModel.getParkingLots().observe(getViewLifecycleOwner(), parkingLots -> {
            ArrayAdapter<ParkingLot> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, parkingLots);
            viewBinding.filledExposedDropdown.setAdapter(adapter);
        });

        viewModel.getBackToPreviousScreen().observe(getViewLifecycleOwner(), backToPreviousScreen -> {
            if(backToPreviousScreen) {
                Toast.makeText(getContext(), "Check-in thành công", Toast.LENGTH_SHORT).show();
                navController.navigateUp();
            }
        });
    }

    private void showDatePickerDialog() {
        datePickerDialog.updateDate(selectedDate.getYear() + Constant.DEFAULT_START_YEAR, selectedDate.getMonth(), selectedDate.getDate());
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        timePickerDialog.updateTime(selectedDate.getHours(), selectedDate.getMinutes());
        timePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        selectedDate.setYear(year - Constant.DEFAULT_START_YEAR);
        selectedDate.setMonth(month);
        selectedDate.setDate(dayOfMonth);
        String date = Constant.YEAR_FORMAT.format(selectedDate);
        viewBinding.textInputDateCheckIn.getEditText().setText(date);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
        selectedDate.setHours(hourOfDay);
        selectedDate.setMinutes(minutes);
        String time = Constant.TIME_FORMAT.format(selectedDate);
        viewBinding.textInputTimeCheckIn.getEditText().setText(time);
    }

    @Override
    public void onStop() {
        viewModel.resetBackToPreviousScreenState();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        datePickerDialog.dismiss();
        timePickerDialog.dismiss();
        selectedDate = null;
        super.onDestroy();
    }
}