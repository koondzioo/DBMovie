package cinema;

import exception.ValidationMovieException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

public class Movie {

    private String titleMovie;
    private String genreMovie;
    private String director;


    public Movie(String titleMovie, String genreMovie, String director) throws ValidationMovieException {
            this.titleMovie = titleMovie;
        if(!validateTitle())
        {
            throw new ValidationMovieException(titleMovie, LocalDateTime.now());
        }
            this.genreMovie = genreMovie;
        if(!validateGenre())
        {
            throw new ValidationMovieException(genreMovie, LocalDateTime.now());
        }
            this.director = director;
        if(!validateDirector())
        {
            throw new ValidationMovieException(director, LocalDateTime.now());
        }

    }

    public Movie() {
    }

    @Override
    public String toString() {
        return "cinema.Movie{" +
                "titleMovie='" + titleMovie + '\'' +
                ", genreMovie='" + genreMovie + '\'' +
                ", director='" + director + '\'' +
                '}';
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public String getGenreMovie() {
        return genreMovie;
    }

    public void setGenreMovie(String genreMovie) {
        this.genreMovie = genreMovie;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    private boolean checkField(String str, String regex) {
        return str.matches(regex);
    }

    private boolean validateTitle() {

        return checkField(titleMovie, "[A-Z]+");
    }

    private boolean validateGenre() {
        String[] arr = movieProperties();
        for (int i = 0; i < arr.length ; i++)
        {
            if(genreMovie.equals(arr[i]))
                return true;
        }
        return false;
    }

    private boolean validateDirector() {
        return checkField(director, "[A-Z][a-z]+ [A-Z][a-z]+");
    }

    private String[] movieProperties() {
        Properties properties = new Properties();
        String[] arr = new String[]{};
        try {
            InputStream inputStream = new FileInputStream("src\\main\\resources\\geners.properties");
            properties.load(inputStream);
            arr = properties.getProperty("genres").split(",");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arr;
    }
}
