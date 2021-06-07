package com.teste.api.movie.web;

import com.teste.api.movie.Movie;
import com.teste.api.movie.MovieCommand;
import com.teste.api.movie.MovieQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

import static com.teste.api.movie.MovieSpecification.movieWinner;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/movies")
public class MovieRestController {

    private final String[] ACCEPTED_TYPES = new String[]{"application/vnd.ms-excel", "text/csv"};

    private final MovieCommand movieCommand;
    private final MovieQuery movieQuery;

    @PostMapping(value = "/csv/import", consumes = {"multipart/form-data"})
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (!Arrays.stream(ACCEPTED_TYPES).anyMatch(t -> t.equals(file.getContentType())))
            return ResponseEntity.badRequest().body("Tipo de arquivo não suportado, favor enviar apenas arquivos no formato CSV.");
        movieCommand.importFromCsv(file);
        return ResponseEntity.ok("Arquivo importado com sucesso!");
    }

    @GetMapping("/max-min-interval-win")
    public ResponseEntity<?> maxMinIntervalWin() {
        List<Movie> winningMovies = movieQuery.findAll(movieWinner());

        if(winningMovies.size() == 0)
            return  ResponseEntity.noContent().build();

        Map<String, List<Movie>> moviesPerProducers = winningMovies.stream()
                .flatMap(m -> m.splitProducers().stream())
                .sorted()
                .collect(groupingBy(Movie::getProducers))
                .entrySet()
                .stream()
                .filter(e -> e.getValue().size() >= 2)
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

        MovieIntervalResponse resultMin = moviesPerProducers.entrySet().stream().filter(e -> e.getValue().get(1).getYear() - e.getValue().get(0).getYear() <= 1)
                .map(e -> MovieIntervalResponse.of(e))
                .min(Comparator.comparing(MovieIntervalResponse::getInterval))
                .get();

        MovieIntervalResponse resultMax = moviesPerProducers.entrySet().stream().filter(e -> e.getValue().get(1).getYear() - e.getValue().get(0).getYear() > 1)
                .map(e -> MovieIntervalResponse.of(e))
                .max(Comparator.comparing(MovieIntervalResponse::getInterval))
                .get();

        return ResponseEntity.ok(new HashMap<String, List<MovieIntervalResponse>>() {{
            put("min", Collections.singletonList(resultMin));
            put("max", Collections.singletonList(resultMax));
        }});
    }
}
