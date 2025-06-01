package com.io.influenceanalysis.controller.dto;

import java.math.BigInteger;

public interface TedTalkPerYearOutputDto {
    Integer getYear();

    String getTitle();

    BigInteger getTotalInfluence();
}