package ru.mirea.myblog_e.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "blogs")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "summary")
    private String summary;

    @Column(nullable = false, name = "text", columnDefinition = "TEXT")
    private String text;

//    @Column(nullable = false, name = "rating")
//    private float rating;

//    @Column(nullable = false, name = "views")
//    private int views;


    @Column(nullable = false, name = "tag")
    private int tag;

    @ManyToOne(fetch = FetchType.EAGER)
    private User creator;

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", text='" + text + '\'' +
                ", tag=" + tag +
                ", creator=" + creator +
                '}';
    }
}
