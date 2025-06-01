package com.io.influenceanalysis.repository;

import com.io.influenceanalysis.controller.dto.InfluentialSpeakerOutputDto;
import com.io.influenceanalysis.controller.dto.TedTalkPerYearOutputDto;
import com.io.influenceanalysis.entity.TedTalks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TedTalksRepository extends JpaRepository<TedTalks, Long> {

    @Query("SELECT t.author AS author, SUM(t.views + t.likes) AS totalInfluence FROM TedTalks t GROUP BY t.author ORDER BY totalInfluence DESC LIMIT :limit")
    List<InfluentialSpeakerOutputDto> findInfluentialSpeakers(@Param("limit") int limit);

    @Query("""
            SELECT
                YEAR(t.date) AS year,
                t.title AS title,
                (t.likes + t.views) AS totalInfluence
            FROM TedTalks t
            WHERE (t.likes + t.views) = (
                SELECT MAX(t2.likes + t2.views)
                FROM TedTalks t2
                WHERE YEAR(t2.date) = YEAR(t.date)
            )
            ORDER BY YEAR(t.date), t.title
            """)
    List<TedTalkPerYearOutputDto> findMostInfluentialTedTalkPerYear();
}