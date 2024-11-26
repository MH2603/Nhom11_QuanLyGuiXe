package com.example.quanlyguixe.screen.tickets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.quanlyguixe.data.model.Tickets;
import com.example.quanlyguixe.data.repo.TicketRepository;
import com.example.quanlyguixe.util.base.BaseViewModel;
import com.example.quanlyguixe.util.interfaces.IResultListener;


import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TicketViewModel extends BaseViewModel {

    private final MutableLiveData<List<Tickets>> _tickets = new MutableLiveData<>();

    public LiveData<List<Tickets>> getTickets() {
        return _tickets;
    }

    private final TicketRepository ticketRepository;

    @Inject
    public TicketViewModel(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;

        getAllTickets();
    }

    public void getAllTickets() {
        registerDisposable(
                executeTaskWithLoading(
                        ticketRepository.getAllTickets(),
                        new IResultListener<List<Tickets>>() {
                            @Override
                            public void onSuccess(List<Tickets> data) {
                                _tickets.setValue(data);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.postValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void insertTicket(Tickets tickets) {
        registerDisposable(
                executeTaskWithLoading(
                        ticketRepository.insertTicket(tickets),
                        new IResultListener<Long>() {
                            @Override
                            public void onSuccess(Long data) {
                                _backToPreviousScreen.setValue(true);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.setValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void updateTicket(Tickets tickets) {
        registerDisposable(
                executeTaskWithLoading(
                        ticketRepository.updateTicket(tickets),
                        new IResultListener<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {
                                _backToPreviousScreen.setValue(true);
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.setValue(throwable.getMessage());
                            }
                        }
                )
        );
    }

    public void deleteTicket(Tickets tickets) {
        registerDisposable(
                executeTaskWithLoading(
                        ticketRepository.deleteTicket(tickets),
                        new IResultListener<Integer>() {
                            @Override
                            public void onSuccess(Integer data) {
                            }

                            @Override
                            public void onError(Throwable throwable) {
                                error.setValue(throwable.getMessage());
                            }
                        }
                )
        );
    }
}
