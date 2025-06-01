package com.io.influenceanalysis.service;

import com.io.influenceanalysis.controller.dto.InfluentialSpeakerOutputDto;
import com.io.influenceanalysis.controller.dto.TedTalkPerYearOutputDto;
import com.io.influenceanalysis.repository.TedTalksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TedTalksService {

    private final TedTalksRepository tedTalksRepository;

    public List<InfluentialSpeakerOutputDto> findInfluentialSpeakers(int limit) {
        return tedTalksRepository.findInfluentialSpeakers(limit);
    }

    public List<TedTalkPerYearOutputDto> getMostInfluentialTedTalkPerYear() {
        return tedTalksRepository.findMostInfluentialTedTalkPerYear();
    }
}
