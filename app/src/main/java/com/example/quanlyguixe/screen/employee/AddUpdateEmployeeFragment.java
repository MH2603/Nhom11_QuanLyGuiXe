package com.example.quanlyguixe.screen.employee;

import android.app.DatePickerDialog;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.data.model.Employees;
import com.example.quanlyguixe.databinding.FragmentAddUpdateEmployeeBinding;
import com.example.quanlyguixe.screen.main.MainActivity;
import com.example.quanlyguixe.util.Constant;
import com.example.quanlyguixe.util.Validator;
import com.example.quanlyguixe.util.base.BaseFragment;
import com.example.quanlyguixe.util.dialog.AlertDialogFactory;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AddUpdateEmployeeFragment extends BaseFragment<FragmentAddUpdateEmployeeBinding> {

    private EmployeeViewModel employeeViewModel;

    private DatePickerDialog datePickerDialog;

    private boolean isUpdate = false;

    private Employees employees = null;

    @Inject
    NavController navController;

    @Override
    public FragmentAddUpdateEmployeeBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentAddUpdateEmployeeBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        if(getArguments() != null) {
            isUpdate = getArguments().getBoolean(Constant.KEY_BUNDLE_IS_UPDATE, false);
            employees = (Employees) getArguments().getParcelable(Constant.KEY_BUNDLE_EMPLOYEE);
        }

        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        datePickerDialog = new DatePickerDialog(getContext());

        MainActivity mainActivity = (MainActivity) getActivity();

        if(isUpdate && employees != null) {
            Objects.requireNonNull(mainActivity).updateTitleToolBar(getResources().getString(R.string.text_button_update_nhanvien));
            viewBinding.buttonAddUpdateEmployee.setText(getResources().getString(R.string.text_button_update_nhanvien));
            setInfoEmployee();
        } else {
            Objects.requireNonNull(mainActivity).updateTitleToolBar(getResources().getString(R.string.text_button_add_nhanvien));
            viewBinding.buttonAddUpdateEmployee.setText(getResources().getString(R.string.text_button_add_nhanvien));
        }
    }

    @Override
    protected void addEvent() {
        datePickerDialog.setOnDateSetListener((datePicker, year, month, day) -> {
            Date date = new Date(year - Constant.DEFAULT_START_YEAR, month, day);
            viewBinding.textInputEmployeeDateOfBirth.getEditText().setText(Constant.YEAR_FORMAT.format(date));
        });

        // Sự kiện mở hộp thoại chọn ngày
        viewBinding.textInputEmployeeDateOfBirth.setOnClickListener(view -> {
            showDatePickerDialog();
        });

        viewBinding.textInputEmployeeDateOfBirth.getEditText().setOnClickListener(view -> {
            showDatePickerDialog();
        });

        viewBinding.buttonClearAllInfo.setOnClickListener(view -> {
            AlertDialogFactory.createClearAllInfoDialog(getContext(), () -> {
                viewBinding.textInputEmployeeName.getEditText().setText("");
                viewBinding.textInputEmployeeEmail.getEditText().setText("");
                viewBinding.textInputEmployeeDateOfBirth.getEditText().setText("");
                viewBinding.textInputEmployeeNumberPhone.getEditText().setText("");

                viewBinding.textInputEmployeeName.getEditText().requestFocus();

                Toast.makeText(getContext(), "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
            }).show();
        });

        viewBinding.buttonAddUpdateEmployee.setOnClickListener(view -> {
            try {
                List<EditText> editTexts = Arrays.asList(
                        viewBinding.textInputEmployeeName.getEditText(),
                        viewBinding.textInputEmployeeEmail.getEditText(),
                        viewBinding.textInputEmployeeDateOfBirth.getEditText(),
                        viewBinding.textInputEmployeeNumberPhone.getEditText()
                );

                if(editTexts.stream().anyMatch(Validator::isEmptyEditText)) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (isUpdate) {
                        employees.setFullName(viewBinding.textInputEmployeeName.getEditText().getText().toString());
                        employees.setEmail(viewBinding.textInputEmployeeEmail.getEditText().getText().toString());
                        employees.setDateOfBirth(Constant.YEAR_FORMAT.parse(viewBinding.textInputEmployeeDateOfBirth.getEditText().getText().toString()));
                        employees.setPhoneNumber(viewBinding.textInputEmployeeNumberPhone.getEditText().getText().toString());

                        employeeViewModel.updateEmployee(employees);
                    } else {
                        Employees newEmployee = new Employees();
                        newEmployee.setEmployeeID(0);

                        newEmployee.setFullName(viewBinding.textInputEmployeeName.getEditText().getText().toString());
                        newEmployee.setEmail(viewBinding.textInputEmployeeEmail.getEditText().getText().toString());
                        newEmployee.setDateOfBirth(Constant.YEAR_FORMAT.parse(viewBinding.textInputEmployeeDateOfBirth.getEditText().getText().toString()));
                        newEmployee.setPhoneNumber(viewBinding.textInputEmployeeNumberPhone.getEditText().getText().toString());

                        employeeViewModel.insertEmployee(newEmployee);
                    }
                }
            } catch (ParseException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void bindToViewModel() {
        employeeViewModel.checkHasError().observe(getViewLifecycleOwner(), hasError -> {
            Toast.makeText(getContext(), hasError, Toast.LENGTH_SHORT).show();
        });

        employeeViewModel.getBackToPreviousScreen().observe(getViewLifecycleOwner(), backToPreviousScreen -> {
            if (backToPreviousScreen) {
                String displayText = isUpdate ? "Sửa dữ liệu thành công" : "Thêm dữ liệu thành công";
                Toast.makeText(getContext(), displayText, Toast.LENGTH_SHORT).show();
                navController.navigateUp();
            }
        });
    }

    private void setInfoEmployee() {
        viewBinding.textInputEmployeeName.getEditText().setText(employees.getFullName());
        viewBinding.textInputEmployeeEmail.getEditText().setText(employees.getEmail());
        viewBinding.textInputEmployeeNumberPhone.getEditText().setText(employees.getPhoneNumber());
        String displayTextDate = Constant.YEAR_FORMAT.format(employees.getDateOfBirth());
        viewBinding.textInputEmployeeDateOfBirth.getEditText().setText(displayTextDate);
    }

    private void showDatePickerDialog() {
        if(viewBinding.textInputEmployeeDateOfBirth.getEditText().getText().toString().isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        } else {
            try {
                String dateString = viewBinding.textInputEmployeeDateOfBirth.getEditText().getText().toString();
                Date date = Constant.YEAR_FORMAT.parse(dateString);
                datePickerDialog.updateDate(date.getYear() + Constant.DEFAULT_START_YEAR, date.getMonth(), date.getDate());
                datePickerDialog.show();
            } catch (ParseException e) {
                Toast.makeText(getContext(), "Không đúng định dạng", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        employeeViewModel.resetBackToPreviousScreenState();
        datePickerDialog.dismiss();
        employees = null;
        super.onDestroy();
    }
}