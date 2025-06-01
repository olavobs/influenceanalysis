package com.io.influenceanalysis.controller.dto;

import java.math.BigInteger;

public interface InfluentialSpeakerOutputDto {
    String getAuthor();
    BigInteger getTotalInfluence();
}