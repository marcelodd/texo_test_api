package com.teste.api.movie;

import lombok.*;
import org.apache.commons.csv.CSVRecord;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 * @date 03/06/2021 11:01
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

    public static Movie of(CSVRecord record) {
        return Movie.builder()
                .year(Integer.valueOf(record.get(0)))
                .title(record.get(1))
                .studios(record.get(2))
                .producers(record.get(3))
                .winner(record.size() > 4 && record.get(5).equals("yes"))
                .build();
    }
}
