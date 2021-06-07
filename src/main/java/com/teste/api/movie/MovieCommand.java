package com.teste.api.movie;

import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */
@Service
@AllArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MovieCommand {

    private final MovieRepository repository;

    public List<Movie> csvToMovies(MultipartFile csv) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getInputStream(), "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader, CSVFormat.EXCEL.withDelimiter(';').withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {
            return csvParser.getRecords()
                    .stream()
                    .map(r -> Movie.of(r))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public List<Movie> saveAll(Iterable<Movie> movies) {
        return repository.saveAll(movies);
    }

    public List<Movie> importFromCsv(MultipartFile csv) {
        return saveAll(csvToMovies(csv));
    }

}
