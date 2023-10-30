package com.example.myapplication;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides an interface for managing blog data with Firebase.
 */
public class BlogFirebaseData {

    private DatabaseReference myBlogDbRef;
    public static final String BlogDataTag = "BLOG DATA";
    private List<BlogPost> blogList;

    /**
     * Opens the Firebase reference for blog data and initializes related resources.
     *
     * @return The opened DatabaseReference for blog data.
     */
    public DatabaseReference open() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myBlogDbRef = database.getReference(BlogDataTag);
        blogList = new ArrayList<>();
        return myBlogDbRef;
    }

    /**
     * Cleans up any resources or references associated with Firebase data, if necessary.
     */
    public void close() {
    }

    /**
     * Creates a new blog entry in Firebase.
     *
     * @param title The title of the blog post.
     * @param body The content of the blog post.
     * @return The created BlogPost object.
     */
    public BlogPost createBlog(String title, String body) {
        String key = myBlogDbRef.push().getKey();
        BlogPost newBlog = new BlogPost(title, body);
        newBlog.setId(key);
        myBlogDbRef.child(key).setValue(newBlog);
        return newBlog;
    }

    /**
     * Removes a specific blog entry from Firebase.
     *
     * @param blog The blog post to be deleted.
     */
    public void deleteBlog(BlogPost blog) {
        String key = blog.getId();
        myBlogDbRef.child(key).removeValue();
    }

    /**
     * Updates the internal list of blog posts based on the latest snapshot from Firebase.
     *
     * @param dataSnapshot The latest snapshot of blog data from Firebase.
     * @return The updated list of blog posts.
     */
    public List<BlogPost> updateBlogList(DataSnapshot dataSnapshot) {
        blogList.clear();
        for (DataSnapshot data : dataSnapshot.getChildren()) {
            BlogPost blog = data.getValue(BlogPost.class);
            blogList.add(blog);
        }
        return blogList;
    }

    /**
     * Retrieves the complete list of blog posts.
     *
     * @return The list of all blog posts.
     */
    public List<BlogPost> getAllBlogs() {
        return blogList;
    }

    /**
     * Retrieves a specific blog post based on its position in the list.
     *
     * @param position The position/index of the blog post in the list.
     * @return The BlogPost object at the specified position.
     */
    public BlogPost getBlog(Integer position) {
        return blogList.get(position);
    }

    /**
     * Provides the total number of blog posts available.
     *
     * @return The number of blog posts.
     */
    public Integer getNumberOfBlogs() {
        return blogList.size();
    }

}