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
//    @Autowired
//    MovieRepository movieRepository;

    Map<String,Movie> movieMap = new HashMap<>(); //movie storage
    Map<String,Director> directorMap =new HashMap<>(); //director storage
    Map<String, ArrayList<Movie>> directorMovieMap = new HashMap<String, ArrayList<Movie>>(); // directorMovie storage

    public String addMovie(@RequestBody Movie movie){
        String str = "";
        if(movie != null){
            movieMap.put(movie.getName(),movie);
            str = "Movie Added Succesfully";
        }
        else {
            str = "Movie Information is not Sufficiednt Or Invalid";
        }
        return str; // or we can directly Return
    }

    public String addDirector(@RequestBody Director director){
        String str = "";
        if(director != null){
            directorMap.put(director.getName(), director);
            str = "Director Added Succesfully";
        }
        else {
            str = "Director Information is not Sufficiednt Or Invalid";
        }
        return str;
    }

    public String addMovieDirectorPair(@RequestParam("movieName") String movieName , @RequestParam("directorName") String directorName){
        String str = "";
        //ArrayList<Movie> movieList = new ArrayList<>(); //parent object List
        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){

            if(!directorMovieMap.containsKey(directorName)){
                directorMovieMap.put(directorName,new ArrayList<Movie>());
            }
            ArrayList<Movie> movieList = directorMovieMap.get(directorName);

            directorMovieMap.get(directorName).add(movieMap.get(movieName)); // direcly added by reference

            // movieList.add(movieMap.get(movieName));

            // directorMovieMap.put(directorName, movieList); //(ArrayList<Movie>) movieList if we mention parent object then we need to cast it


            System.out.println(directorMap.get(directorName).toString());
            //   System.out.println(movieList);
            System.out.println(directorMovieMap.containsKey(directorName));
            System.out.println(directorMovieMap.get(directorName).toString());

            str = "Movie & Director paired succesfully";
        }
        else {
            str = "Movies or Director Doesn't Exist";
        }
//        if(movieMap.containsKey(movieName) && directorMap.containsKey(directorName)){
//            directorMovieMap.put(movieMap.get(movieName),directorMap.get(directorName));
//            str = "Movie & Director paired succesfully";
//        }
//        else {
//            str = "Movie & Director paired succesfully";
//        }
        return str;
    }

    public Movie getMovieByName(@PathVariable("movieName") String name){
        Movie movie = null;
        if (movieMap.containsKey(name)){
            movie = movieMap.get(name);
        }
        return movie;
    }

    public Director getDirectorByName(@PathVariable("directorName") String name){
        Director director = null;
        if (directorMap.containsKey(name)){
            director = directorMap.get(name);
        }
        return director;
    }

    public List<String> getMoviesByDirectorName(@PathVariable("director") String name){ //List<Movie>
        List<String> list = new ArrayList<>();
        if(directorMovieMap.containsKey(name)){
            ArrayList<Movie> movieArrayList = directorMovieMap.get(name);//directorMap.get(name) can't access ny just namme cox created that map by director object key
            //
            System.out.println(movieArrayList.size());
            for (Movie movie : movieArrayList){ //movieArrayList //directorMovieMap.get(name)
                list.add(movie.getName());
                //
                System.out.println(movie.getName());
                System.out.println(movieArrayList);
                System.out.println(list);
            }
        }
        return list;  //return arrayList
    }

    public List<String> findAllMovies(){
//        List<Movie> list = new ArrayList<>();
//        for (String movie : movieMap.keySet()){
//            list.add(movieMap.get(movie));
//        }
        List<String> list = new ArrayList<>();
        for (String movie : movieMap.keySet()){
            list.add(movie);
        }
        return list;
    }

    public String deleteDirectorByName(@RequestParam("name") String name){
        List<String> list = new ArrayList<>();
        String str = "";
        if(directorMovieMap.containsKey(name)){
            for (Movie movie : directorMovieMap.get(name)){ //movieArrayList
                list.add(movie.getName());
//                if(movieMap.containsKey(movie.getName())){
//                    movieMap.remove(movie.getName());
//                }
            }
            directorMovieMap.remove(name); // remove records from directorMovieMap

            for(String movieName : list){//removed records of movies
                if(movieMap.containsKey(movieName)){
                    movieMap.remove(movieName);
                }
            }

            directorMap.remove(name); //remove director

            str = "Records deleted Successfully";
        }
        else {
            str = "Invalid records";
        }
        return str;
    }

    public String deleteAllDirectors(){

        movieMap.clear();
        directorMap.clear();
        directorMovieMap.clear();
        String str = "All records deleted Successfully";

        return str;
    }
}
