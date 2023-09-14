package ru.mirea.myblog_e.dto;

import ru.mirea.myblog_e.models.Blog;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.myblog_e.models.Tag;
import ru.mirea.myblog_e.models.User;
import ru.mirea.myblog_e.services.TagService;

import java.io.Serializable;
import java.util.Set;

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
    private User creator;
    private Integer tags;

    public BlogDto(Blog blog, TagService tagService) /*throws Exception*/ {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.summary = blog.getSummary();
        this.text = blog.getText();
//        this.rating = blog.getRating();
//        this.views = blog.getViews();
        this.tags = blog.getTag();
        this.creator = blog.getCreator();

    }
}
