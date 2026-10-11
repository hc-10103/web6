package org.example.sb_webui_crud.controller;


import org.example.sb_webui_crud.dto.MovieRequest;
import org.example.sb_webui_crud.dto.MovieResponse;
import org.example.sb_webui_crud.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/movies")
public class MovieWebController {
    private final MovieService movieService;

    public MovieWebController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping
    public String list(@RequestParam (required = false) String  q,
                       @RequestParam (required = false) Long selected, Model model) {
        List<MovieResponse> movies = movieService.findAll();
        if (q != null) {
            movies = movieService.findByTitle(q);
        }
        MovieResponse selectedMovie = movies.stream().filter(movie -> movie.id().equals(selected)).
                findFirst().orElse(null);

        model.addAttribute("movies", movies);
        model.addAttribute("query", q == null ? "" : q);
        model.addAttribute("selectedMovie", selectedMovie);

        return "movies/list";
    }

    @GetMapping("/new")
    public String newMovie() {
        return "movies/form";
    }

    @PostMapping
    public String saveMovie(@ModelAttribute MovieRequest movieRequest) {
        movieService.create(movieRequest);
        return "redirect:/movies";
    }



}
