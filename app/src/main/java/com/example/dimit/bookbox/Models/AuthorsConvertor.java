package com.example.dimit.bookbox.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

class AuthorsConvertor {
    @SerializedName("authors")
    @Expose
    List<String> authors;

    public AuthorsConvertor() {
        authors = new ArrayList<String>();
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Authors {" + authors +'}';
    }
}
