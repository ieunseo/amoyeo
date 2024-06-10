package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private int likeId;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "require_no", nullable = false)
    private RequireBuy requireBuy;
}
