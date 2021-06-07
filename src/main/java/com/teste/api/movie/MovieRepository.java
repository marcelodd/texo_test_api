package com.teste.api.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Marcelo Castro - marceloddcastro@gmail.com
 * @date 03/06/2021 11:10
 */
interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {
}
