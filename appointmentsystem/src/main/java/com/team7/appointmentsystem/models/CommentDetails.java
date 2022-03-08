package com.team7.appointmentsystem.models;

import com.team7.appointmentsystem.entity.Comments;

public class CommentDetails {
    private String name;
    private String profileImgPath;
    private Comments comment;

    public CommentDetails(String name, String profileImgPath, Comments comment) {
        this.name = name;
        this.profileImgPath = profileImgPath;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImgPath() {
        return profileImgPath;
    }

    public void setProfileImgPath(String profileImgPath) {
        this.profileImgPath = profileImgPath;
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }
}
