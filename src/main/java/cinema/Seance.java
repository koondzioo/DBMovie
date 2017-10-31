package cinema;

import exception.ValidationSeanceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Seance {

    private Movie movie;
    private int price;
    private int movieLength;
    private int roomNumber;

    @Override
    public String toString() {
        return "cinema.Seance{" +
                "move=" + movie +
                ", price=" + price +
                ", movieLength=" + movieLength +
                ", roomNumber=" + roomNumber +
                '}';
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Seance(Movie movie, int price, int movieLength, int roomNumber) throws ValidationSeanceException {
        Map<String, Integer> map = properties();
        this.movie = movie;
        this.price = price;
        if(!checkPrice(price,map.get("PRICE_MIN"), map.get("PRICE_MAX")))
        {
            throw new ValidationSeanceException(movie.getTitleMovie(), "Price " + price , LocalDateTime.now());
        }
        this.movieLength = movieLength;
        if(!checkMovieLength(movieLength,map.get("MOVIE_TIME1"), map.get("MOVIE_TIME2"), map.get("MOVIE_TIME3")))
        {
            throw new ValidationSeanceException(movie.getTitleMovie(), "movie length " + movieLength , LocalDateTime.now());
        }
        this.roomNumber = roomNumber;
        if(!checkCinemaHall(roomNumber,map.get("ROOM_MIN"), map.get("ROOM_MAX")))
        {
            throw new ValidationSeanceException(movie.getTitleMovie(), "room " +  roomNumber , LocalDateTime.now());
        }
    }



    private Map<String, Integer> properties()
    {

        Map<String, Integer> map = new HashMap<>();
        try {
            Properties properties = new Properties();
            InputStream inputStream = new FileInputStream("src\\main\\resources\\seance.properties");
            properties.load(inputStream);
            map.put("PRICE_MIN", Integer.parseInt(properties.getProperty("PRICE_MIN")));
            map.put("PRICE_MAX", Integer.parseInt(properties.getProperty("PRICE_MAX")));
            map.put("MOVIE_TIME1", Integer.parseInt(properties.getProperty("MOVIE_TIME1")));
            map.put("MOVIE_TIME2", Integer.parseInt(properties.getProperty("MOVIE_TIME2")));
            map.put("MOVIE_TIME3", Integer.parseInt(properties.getProperty("MOVIE_TIME3")));
            map.put("ROOM_MAX", Integer.parseInt(properties.getProperty("ROOM_MAX")));
            map.put("ROOM_MIN", Integer.parseInt(properties.getProperty("ROOM_MIN")));
        } catch (IOException e){
            e.printStackTrace();
        }
        return  map;
    }

    private boolean checkPrice(int price, int minPrice, int maxPrice)
    {
        if(price > minPrice && price < maxPrice)
            return true;
        else
            return false;

    }

    private boolean checkMovieLength(int movieLength, int movieLength_1, int movieLength_2, int movieLength_3)
    {
        if(movieLength == movieLength_1 || movieLength == movieLength_2 || movieLength == movieLength_3)
            return true;
        else
            return false;

    }

    private boolean checkCinemaHall(int hall, int minHall, int maxHall)
    {
        if(hall > minHall && hall < maxHall)
            return true;
        else
            return false;
    }

}

