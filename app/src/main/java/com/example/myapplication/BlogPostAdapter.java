package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogPostAdapter extends RecyclerView.Adapter<BlogPostAdapter.BlogViewHolder> {

    private List<BlogPost> blogList;
    private Context context;
    private BlogFirebaseData blogFirebaseData = new BlogFirebaseData();

    public BlogPostAdapter(Context context, List<BlogPost> blogList) {
        this.context = context;
        this.blogList = blogList;
    }

    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blog_item, parent, false);
        return new BlogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        BlogPost currentBlog = blogList.get(position);
        holder.titleTextView.setText(currentBlog.getTitle());
        //holder.bodyTextView.setText(currentBlog.getBody());

        // Setting up the delete functionality
        holder.deleteButton.setOnClickListener(v -> {
            blogFirebaseData.open();
            BlogPost blogPostToDelete = blogList.get(position);
            blogFirebaseData.deleteBlog(blogPostToDelete);
            notifyDataSetChanged();
        });

        // Setting up the click for detailed view
        holder.itemView.setOnClickListener(v -> {
            // Your logic for opening the detailed view
            Intent intent = new Intent(context, BlogDetailActivity.class);
            intent.putExtra("title", currentBlog.getTitle());
            intent.putExtra("body", currentBlog.getBody());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return blogList.size();
    }

    public void setBlogs(List<BlogPost> blogs) {
        this.blogList = blogs;
        notifyDataSetChanged();
    }

    static class BlogViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView bodyTextView;
        Button deleteButton;

        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.blog_title);
           // bodyTextView = itemView.findViewById(R.id.blog_body);
            deleteButton = itemView.findViewById(R.id.delete_button);
        }
    }
}