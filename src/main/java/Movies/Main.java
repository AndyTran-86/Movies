package Movies;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Main {
    public Main() {

        //Skriv in rätt url!
        String uri = "mongodb+srv://andy:tryme0102@cluster0.gzlja.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("sample_mflix");
            MongoCollection<Document> moviesCollection = database.getCollection("movies");

            //Get all movies from 1975
            List<Movie> movieList = new ArrayList<>();
            for (Document doc : moviesCollection.find(new Document("year", 1975))) {
                {
                    movieList.add(Movie.fromDocument(doc));
                }
            }

            Funktioner funktioner = new Funktioner(movieList);


//             Skriver ut alla filmer
//            for (Movie movie : movieList) {
//                System.out.println(movie);
//            }



            //Här gör du anrop till alla dina funktioner som ska skriva ut svaren på frågorna som
            //efterfrågas i uppgiften


            //1. Hur många filmer gjordes 1975 (enligt vårt data). Returnera ett tal.
            System.out.println("1. Det gjordes total " + funktioner.countMovie() + " filmer under 1975.\n");


            //2. Hitta längden på den film som var längst (högst runtime). Returnera ett tal.
            System.out.println("2. Längsta runtime är " + funktioner.runTimeMax() + " min.\n");


            //3. Hur många UNIKA genrer hade filmerna från 1975. Returnera ett tal.
            System.out.println("3. Det finns total " + funktioner.uniqueGenre() + "st unika genrer i listan.\n");


            //4. Vilka skådisar som spelade i den film som hade högst imdb-rating. Returnera en List<String> med deras namn.
            System.out.println("4. Filmen med högst imdb rating spelas av skådespelarna \n" + funktioner.highestActors() + "\n");


            //5. Vad är titeln på den film som hade minst antal skådisar listade? Returnera en String.
            System.out.println("5. Titeln på filmen med minst antal skådisar är: " + funktioner.minActor() + ".\n");


            //6. Hur många skådisar var med i mer än 1 film? Returnera ett tal.
            System.out.println("6. Det var total " + funktioner.doubleActors() + " st skådespelare som var med i mer än 1 film.\n");


            //7. Vad hette den skådis som var med i flest filmer? Returnera en String
            System.out.println("7. Skådespelaren : " + funktioner.mvpActor() + " var med i flest filmer.\n");


            //8. Hur många UNIKA språk har filmerna? Returnera ett tal.
            System.out.println("8. Det finns total " + funktioner.uniqueLang() + "st unika språk i listan.\n");


            //9. Finns det någon titel som mer än en film har? Returnera en bool.
            funktioner.doubleTitle();







        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
    }
}