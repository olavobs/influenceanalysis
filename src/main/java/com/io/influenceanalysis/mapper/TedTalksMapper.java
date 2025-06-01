package com.io.influenceanalysis.mapper;

import com.io.influenceanalysis.entity.TedTalks;
import com.io.influenceanalysis.loaddata.TedTalksCsv;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Mapper(componentModel = "spring")
public interface TedTalksMapper {

    @Mapping(target = "author", expression = "java(tedTalksCsv.author() == null || tedTalksCsv.author().isBlank() ? \"Unknown\" : tedTalksCsv.author())")
    @Mapping(target = "date", expression = "java(convertToLocalDate(tedTalksCsv.date()))")
    TedTalks toTedTalks(TedTalksCsv tedTalksCsv);

    default LocalDate convertToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH);
        YearMonth yearMonth = YearMonth.parse(dateString, formatter);
        return yearMonth.atDay(1);
    }
}