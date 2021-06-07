package com.teste.api.movie;

import org.springframework.data.jpa.domain.Specification;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 */
public abstract class MovieSpecification {
    public static Specification<Movie> movieWinner() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("winner"), true);
    }
}
