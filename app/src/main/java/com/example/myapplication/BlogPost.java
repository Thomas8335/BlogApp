package com.example.myapplication;

public class BlogPost {

    private String id;



    private String title;
    private String body;


    public BlogPost() {}
    public BlogPost(String title, String body) {
        this.title = title;
        this.body = body;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
