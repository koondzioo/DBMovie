package cinema;

import java.util.Random;

public class GetRandom implements IMovie {

    public final static String[] TITLE = new String[]{"RYS", "MIS", "Krzys", "EDEN"};
    public final static String[] GENRE = new String[]{"komedia", "asd", "horror", "komedia"};
    public final static String[] DIRECTOR = new String[]{"Adam Jackowski", "Jan Nowak", "Adam małysz", "Janusz Gajos"};


    public Movie getMovie(){
        Random rnd = new Random();
        int number = rnd.nextInt(TITLE.length);
        Movie movie = null;
        try {
            movie = new Movie(TITLE[number], GENRE[number], DIRECTOR[number]);
        } catch (exception.CustomException e) {
            exception.CustomException.addNewException(e);
        }
        return movie;
    }


}
