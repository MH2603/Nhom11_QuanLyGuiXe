package com.example.quanlyguixe.screen.tickets;

import android.app.DatePickerDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.example.quanlyguixe.data.model.Tickets;
import com.example.quanlyguixe.databinding.FragmentAddUpdateTicketBinding;
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
public class AddUpdateTicketFragment
        extends BaseFragment<FragmentAddUpdateTicketBinding> {

    private TicketViewModel ticketViewModel;

    private DatePickerDialog datePickerDialog;

    private boolean isUpdate = false;

    private Tickets tickets = null;

    @Inject
    NavController navController;

    @Override
    public FragmentAddUpdateTicketBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentAddUpdateTicketBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        if (getArguments() != null) {
            isUpdate = getArguments().getBoolean(Constant.KEY_BUNDLE_IS_UPDATE, false);
            tickets = getArguments().getParcelable(Constant.KEY_BUNDLE_TICKET);
        }

        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        datePickerDialog = new DatePickerDialog(getContext());

        MainActivity mainActivity = (MainActivity) getActivity();

        if (isUpdate && tickets != null) {
            Objects.requireNonNull(mainActivity).updateTitleToolBar("Cập nhật vé");

            viewBinding.buttonAddTicket.setVisibility(View.GONE);
            viewBinding.buttonUpdateTicket.setVisibility(View.VISIBLE);

            viewBinding.textInputTicketType.getEditText().setText(tickets.getTicketType());
            viewBinding.textInputTicketPrice.getEditText().setText(String.valueOf(tickets.getPrice()));
            String displayDate = Constant.YEAR_FORMAT.format(tickets.getExpirationDate());
            viewBinding.textInputTicketExpirationDate.getEditText().setText(displayDate);
        } else {
            Objects.requireNonNull(mainActivity).updateTitleToolBar("Thêm vé");

            viewBinding.buttonAddTicket.setVisibility(View.VISIBLE);
            viewBinding.buttonUpdateTicket.setVisibility(View.GONE);
        }
    }

    @Override
    protected void addEvent() {
        //Gán sự kiện đổi ngày
        datePickerDialog.setOnDateSetListener((datePicker, year, month, day) -> {
            Date date = new Date(year - Constant.DEFAULT_START_YEAR, month, day);
            viewBinding.textInputTicketExpirationDate.getEditText().setText(Constant.YEAR_FORMAT.format(date));
        });

        // Sự kiện mở hộp thoại chọn ngày
        viewBinding.textInputTicketExpirationDate.setOnClickListener(view -> {
            showDatePickerDialog();
        });

        viewBinding.textInputTicketExpirationDate.getEditText().setOnClickListener(view -> {
            showDatePickerDialog();
        });


        viewBinding.buttonClearAllInfo.setOnClickListener(view -> {
            AlertDialogFactory.createClearAllInfoDialog(getContext(), () -> {
                viewBinding.textInputTicketType.getEditText().setText("");
                viewBinding.textInputTicketPrice.getEditText().setText("");
                viewBinding.textInputTicketExpirationDate.getEditText().setText("");

                viewBinding.textInputTicketType.getEditText().requestFocus();

                Toast.makeText(getContext(), "Xóa dữ liệu thành công", Toast.LENGTH_SHORT).show();
            }).show();
        });


        viewBinding.buttonAddTicket.setOnClickListener(view -> {
            try {
                List<EditText> editTexts = Arrays.asList(
                        viewBinding.textInputTicketType.getEditText(),
                        viewBinding.textInputTicketPrice.getEditText(),
                        viewBinding.textInputTicketExpirationDate.getEditText()
                );

                if(editTexts.stream().anyMatch(Validator::isEmptyEditText)) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    Tickets tickets = new Tickets();
                    tickets.setTicketID(0);
                    tickets.setTicketType(viewBinding.textInputTicketType.getEditText().getText().toString());
                    tickets.setPrice(Long.valueOf(viewBinding.textInputTicketPrice.getEditText().getText().toString()));
                    Date expireDate = Constant.YEAR_FORMAT.parse(viewBinding.textInputTicketExpirationDate.getEditText().getText().toString());
                    tickets.setExpirationDate(expireDate);

                    ticketViewModel.insertTicket(tickets);
                }
            } catch (ParseException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewBinding.buttonUpdateTicket.setOnClickListener(view -> {
            try {
                List<EditText> editTexts = Arrays.asList(
                        viewBinding.textInputTicketType.getEditText(),
                        viewBinding.textInputTicketPrice.getEditText(),
                        viewBinding.textInputTicketExpirationDate.getEditText()
                );

                if(editTexts.stream().anyMatch(Validator::isEmptyEditText)) {
                    Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    tickets.setTicketType(viewBinding.textInputTicketType.getEditText().getText().toString());
                    tickets.setPrice(Long.valueOf(viewBinding.textInputTicketPrice.getEditText().getText().toString()));
                    Date expireDate = Constant.YEAR_FORMAT.parse(viewBinding.textInputTicketExpirationDate.getEditText().getText().toString());
                    tickets.setExpirationDate(expireDate);

                    ticketViewModel.updateTicket(tickets);
                }
            } catch (ParseException e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        viewBinding.textInputTicketType.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus) {
                boolean isEmpty = viewBinding.textInputTicketType.getEditText().getText().toString().isEmpty();
                viewBinding.textInputTicketType.setHelperText(isEmpty ? "Vui lòng nhập loại vé" : null);
            }
        });

        viewBinding.textInputTicketPrice.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus) {
                boolean isEmpty = viewBinding.textInputTicketPrice.getEditText().getText().toString().isEmpty();
                viewBinding.textInputTicketPrice.setHelperText(isEmpty ? "Vui lòng giá vé" : null);
            }
        });

        viewBinding.textInputTicketExpirationDate.setOnFocusChangeListener((view, isFocus) -> {
            if(!isFocus) {
                boolean isEmpty = viewBinding.textInputTicketExpirationDate.getEditText().getText().toString().isEmpty();
                viewBinding.textInputTicketExpirationDate.setHelperText(isEmpty ? "Vui lòng nhập hạn sử dụng" : null);
            }
        });
    }

    @Override
    protected void bindToViewModel() {
        ticketViewModel.checkHasError().observe(getViewLifecycleOwner(), hasError -> {
            Toast.makeText(getContext(), hasError, Toast.LENGTH_SHORT).show();
        });

        ticketViewModel.getBackToPreviousScreen().observe(getViewLifecycleOwner(), backToPreviousScreen -> {
            if (backToPreviousScreen) {
                String displayText = isUpdate ? "Sửa dữ liệu thành công" : "Thêm dữ liệu thành công";
                Toast.makeText(getContext(), displayText, Toast.LENGTH_SHORT).show();
                navController.navigateUp();
            }
        });
    }

    private void showDatePickerDialog() {
        if(viewBinding.textInputTicketExpirationDate.getEditText().getText().toString().isEmpty()) {
            Calendar calendar = Calendar.getInstance();
            datePickerDialog.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        } else {
            try {
                String dateString = viewBinding.textInputTicketExpirationDate.getEditText().getText().toString();
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
        ticketViewModel.resetBackToPreviousScreenState();
        datePickerDialog.dismiss();
        tickets = null;
        super.onDestroy();
    }
}
