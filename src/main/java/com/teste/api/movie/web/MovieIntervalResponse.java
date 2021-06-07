package com.teste.api.movie.web;

import com.teste.api.movie.Movie;
import lombok.*;

import java.util.List;
import java.util.Map;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MovieIntervalResponse implements Comparable<MovieIntervalResponse> {
    private String producer;
    private Integer interval;
    private Integer previousWin;
    private Integer followingWin;

    public static MovieIntervalResponse of(Map.Entry<String, List<Movie>> entry) {
        return MovieIntervalResponse.builder()
                .producer(entry.getKey())
                .interval(entry.getValue().get(1).getYear() - entry.getValue().get(0).getYear())
                .previousWin(entry.getValue().get(0).getYear())
                .followingWin(entry.getValue().get(1).getYear())
                .build();
    }

    @Override
    public int compareTo(MovieIntervalResponse o) {
        return o.getInterval().compareTo(interval);
    }
}
