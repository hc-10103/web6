package org.example.sb_webui_crud.service;

import org.example.sb_webui_crud.domain.Movie;
import org.example.sb_webui_crud.dto.MovieRequest;
import org.example.sb_webui_crud.dto.MovieResponse;
import org.example.sb_webui_crud.repository.MovieRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse create(MovieRequest m) {
        validate(m);
        return toResponse(movieRepository.create(new Movie(null, m.title(), m.director(),
                m.distributor(), m.score(), m.isAdultOnly())));
    }

    public MovieResponse update(Long id, MovieRequest m) {
        validate(m);
        if (movieRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
        return toResponse(movieRepository.update(id, new Movie(null, m.title(), m.director(),
                m.distributor(), m.score(), m.isAdultOnly())));
    }

    public void delete(Long id) {
        if (movieRepository.delete(id)) {
            return;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found");
        }
    }

    public MovieResponse findById(Long id) {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Movie not found"));
        return toResponse(movie);
    }

    public List<MovieResponse> findByTitle(String title) {
        return movieRepository.findByTitle(title).stream().map(this::toResponse).toList();
    }

    public List<MovieResponse> findAll() {
        List<MovieResponse> movies = new ArrayList<>();
        for (Movie m : movieRepository.findAll()) {
            movies.add(toResponse(m));
        }
        return movies;
    }

    public void validate(MovieRequest m) {
        if (!(m.score() >= 0 && m.score() <= 5)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Score must be between 0 and 5");
        }
        if (m.title() == null || m.title().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title is required");
        }
        if (m.director() == null || m.director().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Director is required");
        }
        if (m.distributor() == null || m.distributor().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Distributor is required");
        }
    }

    public MovieResponse toResponse(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getDistributor(),
                movie.getScore(), movie.isAdultOnly());
    }
}
