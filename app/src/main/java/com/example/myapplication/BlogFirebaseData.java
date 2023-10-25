package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class BlogFirebaseData {

    private DatabaseReference myBlogDbRef;
    public static final String BlogDataTag = "BLOG DATA";
    private List<BlogPost> blogList;

    public DatabaseReference open() {
        // Initialize database and reference
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myBlogDbRef = database.getReference(BlogDataTag);
        blogList = new ArrayList<>();
        return myBlogDbRef;
    }

    public void close() {
        // Any cleanup if necessary
    }

    public BlogPost createBlog(String title, String body) {
        String key = myBlogDbRef.push().getKey(); // Simplified key generation
        BlogPost newBlog = new BlogPost(title, body);
        newBlog.setId(key); // Set the ID
        myBlogDbRef.child(key).setValue(newBlog);
        return newBlog;
    }

    public void deleteBlog(BlogPost blog) {
        String key = blog.getId();
        myBlogDbRef.child(key).removeValue();
    }

    public List<BlogPost> updateBlogList(DataSnapshot dataSnapshot) {
        blogList.clear();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            BlogPost blog = data.getValue(BlogPost.class);
            blogList.add(blog);
        }
        return blogList;
    }

    public List<BlogPost> getAllBlogs() {
        return blogList;
    }

    public BlogPost getBlog(Integer position) {
        return blogList.get(position);
    }

    public Integer getNumberOfBlogs() {
        return blogList.size();
    }

}