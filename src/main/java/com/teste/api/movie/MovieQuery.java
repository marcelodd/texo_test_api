package com.teste.api.movie;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class MovieQuery {
    private MovieRepository repository;

    public List<Movie> findAll(Specification<Movie> specification) {
        return repository.findAll(specification, Sort.by(Sort.Order.asc("producers"), Sort.Order.asc("year")));
    }
}
