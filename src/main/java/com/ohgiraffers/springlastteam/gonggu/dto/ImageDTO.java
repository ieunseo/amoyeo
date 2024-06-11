package com.ohgiraffers.springlastteam.gonggu.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ImageDTO {

    private String imgOriginFilename;
    private String imgName;
    private String imgPath;

    public ImageDTO(String imgOriginFilename, String imgName, String imgPath) {
        this.imgOriginFilename = imgOriginFilename;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }
}
