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

    @Query(value = """
            SELECT
                year,
                title,
                totalInfluence
            FROM (
                SELECT
                    YEAR(date) AS year,
                    title,
                    SUM(views + likes) AS totalInfluence,
                    ROW_NUMBER() OVER (PARTITION BY YEAR(date) ORDER BY SUM(views + likes) DESC) AS rn
                FROM ted_talks
                GROUP BY YEAR(date), title
            ) AS subquery
            WHERE rn = 1
            ORDER BY year;
            """, nativeQuery = true)
    List<TedTalkPerYearOutputDto> findMostInfluentialTedTalkPerYear();
}