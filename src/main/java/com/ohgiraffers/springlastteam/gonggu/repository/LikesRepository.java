package com.ohgiraffers.springlastteam.gonggu.repository;

import com.ohgiraffers.springlastteam.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    Optional<Likes> findByUserUserNoAndRequireBuyRequireNo(int userNo, int requireNo);
    @Query("SELECT COUNT(l) FROM Likes l WHERE l.requireBuy.requireNo = :requireNo")
    int countByRequireNo(int requireNo);
}
