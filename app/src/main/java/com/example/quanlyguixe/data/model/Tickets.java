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
public class Tickets  implements Parcelable {
    @SerializedName("ticket_id")
    @Expose
    @PrimaryKey(autoGenerate = true)
    private int ticketID;

    @SerializedName("ticket_type")
    @Expose
    private String ticketType;

    @SerializedName("price")
    @Expose
    private Long price;

    @SerializedName("expiration_date")
    @Expose
    private Date expirationDate;

    public Tickets() {

    }

    public static DiffUtil.ItemCallback<Tickets> getDiffCallback() {
        return new DiffUtil.ItemCallback<Tickets>() {
            @Override
            public boolean areItemsTheSame(@NonNull Tickets oldItem, @NonNull Tickets newItem) {
                return oldItem.getTicketID() == newItem.getTicketID();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Tickets oldItem, @NonNull Tickets newItem) {
                return oldItem.equals(newItem);
            }
        };
    }

    public Tickets(int ticketID, String ticketType, Long price, Date expirationDate) {
        this.ticketID = ticketID;
        this.ticketType = ticketType;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tickets)) return false;
        Tickets tickets = (Tickets) o;
        return getTicketID() == tickets.getTicketID() && Double.compare(tickets.getPrice(), getPrice()) == 0
                && Objects.equals(getTicketType(), tickets.getTicketType()) && Objects.equals(getExpirationDate(), tickets.getExpirationDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTicketID(), getTicketType(), getPrice(), getExpirationDate());
    }

    @Override
    public String toString() {
        return this.ticketType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ticketID);
        dest.writeString(this.ticketType);
        dest.writeDouble(this.price);
        dest.writeLong(this.expirationDate != null ? this.expirationDate.getTime() : -1);
    }

    public void readFromParcel(Parcel source) {
        this.ticketID = source.readInt();
        this.ticketType = source.readString();
        this.price = source.readLong();
        long tmpExpirationDate = source.readLong();
        this.expirationDate = tmpExpirationDate == -1 ? null : new Date(tmpExpirationDate);
    }

    protected Tickets(Parcel in) {
        this.ticketID = in.readInt();
        this.ticketType = in.readString();
        this.price = in.readLong();
        long tmpExpirationDate = in.readLong();
        this.expirationDate = tmpExpirationDate == -1 ? null : new Date(tmpExpirationDate);
    }

    public static final Parcelable.Creator<Tickets> CREATOR = new Parcelable.Creator<Tickets>() {
        @Override
        public Tickets createFromParcel(Parcel source) {
            return new Tickets(source);
        }

        @Override
        public Tickets[] newArray(int size) {
            return new Tickets[size];
        }
    };
}
