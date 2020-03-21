package com.example.instabybakosta;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.security.PrivateKey;

import static java.sql.Types.NULL;

@SuppressWarnings("serial")
public class News implements Parcelable {
    private String author;
    private String title;
    private String postData;
    private String photoLink;
    private String viewsCount;//likes
    private String commentsCount;//comments



    private String ava;
    private int postPhoto;
    private boolean postLiked = false;


    //-----------------------------------------N E W S----------------------------------------------
    public News(
            String postData,
            String author,
            String title,
            String viewsCount,
            String commentsCount,
            String photoLink,
            String ava
    ) {
        this.photoLink = photoLink;
        this.postData = postData;
        this.author = author;
        this.title = title;
        this.viewsCount = viewsCount;
        this.commentsCount = commentsCount;
        this.ava = ava;


    }
//-----------------------------------------N E W S--------------------------------------------------

    //-----------------------------------------GETTER/SETTER----------------------------------------

    public boolean isPostLiked() {
        return postLiked;
    }

    public void setPostLiked(boolean postLiked) {
        this.postLiked = postLiked;
    }
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(String viewsCount) {
        this.viewsCount = viewsCount;
    }

    public String getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(String commentsCount) {
        this.commentsCount = commentsCount;
    }

    public String getAva() {
        return ava;
    }

    public void setAva(String ava) {
        this.ava = ava;
    }

    public int getPostPhoto() {
        return postPhoto;
    }

    public void setPostPhoto(int postPhoto) {
        this.postPhoto = postPhoto;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }


    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
//-----------------------------------------GETTER/SETTER--------------------------------------------


    @Override
    public String toString() {
        return "News{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", postData='" + postData + '\'' +
                ", viewsCount='" + viewsCount + '\'' +
                ", commentsCount='" + commentsCount + '\'' +
                ", ava='" + ava + '\'' +
                ", postPhoto=" + postPhoto +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.author);
        dest.writeString(this.title);
        dest.writeString(this.viewsCount);
        dest.writeString(this.commentsCount);

        dest.writeString(this.ava);
        dest.writeInt(this.postPhoto);

        dest.writeString(this.postData);
        dest.writeString(this.photoLink);

    }

    protected News(Parcel in) {
        this.author = in.readString();
        this.title = in.readString();
        this.viewsCount = in.readString();
        this.commentsCount = in.readString();

        this.ava = in.readString();
        this.postPhoto = in.readInt();
        this.postData = in.readString();

        this.photoLink = in.readString();
    }

    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {

            return new News(source);
        }

        @Override
        public News[] newArray(int size) {

            return new News[size];
        }
    };
}
