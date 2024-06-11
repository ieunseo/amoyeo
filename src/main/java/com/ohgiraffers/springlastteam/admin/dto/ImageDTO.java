package com.ohgiraffers.springlastteam.admin.dto;

import com.ohgiraffers.springlastteam.entity.GroupBuying;
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

    private int groupBuyingNo;

    public ImageDTO(String imgOriginFilename, String imgName, String imgPath) {
        this.imgOriginFilename = imgOriginFilename;
        this.imgName = imgName;
        this.imgPath = imgPath;
    }

}
