package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

/**
 * The main activity for displaying the list of blog posts.
 * Presenter integration and bottom nav bar generated by AI
 */
public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private BlogPostAdapter blogPostAdapter;
    private RecyclerView recyclerView;
    private MainPresenter mainPresenter;

    /**
     * Called when the activity is starting.
     * Initializes the main presenter, sets up the RecyclerView and handles bottom navigation.
     *
     * @param savedInstanceState if the activity is being re-initialized after previously being shut down
     *                           then this Bundle contains the data it most recently supplied in
     *                           {@link #onSaveInstanceState}. Otherwise, it is null.
     */
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

    /**
     * Update the blogs displayed in the RecyclerView.
     *
     * @param blogs a list of {@link BlogPost} objects to display.
     */
    @Override
    public void updateBlogs(List<BlogPost> blogs) {
        blogPostAdapter.setBlogs(blogs);
    }

    /**
     * Called before the activity is destroyed.
     * Notifies the main presenter about the destruction.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.onDestroy();
    }
}
