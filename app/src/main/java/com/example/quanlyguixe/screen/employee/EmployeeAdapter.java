package com.example.quanlyguixe.screen.employee;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.data.model.Employees;
import com.example.quanlyguixe.databinding.ItemEmployeeLayoutBinding;
import com.example.quanlyguixe.util.Constant;
import com.example.quanlyguixe.util.base.BaseRecyclerViewAdapter;
import com.example.quanlyguixe.util.base.BaseViewHolder;
import com.example.quanlyguixe.util.interfaces.IUpdateDeleteListener;

public class EmployeeAdapter extends BaseRecyclerViewAdapter<Employees, ItemEmployeeLayoutBinding, EmployeeAdapter.ViewHolder> {

    protected EmployeeAdapter() {
        super(Employees.getDiffCallback());
    }

    private IUpdateDeleteListener<Employees> listener;

    public void setOnUpdateDeleteListener(IUpdateDeleteListener<Employees> listener) {
        this.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(ItemEmployeeLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    public class ViewHolder extends BaseViewHolder<Employees, ItemEmployeeLayoutBinding> {

        public ViewHolder(@NonNull ItemEmployeeLayoutBinding binding) {
            super(binding);
        }

        @Override
        public void bindItem(Employees item) {
            super.bindItem(item);

            Resources resources = viewBinding.getRoot().getContext().getResources();

            viewBinding.textEmployeeId.setText(resources.getString(R.string.text_employee_id_title, item.getEmployeeID()));

            viewBinding.textEmployeeName.setText(resources.getString(R.string.text_employee_name_title, item.getFullName()));

            String displayDateText = Constant.YEAR_FORMAT.format(item.getDateOfBirth());

            viewBinding.textEmployeeDateOfBirth.setText(resources.getString(R.string.text_employee_date_of_birth, displayDateText));

            viewBinding.textEmployeeEmail.setText(resources.getString(R.string.text_employee_email, item.getEmail()));

            viewBinding.textEmployeePhoneNumber.setText(resources.getString(R.string.text_employee_phone_number, item.getPhoneNumber()));

            viewBinding.buttonUpdateTicket.setOnClickListener(v -> listener.onUpdate(item));

            viewBinding.buttonDeleteTicket.setOnClickListener(v -> listener.onDelete(item, getAdapterPosition()));
        }
    }
}
