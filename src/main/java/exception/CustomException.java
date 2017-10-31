package exception;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CustomException extends  Throwable {

    private static Map<String, List<String>> map = new HashMap<>();

    public CustomException() {
    }

    public static void addNewException(CustomException c)
    {
        String className = "";
        if (c instanceof ValidationMovieException)
        {
            className = ValidationMovieException.class.getCanonicalName();
        }
        else if (c instanceof ValidationSeanceException)
        {
            className = ValidationSeanceException.class.getCanonicalName();
        }
        else if (c instanceof ValidationTimeException)
        {
            className = ValidationTimeException.class.getCanonicalName();
        }

        if (getMap().containsKey(className))
        {
            getMap().get(className).add(c.getMessage());
        }
        else
        {
            getMap().put(className, new ArrayList<>(Arrays.asList(c.getMessage())));

        }
    }

    private static LocalDateTime getExceptionTime(String s)
    {
        String[] elements = s.split(", ");
        return LocalDateTime.parse(elements[elements.length - 1], DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }
    public static String theEarliestException()
    {
        String exception =  map
                .entrySet()
                .stream()
                .flatMap(e -> e.getValue().stream())
                .sorted(Comparator.comparing(CustomException::getExceptionTime))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Can't view first exception"));


        switch (exception.split(", ")[0])
        {
            case "VALIDATION MOVIE":
                return ValidationMovieException.class.getCanonicalName();
            case "VALIDATION SEANCE":
                return ValidationSeanceException.class.getCanonicalName();
            case "VALIDATION TIME":
                return ValidationTimeException.class.getCanonicalName();
            default:
                return "";
        }

    }

    public static String theMostOccuringException()
    {
        return map
                .entrySet()
                .stream()
                .sorted((e1,e2) -> Integer.compare(e2.getValue().size(), e1.getValue().size()))
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Can't get exception"))
                .getKey();
    }


    public static void clearMap()
    {
        map.clear();
    }

    public static Map<String, List<String>> getMap() {
        return map;
    }


    public static void saveToFile(String fileName)
    {
        try {
            FileWriter fw = new FileWriter(fileName, true);
            PrintWriter pw = new PrintWriter(fw);
            Scanner scanner = new Scanner(System.in);
            map.forEach((k,v) -> pw.println(k + ":\n" + v));
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
