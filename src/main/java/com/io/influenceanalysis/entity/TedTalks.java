package com.io.influenceanalysis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ted_talks", indexes = {
        @Index(name = "idx_date", columnList = "date"),
        @Index(name = "idx_likes_views", columnList = "likes, views")
})
public class TedTalks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private LocalDate date;
    private Long views;
    private Long likes;
    private String link;
}
