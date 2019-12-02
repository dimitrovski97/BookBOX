package com.example.dimit.bookbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {
    private TextView textTitle;
    private TextView textAuthors;
    private TextView textDate;
    private TextView textDescription;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();
        String title = intent.getStringExtra("Title");
        String authors = intent.getStringExtra("Authors");
        String date = intent.getStringExtra("Date");
        String description = intent.getStringExtra("description");

        String imageUrl = intent.getStringExtra("imageUrl");




        bindItems(title,authors,date,description,imageUrl);


    }

    private void bindItems(String title, String authors, String date, String description, String imageUrl) {
        this.textTitle = (TextView)findViewById(R.id.textTitle);
        this.textAuthors = (TextView)findViewById(R.id.textAuthors);
        this.textDate = (TextView)findViewById(R.id.textDate);
        this.textDescription = (TextView)findViewById(R.id.textDescription);
        this.imageView = (ImageView)findViewById(R.id.imageView1);
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .into(imageView);
        this.textTitle.setText(String.format("%s",title));
        this.textAuthors.setText(String.format("%s",authors));
        this.textDate.setText(String.format("%s",date));
        this.textDescription.setText(String.format("%s",description));

    }

}
