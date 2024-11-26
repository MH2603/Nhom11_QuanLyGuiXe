package com.example.quanlyguixe.data.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

@Entity
public class Reports implements Parcelable {
    @SerializedName("report_id")
    @Expose
    @PrimaryKey
    private int reportID;

    @SerializedName("report_name")
    @Expose
    private String reportName;

    public Reports() {

    }

    public Reports(int reportID, String reportName) {
        this.reportID = reportID;
        this.reportName = reportName;
    }

    public int getReportID() {
        return reportID;
    }

    public void setReportID(int reportID) {
        this.reportID = reportID;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reports)) return false;
        Reports reports = (Reports) o;
        return getReportID() == reports.getReportID() && Objects.equals(getReportName(), reports.getReportName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getReportID(), getReportName());
    }

    @Override
    public String toString() {
        return "Reports{" +
                "reportID=" + reportID +
                ", reportName='" + reportName + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.reportID);
        dest.writeString(this.reportName);
    }

    public void readFromParcel(Parcel source) {
        this.reportID = source.readInt();
        this.reportName = source.readString();
    }

    protected Reports(Parcel in) {
        this.reportID = in.readInt();
        this.reportName = in.readString();
    }

    public static final Parcelable.Creator<Reports> CREATOR = new Parcelable.Creator<Reports>() {
        @Override
        public Reports createFromParcel(Parcel source) {
            return new Reports(source);
        }

        @Override
        public Reports[] newArray(int size) {
            return new Reports[size];
        }
    };
}

