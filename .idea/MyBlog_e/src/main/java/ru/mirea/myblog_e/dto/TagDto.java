package ru.mirea.myblog_e.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mirea.myblog_e.models.Tag;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TagDto implements Serializable {
    private Long tagId;
    private String name;

    public TagDto(Tag tag){
        this.name = tag.getName();
        this.tagId = tag.getTagId();
    }
}
