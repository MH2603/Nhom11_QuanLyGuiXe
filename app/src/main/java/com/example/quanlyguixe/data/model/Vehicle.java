package com.example.quanlyguixe.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

@Entity
public class Vehicle implements Parcelable {
    @SerializedName("control_id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int vehicleControlID;

    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;

    @SerializedName("date_time_in")
    @Expose
    private Date dateTimeIn;

    @SerializedName("date_time_out")
    @Expose
    private Date dateTimeOut;

    @SerializedName("employee_id")
    @Expose
    private int employeeID;

    @SerializedName("ticket")
    @Expose
    private Tickets ticket;

    @SerializedName("area_id")
    @Expose
    private int areaID;

    @SerializedName("customer_name")
    @Expose
    private String customerName;

    public Vehicle() {
    }

    public int getVehicleControlID() {
        return vehicleControlID;
    }

    public void setVehicleControlID(int vehicleControlID) {
        this.vehicleControlID = vehicleControlID;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Date getDateTimeIn() {
        return dateTimeIn;
    }

    public void setDateTimeIn(Date dateTimeIn) {
        this.dateTimeIn = dateTimeIn;
    }

    public Date getDateTimeOut() {
        return dateTimeOut;
    }

    public void setDateTimeOut(Date dateTimeOut) {
        this.dateTimeOut = dateTimeOut;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Tickets getTicket() {
        return ticket;
    }

    public void setTicket(Tickets ticket) {
        this.ticket = ticket;
    }

    public int getAreaID() {
        return areaID;
    }

    public void setAreaID(int areaID) {
        this.areaID = areaID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;
        Vehicle vehicle = (Vehicle) o;
        return getVehicleControlID() == vehicle.getVehicleControlID() && getEmployeeID() == vehicle.getEmployeeID() && getAreaID() == vehicle.getAreaID() && Objects.equals(getVehicleNumber(), vehicle.getVehicleNumber()) && Objects.equals(getDateTimeIn(), vehicle.getDateTimeIn()) && Objects.equals(getDateTimeOut(), vehicle.getDateTimeOut()) && Objects.equals(getTicket(), vehicle.getTicket()) && Objects.equals(getCustomerName(), vehicle.getCustomerName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVehicleControlID(), getVehicleNumber(), getDateTimeIn(), getDateTimeOut(), getEmployeeID(), getTicket(), getAreaID(), getCustomerName());
    }

    public Vehicle(int vehicleControlID, String vehicleNumber, Date dateTimeIn, Date dateTimeOut, int employeeID, Tickets ticket, int areaID, String customerName) {
        this.vehicleControlID = vehicleControlID;
        this.vehicleNumber = vehicleNumber;
        this.dateTimeIn = dateTimeIn;
        this.dateTimeOut = dateTimeOut;
        this.employeeID = employeeID;
        this.ticket = ticket;
        this.areaID = areaID;
        this.customerName = customerName;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleControlID=" + vehicleControlID +
                ", vehicleNumber='" + vehicleNumber + '\'' +
                ", dateTimeIn=" + dateTimeIn +
                ", dateTimeOut=" + dateTimeOut +
                ", employeeID=" + employeeID +
                ", ticket=" + ticket +
                ", areaID=" + areaID +
                ", customerName='" + customerName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.vehicleControlID);
        dest.writeString(this.vehicleNumber);
        dest.writeLong(this.dateTimeIn != null ? this.dateTimeIn.getTime() : -1);
        dest.writeLong(this.dateTimeOut != null ? this.dateTimeOut.getTime() : -1);
        dest.writeInt(this.employeeID);
        dest.writeParcelable(this.ticket, flags);
        dest.writeInt(this.areaID);
        dest.writeString(this.customerName);
    }

    public void readFromParcel(Parcel source) {
        this.vehicleControlID = source.readInt();
        this.vehicleNumber = source.readString();
        long tmpDateTimeIn = source.readLong();
        this.dateTimeIn = tmpDateTimeIn == -1 ? null : new Date(tmpDateTimeIn);
        long tmpDateTimeOut = source.readLong();
        this.dateTimeOut = tmpDateTimeOut == -1 ? null : new Date(tmpDateTimeOut);
        this.employeeID = source.readInt();
        this.ticket = source.readParcelable(Tickets.class.getClassLoader());
        this.areaID = source.readInt();
        this.customerName = source.readString();
    }

    protected Vehicle(Parcel in) {
        this.vehicleControlID = in.readInt();
        this.vehicleNumber = in.readString();
        long tmpDateTimeIn = in.readLong();
        this.dateTimeIn = tmpDateTimeIn == -1 ? null : new Date(tmpDateTimeIn);
        long tmpDateTimeOut = in.readLong();
        this.dateTimeOut = tmpDateTimeOut == -1 ? null : new Date(tmpDateTimeOut);
        this.employeeID = in.readInt();
        this.ticket = in.readParcelable(Tickets.class.getClassLoader());
        this.areaID = in.readInt();
        this.customerName = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel source) {
            return new Vehicle(source);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

    public static DiffUtil.ItemCallback<Vehicle> getDiffCallback() {
        return new DiffUtil.ItemCallback<Vehicle>() {
            @Override
            public boolean areItemsTheSame(@NonNull Vehicle oldItem, @NonNull Vehicle newItem) {
                return oldItem.getVehicleControlID() == newItem.getVehicleControlID();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Vehicle oldItem, @NonNull Vehicle newItem) {
                return oldItem.equals(newItem);
            }
        };
    }

}
