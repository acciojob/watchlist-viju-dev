package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieMap = new HashMap<>(); //movie storage
    Map<String,Director> directorMap =new HashMap<>(); //director storage
    Map<String, ArrayList<String>> directorMovieMap = new HashMap<>(); // directorMovie storage

    public String addMovie(Movie movie){
            movieMap.put(movie.getName(),movie);
            return "Movie Added Succesfully";
    }

    public String addDirector(Director director){
            directorMap.put(director.getName(), director);
            return  "Director Added Succesfully";

    }

    public String addMovieDirectorPair(String movieName , String directorName){
        String str = "";

        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){ //checking if dir and movie contains in storage or not

            if(!directorMovieMap.containsKey(directorName)){
                directorMovieMap.put(directorName,new ArrayList<String>());
            }

            directorMovieMap.get(directorName).add(movieName); // direcly added by reference

            str = "Movie & Director paired succesfully";
        }
        else {
            str = "Information is not Valid";
        }

        return str;
    }

    public Movie getMovieByName(String name){
        return movieMap.get(name);
    }

    public Director getDirectorByName(String name){
            return directorMap.get(name);
    }

    public List<String> getMoviesByDirectorName(String name){
//        List<String> list = new ArrayList<>(); //for storing movies
//        if(directorMovieMap.containsKey(name)){ // cheking if director exist
//            ArrayList<String> movieArrayList = directorMovieMap.get(name); //getting movielist of that director
//            for (Movie movie : movieArrayList){
//                list.add(movie.getName()); // adding movie names to list
//            }
//        }
        return directorMovieMap.get(name); //returned list of movie names
    }

    public List<String> findAllMovies(){
        List<String> list = new ArrayList<>(); //for storing movies
        for (String movie : movieMap.keySet()){
            list.add(movie); //added al movie names to list
        }
        return list; ///returned list of all movie names
    }

    public String deleteDirectorByName(String name){
        List<String> list = new ArrayList<>();
        String str = "";
        if(directorMovieMap.containsKey(name)){
            for (String movie : directorMovieMap.get(name)){ //movieArrayList
                list.add(movie); // store movies related to given director in this list
            }
            directorMovieMap.remove(name); // removed director record from directorMovieMap

            for(String movieName : list){ //Iterate on movieName List
                if(movieMap.containsKey(movieName)){
                    movieMap.remove(movieName);   // if movie matched then removed from storage
                }
            }

           if (directorMap.containsKey(name)){// remove director from storage
               directorMap.remove(name);
           }

            str = "Records deleted Successfully";
        }
        else {
            str = "Director with given name not Exist";
        }
        return str;
    }

    public String deleteAllDirectors(){
        for (String directorName : directorMovieMap.keySet()){ // iterate on directorMovieMap to get every director
            for (String movie:directorMovieMap.get(directorName)){ //iterate on directorMovieMap by directorName to get every movie
                if (movieMap.containsKey(movie)){ // if that movie matched with movieMap then we'll remove it
                    movieMap.remove(movie);
                }
            }
        }
        directorMap.clear(); // clear all directors
        directorMovieMap.clear();// clear all director and movie pairs
        return  "All records deleted Successfully";

    }
}
