package org.example.sb_webui_crud.repository;

import org.example.sb_webui_crud.domain.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class MemoryMovieRepository implements MovieRepository {
    private final LinkedHashMap<Long, Movie> movies = new LinkedHashMap<>();
    private Long id = 0L;

    @Override
    public Movie create(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(++id);
            movies.put(movie.getId(), movie);
        }
        return movie;
    }

    @Override
    public Movie update(Long id, Movie movie) {
        Movie savedMovie = findById(id).orElse(null);
        savedMovie.setTitle(movie.getTitle());
        savedMovie.setDirector(movie.getDirector());
        savedMovie.setDistributor(movie.getDistributor());
        savedMovie.setScore(movie.getScore());
        savedMovie.setAdultOnly(movie.isAdultOnly());

        return savedMovie;
    }

    @Override
    public boolean delete(Long id) {
        if (findById(id).isEmpty()) { return false; }
        movies.remove(id);
        return true;
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(movies.get(id));
    }

    @Override
    public List<Movie> findByTitle(String title) {
        return movies.values().stream()
                .filter(movie ->
                        movie.getTitle()
                                .toLowerCase()
                                .contains(title.toLowerCase())
                ).toList();
    }
}
