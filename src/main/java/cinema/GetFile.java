package cinema;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class GetFile implements IMovie {

    private static HashSet<Movie> movies = new HashSet<>();


    public GetFile(String fileName) {
        HashSet<Movie> movies = new HashSet<>();
        try {
            FileReader fr = new FileReader(fileName);
            Scanner scanner = new Scanner(fr);
            String[] line;
            while (scanner.hasNextLine())
            {
                line=scanner.nextLine().split(";");
                Movie movie = null;
                try {
                    movie = new Movie(line[0], line[1], line[2]);
                } catch (exception.CustomException e) {
                    exception.CustomException.addNewException(e);
                }
                movies.add(movie);
            }
        }catch(FileNotFoundException e){
        e.printStackTrace();
        }
        this.movies = movies;
    }

    @Override
    public Movie getMovie() {
        Random rnd = new Random();
        int number = rnd.nextInt(movies.size());
        List<Movie> list = new ArrayList<>(movies);
        return list.get(number);
    }
}

