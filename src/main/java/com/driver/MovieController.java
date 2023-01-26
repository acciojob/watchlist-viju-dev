package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;
    Map<String,Movie> movieMap = new HashMap<>(); //movie storage
    Map<String,Director> directorMap =new HashMap<>(); //director storage
    Map<String, ArrayList<Movie>> directorMovieMap = new HashMap<String, ArrayList<Movie>>(); // directorMovie storage


    @PostMapping("/movies/add-movie") // adding movie
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String str = movieService.addMovie(movie);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
//        or we can do this as well
//        return new ResponseEntity<>(movieService.addMovie(movie);, HttpStatus.CREATED);
    }

    @PostMapping("/movies/add-director") //adding director
    public ResponseEntity addDirector(@RequestBody Director director){
        String str = movieService.addDirector(director);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @PutMapping("/movies/add-movie-director-pair") //adding movie-director pair
        public ResponseEntity addMovieDirectorPair(@RequestParam("movieName") String movieName ,@RequestParam("directorName") String directorName){
        String str = movieService.addMovieDirectorPair(movieName,directorName);
        return new ResponseEntity<>(str, HttpStatus.CREATED);
    }

    @GetMapping("/movies/get-movie-by-name/{movieName}") //getting movie by name
    public ResponseEntity getMovieByName(@PathVariable("movieName") String name){
        Movie movie = movieService.getMovieByName(name);
        return new ResponseEntity<>(movie,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-director-by-name/{directorName}") //getting director by directorName
    public ResponseEntity getDirectorByName(@PathVariable("directorName") String name){
        Director director = movieService.getDirectorByName(name);
       return new ResponseEntity<>(director,HttpStatus.FOUND);
    }

    @GetMapping("/movies/get-movies-by-director-name/{director}") //getting movies by directorName
    public ResponseEntity getMoviesByDirectorName(@PathVariable("director") String name){ //List<Movie>
        List<String> list = movieService.getMoviesByDirectorName(name);
        return new ResponseEntity<>(list,HttpStatus.FOUND);   //return arrayList
    }

    @GetMapping("/movies/get-all-movies")
    public ResponseEntity findAllMovies(){
//        List<Movie> list = movieService.findAllMovies();
        List<String> list = movieService.findAllMovies();
        return new ResponseEntity<>(list,HttpStatus.FOUND);
    }

    @DeleteMapping("/movies/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam("name") String name){
        String str = movieService.deleteDirectorByName(name);
        return new ResponseEntity<>(str,HttpStatus.GONE);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        String str = movieService.deleteAllDirectors();
        return new ResponseEntity<>(str,HttpStatus.RESET_CONTENT);
    }

}
