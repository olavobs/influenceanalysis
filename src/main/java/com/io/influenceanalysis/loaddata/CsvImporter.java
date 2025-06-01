package com.io.influenceanalysis.loaddata;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.io.influenceanalysis.entity.TedTalks;
import com.io.influenceanalysis.mapper.TedTalksMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvImporter {

    private final TedTalksMapper tedTalksMapper;

    public List<TedTalks> readCsv(String filePath) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = createSchema();
        ClassPathResource resource = new ClassPathResource(filePath);
        InputStream inputStream = resource.getInputStream();
        List<TedTalks> validTedTalks = new ArrayList<>();

        try (MappingIterator<TedTalksCsv> iterator = mapper.readerFor(TedTalksCsv.class)
                .with(schema)
                .readValues(inputStream)) {
            while (iterator.hasNext()) {
                try {
                    TedTalksCsv tedTalksCsv = iterator.next();
                    validTedTalks.add(tedTalksMapper.toTedTalks(tedTalksCsv));
                } catch (NumberFormatException e) {
                    log.error("Skipping line due to invalid number format: " + e.getMessage());
                } catch (Exception e) {
                    log.error("Skipping line due to an error: " + e.getMessage());
                }
            }
        }
        return validTedTalks;
    }

    private CsvSchema createSchema() {
        return CsvSchema.builder()
                .addColumn("title")
                .addColumn("author")
                .addColumn("date")
                .addColumn("views", CsvSchema.ColumnType.NUMBER)
                .addColumn("likes", CsvSchema.ColumnType.NUMBER)
                .addColumn("link")
                .setSkipFirstDataRow(true)
                .build();
    }
}