package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private BlogPostAdapter blogPostAdapter;
    private RecyclerView recyclerView;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenter(this);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        blogPostAdapter = new BlogPostAdapter(this, new ArrayList<>());  // Initialize with empty list
        recyclerView.setAdapter(blogPostAdapter);

        mainPresenter.loadBlogs();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_add_blog) {
                Intent intent = new Intent(this, CreateBlogActivity.class);
                startActivity(intent);
            }

            return true;
        });
    }

    @Override
    public void updateBlogs(List<BlogPost> blogs) {
        blogPostAdapter.setBlogs(blogs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}