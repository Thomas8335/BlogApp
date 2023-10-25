package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CreateBlogActivity extends AppCompatActivity implements CreateBlogPresenter.View {

    private EditText titleEditText, bodyEditText;
    private Button postButton;
    private CreateBlogPresenter createBlogPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        titleEditText = findViewById(R.id.title_edit_text);
        bodyEditText = findViewById(R.id.body_edit_text);
        postButton = findViewById(R.id.post_button);

        createBlogPresenter = new CreateBlogPresenter(this, new BlogFirebaseData());

        postButton.setOnClickListener(v -> {
            createBlogPresenter.onPostButtonClicked(
                    titleEditText.getText().toString(),
                    bodyEditText.getText().toString()
            );
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                createBlogPresenter.onHomeNavigationSelected();
            } else if (itemId == R.id.nav_add_blog) {
                createBlogPresenter.onAddBlogNavigationSelected();
            }

            return true;
        });
    }

    @Override
    public void closeActivity() {
        finish();
    }
}