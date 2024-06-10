package com.ohgiraffers.springlastteam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "require_buying")
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class RequireBuy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "require_no")
    private int requireNo;

    @Column(name = "require_text")
    private String requireText;

    @Column(name = "require_item")
    private String requireItem;

    @Column(name = "require_price")
    private int requirePrice;

    @Column(name = "require_quality")
    private String requireQuality;

    @ManyToOne
    @JoinColumn(name = "user_no", nullable = false)
    private Users user;


}
