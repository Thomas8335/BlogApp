package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BlogDetailActivity extends AppCompatActivity implements BlogDetailPresenter.View {

    private TextView titleTextView, bodyTextView;
    private BlogDetailPresenter blogDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_detail);

        titleTextView = findViewById(R.id.detail_title);
        bodyTextView = findViewById(R.id.detail_body);

        blogDetailPresenter = new BlogDetailPresenter(this);
        blogDetailPresenter.displayBlogDetail(getIntent());

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            blogDetailPresenter.handleNavigationItemSelected(itemId);
            return true;
        });
    }

    @Override
    public void displayBlog(String title, String body) {
        titleTextView.setText(title);
        bodyTextView.setText(body);
    }
}