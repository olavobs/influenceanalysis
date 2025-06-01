package com.io.influenceanalysis.loaddata;

import com.io.influenceanalysis.entity.TedTalks;
import com.io.influenceanalysis.mapper.TedTalksMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CsvImporterTest {

    private TedTalksMapper tedTalksMapper;
    private CsvImporter csvImporter;

    @BeforeEach
    void setUp() {
        tedTalksMapper = mock(TedTalksMapper.class);
        csvImporter = new CsvImporter(tedTalksMapper);
    }

    @Test
    void testReadCsv() throws IOException {
        // Given
        String filePath = "test_ted_talks.csv";

        when(tedTalksMapper.toTedTalks(any())).thenAnswer(invocation -> TedTalks.builder()
                .title("Sample Talk")
                .author("Sample Author")
                .date(LocalDate.of(2020, 1, 1))
                .views(1000L)
                .likes(100L)
                .link("http://example.com")
                .build());

        // When
        List<TedTalks> result = csvImporter.readCsv(filePath);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
