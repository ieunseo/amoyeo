package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notices")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private int id;

    @Column(name = "notice_title", nullable = false)
    private String title;

    @Column(name = "notice_content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Users author;

    @Column(name = "author_name", nullable = false)
    private String authorName;

    public Notice(String title, String content, Users author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.authorName = author.getUserName();
    }
}
