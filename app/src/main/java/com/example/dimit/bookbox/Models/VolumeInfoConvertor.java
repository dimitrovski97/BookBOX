package com.example.dimit.bookbox.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class VolumeInfoConvertor {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("authors")
    @Expose
    private AuthorsConvertor authors;
    @SerializedName("publisher")
    @Expose
    private String publisher;
    @SerializedName("publishedDate")
    @Expose
    private String publishedDate;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("imageLinks")
    @Expose
    private ImageLinkConvertor imageLinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public AuthorsConvertor getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorsConvertor authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ImageLinkConvertor getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinkConvertor imageLinks) {
        this.imageLinks = imageLinks;
    }
}
