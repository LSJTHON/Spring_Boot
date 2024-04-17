package com.codehows.sbd.repository;

import com.codehows.sbd.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article,Long> {

}
