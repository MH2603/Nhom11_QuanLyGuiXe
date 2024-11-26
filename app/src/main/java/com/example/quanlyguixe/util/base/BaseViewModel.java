package com.example.quanlyguixe.util.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quanlyguixe.util.interfaces.IResultListener;

import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class BaseViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    protected MutableLiveData<Boolean> isLoading = new MutableLiveData<>(false);
    protected MutableLiveData<String> error = new MutableLiveData<>();

    protected final MutableLiveData<Boolean> _backToPreviousScreen = new MutableLiveData<>(false);

    public LiveData<Boolean> getBackToPreviousScreen() {
        return _backToPreviousScreen;
    }

    public void resetBackToPreviousScreenState() {
        _backToPreviousScreen.setValue(false);
    }

    public MutableLiveData<Boolean> getLoadingState() {
        return isLoading;
    }

    public MutableLiveData<String> checkHasError() {
        return error;
    }

    public @Inject BaseViewModel() {
    }

    /*
     *  Phương thức này dùng để đăng ký thực thi một tác vụ (Gọi API, Thao tác Database, ...)
     *
     *   Disposable: một tác vụ có thể hủy ngay khi không cần thực thi tác vụ này nữa
     *
     *   Ví dụ: khi thoát khỏi 1 màn hình thì các tác vụ đang chạy của màn hình này sẽ
     *   đồng loạt bị hủy do không cần sử dụng đến nữa
     * */
    protected void registerDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    /*
     *  Phương thức này thực thi tác vụ thời gian chạy ngầm không cần hiển thị lên giao diện
     *
     *  Single<>: tác vụ chạy 1 lần là đưa ra kết quả
     *  IResultListener: callback xử lý kết quả trả về
     *
     *   return => 1 tác vụ thực thi
     * */
    public <T> Disposable executeTask(
            Single<T> task,
            IResultListener<T> resultListener
    ) {
        return task
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultListener::onSuccess, resultListener::onError);
    }

    /*
     *  Phương thức này thực thi tác vụ thời gian chạy tốn nhiều thời gian
     *  cần hiển thị giao diện "ĐANG TẢI" để người dùng đợi
     *
     *  Single<>: tác vụ chạy 1 lần là đưa ra kết quả
     *  IResultListener: callback xử lý kết quả trả về
     *
     *   return => 1 tác vụ thực thi (có hiển thị giao diện "ĐANG TẢI")
     * */
    public <T> Disposable executeTaskWithLoading(
            Single<T> task,
            IResultListener<T> resultListener
    ) {
        return task
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> showLoading())
                .doOnSuccess(disposable -> hideLoading())
                .doOnError(disposable -> hideLoading())
                .subscribe(resultListener::onSuccess, resultListener::onError);
    }

    protected void showLoading() {
        isLoading.setValue(true);
    }

    protected void hideLoading() {
        isLoading.setValue(false);
    }


    /*
     *  Hủy đồng loạt các tác vụ khi thoát màn hình
     *  onCleared() sử dụng khi ViewModel này không còn cần thiết để sử dụng
     * */
    @Override
    protected void onCleared() {
        compositeDisposable.clear();
        super.onCleared();
    }
}