package com.example.quanlyguixe.screen.home;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.recyclerview.widget.DiffUtil;

import java.util.Objects;

public class ScreenEntry {
    @DrawableRes
    private int imageResourceID;

    @StringRes
    private int displayText;

    private ScreenCategoryEnum screenCategory;

    public ScreenEntry() {

    }

    public static DiffUtil.ItemCallback<ScreenEntry> getDiffCallback() {
        return new DiffUtil.ItemCallback<ScreenEntry>() {
            @Override
            public boolean areItemsTheSame(@NonNull ScreenEntry oldItem, @NonNull ScreenEntry newItem) {
                return oldItem.getDisplayText() == newItem.getDisplayText();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ScreenEntry oldItem, @NonNull ScreenEntry newItem) {
                return oldItem.equals(newItem);
            }
        };
    }

    public ScreenEntry(@DrawableRes int imageResourceID, @StringRes int displayText, ScreenCategoryEnum screenCategory) {
        this.imageResourceID = imageResourceID;
        this.displayText = displayText;
        this.screenCategory = screenCategory;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    public int getDisplayText() {
        return displayText;
    }

    public void setDisplayText(int displayText) {
        this.displayText = displayText;
    }

    public ScreenCategoryEnum getScreenCategory() {
        return screenCategory;
    }

    public void setScreenCategory(ScreenCategoryEnum screenCategory) {
        this.screenCategory = screenCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScreenEntry)) return false;
        ScreenEntry that = (ScreenEntry) o;
        return getImageResourceID() == that.getImageResourceID() && getDisplayText() == that.getDisplayText() && getScreenCategory() == that.getScreenCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getImageResourceID(), getDisplayText(), getScreenCategory());
    }

    @Override
    public String toString() {
        return "ScreenEntry{" +
                "imageResourceID=" + imageResourceID +
                ", displayText=" + displayText +
                ", screenCategory=" + screenCategory +
                '}';
    }
}

