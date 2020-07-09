package com.isensor.contacttracingbackend.db.repository;

import com.isensor.contacttracingbackend.db.entity.C19NewsUpdates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface C19NewsUpdatesRepository extends JpaRepository<C19NewsUpdates, Long> {
    @Query(value = "select * from c19_news_updates order by id desc limit :count", nativeQuery = true)
    List<C19NewsUpdates> getLatestNewsUpdates(int count);
}
