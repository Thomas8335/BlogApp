package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

public class CreateBlogPresenter {

    private View view;
    private BlogFirebaseData blogFirebaseData;

    public CreateBlogPresenter(View view, BlogFirebaseData blogFirebaseData) {
        this.view = view;
        this.blogFirebaseData = blogFirebaseData;
        this.blogFirebaseData.open();
    }

    public void onPostButtonClicked(String title, String body) {
        if (!title.isEmpty() && !body.isEmpty()) {
            blogFirebaseData.createBlog(title, body);
            view.closeActivity();
        }
    }

    public void onHomeNavigationSelected() {
        Intent intent = new Intent((Context) view, MainActivity.class);
        ((Context) view).startActivity(intent);
    }

    public void onAddBlogNavigationSelected() {
        Intent intent = new Intent((Context) view, CreateBlogActivity.class);
        ((Context) view).startActivity(intent);
    }

    public interface View {
        void closeActivity();
    }
}