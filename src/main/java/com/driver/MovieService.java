package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(@RequestBody Movie movie){
        return movieRepository.addMovie(movie);
    }
    public String addDirector(@RequestBody Director director){
        return movieRepository.addDirector(director);
    }

    public String addMovieDirectorPair(@RequestParam("movieName") String movieName , @RequestParam("directorName") String directorName){
        return movieRepository.addMovieDirectorPair(movieName,directorName);
    }

    public Movie getMovieByName(@PathVariable("movieName") String name){
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(@PathVariable("directorName") String name){
        return movieRepository.getDirectorByName(name);
    }

    public List<String> getMoviesByDirectorName(@PathVariable("director") String name){ //List<Movie>
        return movieRepository.getMoviesByDirectorName(name);  //return arrayList
    }

    public List<Movie> findAllMovies(){
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(@RequestParam("name") String name){
        return movieRepository.deleteDirectorByName(name);
    }
    public String deleteAllDirectors(){
        return movieRepository.deleteAllDirectors();
    }
}
