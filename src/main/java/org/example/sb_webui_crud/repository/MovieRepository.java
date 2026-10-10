package org.example.sb_webui_crud.repository;

import org.example.sb_webui_crud.domain.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {

    Movie create(Movie m);
    Movie update(Long id, Movie m);
    boolean delete(Long id);
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    List<Movie> findByTitle(String title);

}
