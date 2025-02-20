package Movies;

import java.util.Comparator;
import java.util.List;

public class Funktioner {
    private List<Movie> movieList;


    private List<String> actorList;
    private List<String> titleList;



    public Funktioner(List<Movie> movieList){
        this.movieList = movieList;


        this.actorList = movieList.stream().flatMap(movie -> movie.getCast().stream()).toList();
        this.titleList = movieList.stream().map(Movie::getTitle).toList();
    }




//1. Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal.
    public long countMovie(){

        return movieList.stream().count();
    }



    //2. Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
    public long runTimeMax(){

        return movieList.stream().mapToInt(Movie::getRuntime).max().orElse(-1);
    }



    //3. Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
    public long uniqueGenre(){

        return movieList.stream().flatMap(movie -> movie.getGenres().stream()).distinct().count();
    }



    //4. Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en List<String> med deras namn.
    public List<String> highestActors(){
        double highestRate = movieList.stream().mapToDouble(Movie::getImdbRating).max().orElse(0.0);

        Movie highestMovie = movieList.stream().filter(movie -> movie.getImdbRating() == highestRate).findFirst().orElse(null);

        return highestMovie.getCast();
    }



    //5. Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
    public String minActor(){
        Movie minActor = movieList.stream().min(Comparator.comparingInt(movie -> movie.getCast().size())).orElse(null);

        return minActor.getTitle();
    }



    //6. Hur många skådisar var med i mer än 1 film? Returnera ett tal.
    public long doubleActors(){

        return actorList.stream().filter(actor -> movieList.stream().filter(movie -> movie.getCast().contains(actor)).count() > 1).distinct().count();
    }



    //7. Vad hette den skådis som var med i flest filmer? Returnera en String
    public String mvpActor(){

        return actorList.stream().distinct().max(Comparator.comparingLong(actor -> actorList.stream().filter(a -> a.equals(actor)).count())).orElse(null);
    }


    //8.  Hur många UNIKA språk har filmerna? Returnera ett tal.
    public long uniqueLang(){

        return movieList.stream().flatMap(movie -> movie.getLanguages().stream()).distinct().count();
    }



    //9.  Finns det någon titel som mer än en film har? Returnera en bool.
    public boolean doubleTitle(){
        boolean dt = titleList.stream().anyMatch(title -> titleList.stream().filter(t -> t.equals(title)).count() > 1);

        if (dt){
            System.out.println("9. Ja, det finns titlar med samma namn");
        }
        else {
            System.out.println("9. Nej, det finns inte titlar med samma namn");
        }
        return dt;
    }









    //ville veta hur många genre och hur många olika språk det fanns i listan
    public List<String> allGenre(){
        return movieList.stream().flatMap(movie -> movie.getGenres().stream()).distinct().toList();
    }

    public List<String> allLang(){
        return movieList.stream().flatMap(movie -> movie.getLanguages().stream()).distinct().toList();
    }


}
