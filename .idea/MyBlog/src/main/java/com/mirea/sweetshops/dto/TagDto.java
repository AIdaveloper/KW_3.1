package com.mirea.sweetshops.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.mirea.sweetshops.models.Tag;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class TagDto implements Serializable {
    private Long tagId;
    private String name;

    public TagDto(Tag tag){
        this.name = tag.getName();
        this.tagId = tag.getId();
    }
}
