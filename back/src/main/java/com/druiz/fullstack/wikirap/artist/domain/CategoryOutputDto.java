package com.druiz.fullstack.wikirap.artist.domain;

import lombok.Data;

import java.util.List;

@Data
public class CategoryOutputDto {

    private int idCategory;
    private String name;
    private List<String> albumNames;
}
