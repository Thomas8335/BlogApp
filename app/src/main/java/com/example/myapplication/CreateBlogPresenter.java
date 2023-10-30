package com.example.myapplication;

import android.content.Context;
import android.content.Intent;

/**
 * Presenter for handling the logic of the CreateBlogActivity.
 * Presenter generated by AI
 */
public class CreateBlogPresenter {

    private View view;
    private BlogFirebaseData blogFirebaseData;

    /**
     * Constructor initializing the presenter with its view and data source.
     *
     * @param view the associated view (usually an activity or fragment).
     * @param blogFirebaseData the data source for blog operations.
     */
    public CreateBlogPresenter(View view, BlogFirebaseData blogFirebaseData) {
        this.view = view;
        this.blogFirebaseData = blogFirebaseData;
        this.blogFirebaseData.open();
    }

    /**
     * Handles the logic when the post button is clicked.
     *
     * @param title the title of the blog post.
     * @param body the body/content of the blog post.
     */
    public void onPostButtonClicked(String title, String body) {
        if (!title.isEmpty() && !body.isEmpty()) {
            blogFirebaseData.createBlog(title, body);
            view.closeActivity();
        }
    }

    /**
     * Navigates to the main activity when the home navigation is selected.
     */
    public void onHomeNavigationSelected() {
        Intent intent = new Intent((Context) view, MainActivity.class);
        ((Context) view).startActivity(intent);
    }

    /**
     * Navigates back to the create blog activity when the add blog navigation is selected.
     */
    public void onAddBlogNavigationSelected() {
        Intent intent = new Intent((Context) view, CreateBlogActivity.class);
        ((Context) view).startActivity(intent);
    }

    /**
     * Interface representing the view, providing a method to close the activity.
     */
    public interface View {
        void closeActivity();
    }
}
