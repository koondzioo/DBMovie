import cinema.GetFile;
import cinema.GetRandom;
import cinema.Repertoire;

import java.time.LocalDate;

public class Main {



    public static void main(String[] args) {



        Repertoire repertuar = new Repertoire("src/seance", LocalDate.of(2017,01,01), LocalDate.of(2017,10,12));

        GetRandom filmRandomo = new GetRandom();
        filmRandomo.getMovie();

        GetFile filmPlik = new GetFile("src/move");

        exception.CustomException.getMap().forEach((k,v) -> System.out.println(k + ":\n" + v));

        exception.CustomException.saveToFile("exception");



    }

}
