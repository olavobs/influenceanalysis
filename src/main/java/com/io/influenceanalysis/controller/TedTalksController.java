package com.io.influenceanalysis.controller;

import com.io.influenceanalysis.controller.dto.InfluentialSpeakerOutputDto;
import com.io.influenceanalysis.controller.dto.TedTalkPerYearOutputDto;
import com.io.influenceanalysis.service.TedTalksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tedtalks")
public class TedTalksController {

    private final TedTalksService tedTalksService;

    @GetMapping("influential-speakers")
    public ResponseEntity<List<InfluentialSpeakerOutputDto>> getInfluentialSpeakers(@RequestParam(defaultValue = "10") int limit) {
        return ResponseEntity.ok(tedTalksService.findInfluentialSpeakers(limit));
    }

    @GetMapping("/most-influential-per-year")
    public ResponseEntity<List<TedTalkPerYearOutputDto>> getMostInfluentialTedTalkPerYear() {
        return ResponseEntity.ok(tedTalksService.getMostInfluentialTedTalkPerYear());
    }
}
