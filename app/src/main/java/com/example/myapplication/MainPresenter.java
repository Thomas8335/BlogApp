package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainPresenter {

    private BlogFirebaseData blogFirebaseData;
    private View view;

    public MainPresenter(View view) {
        this.view = view;
        this.blogFirebaseData = new BlogFirebaseData();
    }

    public void loadBlogs() {
        DatabaseReference blogDbRef = blogFirebaseData.open();

        blogDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<BlogPost> updatedBlogs = blogFirebaseData.updateBlogList(dataSnapshot);
                view.updateBlogs(updatedBlogs);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors.
            }
        });
    }

    public interface View {
        void updateBlogs(List<BlogPost> blogs);
    }

    public void onDestroy() {
        blogFirebaseData.close();
    }
}
