package com.example.newslux;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShowNews extends AppCompatActivity {

    TextView showDesc;
    TextView showTitle;
    TextView showAuthor;
    TextView showCountry;
    ImageView showImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);

        Intent intent = getIntent();

        showDesc = findViewById(R.id.descView);
        showTitle = findViewById(R.id.titleView);
        showAuthor = findViewById(R.id.authorView);
        showCountry = findViewById(R.id.countryView);
        showImage = findViewById(R.id.imageView2);

        showDesc.setText(intent.getStringExtra("desc"));
        showTitle.setText(intent.getStringExtra("title"));
        showAuthor.setText(intent.getStringExtra("author"));
        showCountry.setText(intent.getStringExtra("country"));
        Glide.with(getApplicationContext()).load(intent.getStringExtra("image")).into(showImage);

    }
}