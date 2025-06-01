package com.io.influenceanalysis.loaddata;

import com.io.influenceanalysis.entity.TedTalks;
import com.io.influenceanalysis.repository.TedTalksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
class DatabaseInitializer implements CommandLineRunner {

    private static final String DATA_SET = "iO_Data.csv";

    private final TedTalksRepository tedTalksRepository;
    private final CsvImporter csvImporter;

    @Override
    public void run(String... args) throws Exception {
        List<TedTalks> tedTalks = csvImporter.readCsv(DATA_SET);
        tedTalksRepository.saveAll(tedTalks);
    }
}
