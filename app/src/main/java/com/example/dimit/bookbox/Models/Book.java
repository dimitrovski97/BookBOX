package com.example.dimit.bookbox.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("volumeInfo")
    @Expose
    private VolumeInfoConvertor volumeInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public VolumeInfoConvertor getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfoConvertor volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
