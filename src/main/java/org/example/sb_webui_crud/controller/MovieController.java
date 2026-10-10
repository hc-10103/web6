package org.example.sb_webui_crud.controller;


import org.example.sb_webui_crud.dto.MovieRequest;
import org.example.sb_webui_crud.dto.MovieResponse;
import org.example.sb_webui_crud.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public List<MovieResponse> findAll() {
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieResponse findById(@PathVariable Long id){
        return movieService.findById(id);
    }

    @GetMapping("/search")
    public List<MovieResponse> findByTitle(@RequestParam String title) {
        return movieService.findByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieResponse create(@RequestBody MovieRequest movieRequest){
        return movieService.create(movieRequest);
    }

    @PutMapping("/{id}")
    public MovieResponse update(@RequestBody MovieRequest movieRequest, @PathVariable Long id){
        return movieService.update(id, movieRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        movieService.delete(id);
    }






}
