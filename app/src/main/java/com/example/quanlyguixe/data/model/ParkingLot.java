package com.example.quanlyguixe.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity
public class ParkingLot implements Parcelable {
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("availabelSlot")
    @Expose
    private Long availabelSlot;

    @SerializedName("slotMax")
    @Expose
    private Long slotMax;

    public ParkingLot() {

    }

    public static DiffUtil.ItemCallback<ParkingLot> getDiffCallback() {
        return new DiffUtil.ItemCallback<ParkingLot>() {
            @Override
            public boolean areItemsTheSame(@NonNull ParkingLot oldItem, @NonNull ParkingLot newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull ParkingLot oldItem, @NonNull ParkingLot newItem) {
                return oldItem.id == newItem.id && oldItem.slotMax == newItem.slotMax && oldItem.name == newItem.name;
            }
        };
    }

    public ParkingLot(int id, String name, Long availabelSlot, Long slotMax) {
        this.id = id;
        this.name = name;
        this.availabelSlot = availabelSlot;
        this.slotMax = slotMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAvailabelSlot() {
        return availabelSlot;
    }

    public void setAvailabelSlot(Long availabelSlot) {
        this.availabelSlot = availabelSlot;
    }

    public Long getSlotMax() {
        return slotMax;
    }

    public void setSlotMax(Long slotMax) {
        this.slotMax = slotMax;
    }

    @Override
    public String toString() {
        return "ParkingLots{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availabelSlot=" + availabelSlot +
                ", slotMax=" + slotMax +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingLot)) return false;
        ParkingLot that = (ParkingLot) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAvailabelSlot(), getSlotMax());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeValue(this.availabelSlot);
        dest.writeValue(this.slotMax);
    }

    public void readFromParcel(Parcel source) {
        this.id = source.readInt();
        this.name = source.readString();
        this.availabelSlot = (Long) source.readValue(Long.class.getClassLoader());
        this.slotMax = (Long) source.readValue(Long.class.getClassLoader());
    }

    protected ParkingLot(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.availabelSlot = (Long) in.readValue(Long.class.getClassLoader());
        this.slotMax = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<ParkingLot> CREATOR = new Creator<ParkingLot>() {
        @Override
        public ParkingLot createFromParcel(Parcel source) {
            return new ParkingLot(source);
        }

        @Override
        public ParkingLot[] newArray(int size) {
            return new ParkingLot[size];
        }
    };
}
