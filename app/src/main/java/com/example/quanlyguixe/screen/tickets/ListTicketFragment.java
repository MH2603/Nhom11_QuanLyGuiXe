package com.example.quanlyguixe.screen.tickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quanlyguixe.R;
import com.example.quanlyguixe.data.model.Tickets;
import com.example.quanlyguixe.databinding.FragmentListTicketsBinding;
import com.example.quanlyguixe.util.Constant;
import com.example.quanlyguixe.util.base.BaseFragment;
import com.example.quanlyguixe.util.dialog.AlertDialogFactory;
import com.example.quanlyguixe.util.interfaces.IUpdateDeleteListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
@AndroidEntryPoint
public class ListTicketFragment extends BaseFragment<FragmentListTicketsBinding> {

    private TicketViewModel ticketViewModel;

    @Inject
    NavController navController;

    private TicketAdapter ticketAdapter;
    @Override
    public FragmentListTicketsBinding inflateViewBinding(LayoutInflater inflater) {
        return FragmentListTicketsBinding.inflate(inflater);
    }

    @Override
    protected void initScreenData() {
        ticketViewModel = new ViewModelProvider(this).get(TicketViewModel.class);
        ticketAdapter = new TicketAdapter();
        viewBinding.recyclerViewTickets.setAdapter(ticketAdapter);
        ticketViewModel.getAllTickets();
    }

    @Override
    protected void addEvent() {
        viewBinding.buttonAddTicket.setOnClickListener(view -> {
            navController.navigate(R.id.action_nav_list_tickets_to_nav_add_update_tickets);
        });

        viewBinding.recyclerViewTickets.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                boolean isScrollDown = dy > 0;

                if(isScrollDown) {
                    viewBinding.buttonAddTicket.hide();
                } else {
                    viewBinding.buttonAddTicket.show();
                }
            }
        });

        ticketAdapter.setOnUpdateDeleteListener(new IUpdateDeleteListener<Tickets>() {
            @Override
            public void onUpdate(Tickets item) {
                AlertDialogFactory.createNormalMessageDialog(
                        getContext(),
                        "Bạn có muốn cập nhật vé này không?",
                        () -> {
                            Bundle bundle = new Bundle();
                            bundle.putBoolean(Constant.KEY_BUNDLE_IS_UPDATE, true);
                            bundle.putParcelable(Constant.KEY_BUNDLE_TICKET, item);
                            navController.navigate(
                                    R.id.action_nav_list_tickets_to_nav_add_update_tickets, bundle);
                        }).show();
            }

            @Override
            public void onDelete(Tickets item, int position) {
                AlertDialogFactory.createNormalMessageDialog(
                        getContext(),
                        "Bạn có muốn xóa vé này không?",
                        () -> {
                            ticketViewModel.deleteTicket(item);
                            List<Tickets> ticketsList = new ArrayList<>(ticketAdapter.getCurrentList());
                            ticketsList.remove(item);
                            ticketAdapter.submitList(ticketsList);
                            ticketAdapter.notifyItemRemoved(position);

                            Toast.makeText(getContext(), "Xóa vé xe thành công", Toast.LENGTH_SHORT).show();
                            if(ticketsList.size() == 0) {
                                viewBinding.recyclerViewTickets.setVisibility(View.GONE);
                                viewBinding.textEmptyTickets.setVisibility(View.VISIBLE);
                            }
                        }).show();
            }
        });
    }

    @Override
    protected void bindToViewModel() {
        ticketViewModel.getTickets().observe(getViewLifecycleOwner(), tickets -> {
            ticketAdapter.submitList(tickets);

            if(ticketAdapter.getCurrentList().size() == 0) {
                viewBinding.recyclerViewTickets.setVisibility(View.GONE);
                viewBinding.textEmptyTickets.setVisibility(View.VISIBLE);
            } else {
                viewBinding.textEmptyTickets.setVisibility(View.GONE);
            }
        });
    }
}
