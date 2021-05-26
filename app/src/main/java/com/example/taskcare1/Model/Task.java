package com.example.taskcare1.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tbl_tugas")
public class Task implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int nmr;

    @ColumnInfo(name = "tugas")
    public String tugas;

    @ColumnInfo(name = "deskripsi")
    public String deskripsi;

    @ColumnInfo(name = "tenggat")
    public String tenggat;

    @ColumnInfo(name = "waktu")
    public String waktu;

    public Task() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nmr);
        dest.writeString(this.tugas);
        dest.writeString(this.deskripsi);
        dest.writeString(this.tenggat);
        dest.writeString(this.waktu);
    }

    protected Task(Parcel in) {
        this.nmr = in.readInt();
        this.tugas = in.readString();
        this.deskripsi = in.readString();
        this.tenggat = in.readString();
        this.waktu = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}
