package com.mirea.sweetshops.dto;

import com.mirea.sweetshops.services.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.mirea.sweetshops.models.Blog;
import com.mirea.sweetshops.models.User;
import com.mirea.sweetshops.services.TagService;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BlogDto implements Serializable {
    private Long id;
    private String title;
    private String summary;
    private String text;
//    private float rating;
//    private int views;
    private Long creatorId;
    private String creatorName;
    private Long tag;

    public BlogDto(Blog blog, UserService us, TagService tagService) /*throws Exception*/ {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.text = blog.getText();
        this.creatorId = blog.getCreator();
        this.creatorName = us.findUserById(creatorId).getUsername();
//        this.rating = blog.getRating();
//        this.views = blog.getViews();
        this.tag = blog.getTag();

    }
}
