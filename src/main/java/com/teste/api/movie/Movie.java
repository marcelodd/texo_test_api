package com.teste.api.movie;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int year;
    private String title;
    private String studios;
    private String producers;
    private boolean winner;

    public List<Movie> splitProducers() {
        return Arrays.stream(producers.split(",|and")).map(producer -> Movie.builder()
                .year(year)
                .title(title)
                .studios(studios)
                .producers(producer.trim())
                .winner(winner)
                .build())
                .collect(Collectors.toList());
    }

    public static Movie of(CSVRecord record) {
        return Movie.builder()
                .year(Integer.valueOf(record.get(0)))
                .title(record.get(1))
                .studios(record.get(2))
                .producers(record.get(3))
                .winner(record.size() > 4 && record.get(4).equals("yes"))
                .build();
    }
}
