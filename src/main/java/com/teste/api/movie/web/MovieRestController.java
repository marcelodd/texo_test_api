package com.teste.api.movie.web;

import com.teste.api.movie.MovieCommand;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 * @date 03/06/2021 11:32
 */
@Api
@AllArgsConstructor
@RestController
@RequestMapping(value = "/v1/movies")
public class MovieRestController {
    private final MovieCommand movieCommand;
    @PostMapping("/csv-upload")
    public ResponseEntity<?> uploadCsv(@RequestParam("file") MultipartFile file) {
        movieCommand.importFromCsv(file);
        return ResponseEntity.ok("Arquivo importado com sucesso!");
    }
}
