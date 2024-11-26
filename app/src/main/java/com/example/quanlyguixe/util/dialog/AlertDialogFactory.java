package com.example.quanlyguixe.util.dialog;

import android.app.AlertDialog;
import android.content.Context;

import com.example.quanlyguixe.util.interfaces.IOnAcceptListener;

public class AlertDialogFactory {

    public static AlertDialog createClearAllInfoDialog(Context context, IOnAcceptListener onAcceptListener) {
        return new AlertDialog.Builder(context)
                .setTitle("Xóa thông tin")
                .setMessage("Bạn có muốn xóa tất cả thông tin không?")
                .setPositiveButton("Có", (dialog, which) -> {
                    onAcceptListener.onAccept();
                })
                .setNegativeButton("Không", (dialog, which) -> {})
                .create();
    }

    public static AlertDialog createOnBackToPreviousScreenDialog(Context context, IOnAcceptListener onAcceptListener) {
        return new AlertDialog.Builder(context)
                .setTitle("Thoát màn hình")
                .setMessage("Bạn có muốn thoát màn hiện tại không? Tất cả các thông tin sẽ không được lưu!!")
                .setPositiveButton("Có", (dialog, which) -> {
                    onAcceptListener.onAccept();
                })
                .setNegativeButton("Không", (dialog, which) -> {})
                .create();
    }

    public static AlertDialog createNormalMessageDialog(Context context, String message, IOnAcceptListener onAcceptListener) {
        return new AlertDialog.Builder(context)
                .setTitle("Thông báo")
                .setMessage(message)
                .setPositiveButton("Có", (dialog, which) -> {
                    onAcceptListener.onAccept();
                })
                .setNegativeButton("Không", (dialog, which) -> {})
                .create();
    }
}
