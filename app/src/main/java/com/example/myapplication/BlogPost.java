package com.example.myapplication;
/**
 * Represents a blog post with an ID, title, and body content.
 */
public class BlogPost {

    private String id;
    private String title;
    private String body;

    /**
     * Default constructor for BlogPost.
     */
    public BlogPost() {}

    /**
     * Constructs a new instance of BlogPost with the specified title and body.
     *
     * @param title The title of the blog post.
     * @param body The content/body of the blog post.
     */
    public BlogPost(String title, String body) {
        this.title = title;
        this.body = body;
    }

    /**
     * Retrieves the title of the blog post.
     *
     * @return The title of the blog post.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the blog post.
     *
     * @param title The title to set for the blog post.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the content/body of the blog post.
     *
     * @return The content/body of the blog post.
     */
    public String getBody() {
        return body;
    }

    /**
     * Sets the content/body of the blog post.
     *
     * @param body The content to set for the blog post.
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Retrieves the ID of the blog post.
     *
     * @return The ID of the blog post.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the ID for the blog post.
     *
     * @param id The ID to set for the blog post.
     */
    public void setId(String id) {
        this.id = id;
    }

}
