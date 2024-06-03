package com.ohgiraffers.springlastteam.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "image")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Image implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_no")
    private int imgNo;

    @Column(name = "img_origin_filename")
    private String imgOriginFilename;

    @Column(name = "img_name")
    private String imgName;

    @Column(name = "img_path")
    private String imgPath;

    @ManyToOne
    @JoinColumn(name = "buying_no", nullable = false)
    private GroupBuying groupBuying;
}
