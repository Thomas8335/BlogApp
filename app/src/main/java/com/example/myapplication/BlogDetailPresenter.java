package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

public class BlogDetailPresenter {

    private View view;

    public BlogDetailPresenter(View view) {
        this.view = view;
    }

    public void displayBlogDetail(Intent intent) {
        String title = intent.getStringExtra("title");
        String body = intent.getStringExtra("body");
        view.displayBlog(title, body);
    }

    public void handleNavigationItemSelected(int itemId) {
        if (itemId == R.id.nav_home) {
            Intent intent = new Intent((Context) view, MainActivity.class);
            ((Context) view).startActivity(intent);
        } else if (itemId == R.id.nav_add_blog) {
            Intent intent = new Intent((Context) view, CreateBlogActivity.class);
            ((Context) view).startActivity(intent);
        }
    }

    public interface View {
        void displayBlog(String title, String body);
    }
}