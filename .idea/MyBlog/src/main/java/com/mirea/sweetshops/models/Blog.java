package com.mirea.sweetshops.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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


    @Column(nullable = false, name = "tag")
    private Long tag;

    @Column(nullable = false, name = "creator")
    private Long creator;

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
