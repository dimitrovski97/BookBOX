package com.example.dimit.bookbox.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dimit.bookbox.Models.Book;
import com.example.dimit.bookbox.R;

public class BookViewHolder extends RecyclerView.ViewHolder {
    public TextView textTitle;
    public TextView textAuthors;
    public TextView textPublished;
    public ImageView imageView;
    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle = (TextView) itemView.findViewById(R.id.text_title);
        textAuthors = (TextView) itemView.findViewById(R.id.text_authors);
        textPublished = (TextView) itemView.findViewById(R.id.text_published);
        imageView = (ImageView) itemView.findViewById(R.id.imageView1);
    }
    public void bind(Book book){
        Glide.with(imageView.getContext())
                .load(book.getVolumeInfo().getImageLinks().getSmallThumbnail())
                .into(this.imageView);
        textTitle.setText(book.getVolumeInfo().getTitle());
        textAuthors.setText(book.getVolumeInfo().getAuthors().toString());
        textPublished.setText(book.getVolumeInfo().getPublisher());

    }
}
