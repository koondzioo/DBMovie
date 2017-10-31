package cinema;

import exception.ValidationTimeException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Repertoire {

    private Map<LocalDate, Map<Seance, List<LocalTime>>> map = new HashMap<LocalDate, Map<Seance, List<LocalTime>>>();

    @Override
    public String toString() {
        return "cinema.Repertoire{" +
                "map=" + map +
                '}';
    }

    public Map<LocalDate, Map<Seance, List<LocalTime>>> getMap() {
        return map;
    }

    public void setMap(Map<LocalDate, Map<Seance, List<LocalTime>>> map) {
        this.map = map;
    }

    public Repertoire(String fileName, LocalDate minDate, LocalDate maxDate) {

        Map<LocalDate, Map<Seance, List<LocalTime>>> map = new LinkedHashMap<>();
        try {
            FileReader fr = new FileReader(fileName);
            Scanner scanner = new Scanner(fr);
            while (scanner.hasNextLine()) {
                Map<Seance, List<LocalTime>> seanceListMap = new HashMap<>();
                String[] line = scanner.nextLine().split(";");
                LocalDate localDate = LocalDate.parse(line[0]);
                if (localDate.isAfter(minDate) && localDate.isBefore(maxDate))
                {
                    try {
                        Movie film = null;
                        film = new Movie(line[1], line[2], line[3]);
                        Seance seance = new Seance(film, Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]));
                        String[] time = line[7].split(",");
                        List<LocalTime> timeList = new ArrayList<>();
                        for (String str : time)
                        {
                                String[] tmp = str.split(":");
                                if(!checkHours(Integer.parseInt(tmp[0])))
                                {
                                    throw new ValidationTimeException(film.getTitleMovie(), Double.parseDouble(tmp[0]), LocalDateTime.now());
                                }
                                if(!checkMinuts(Integer.parseInt(tmp[1])))
                                {
                                 throw new ValidationTimeException(film.getTitleMovie(), Double.parseDouble(tmp[1]), LocalDateTime.now());
                                }
                                timeList.add(LocalTime.parse(str));
                        }
                        if (map.containsKey(localDate))
                        {
                            map.get(localDate).put(seance, timeList);
                        } else {
                            seanceListMap.put(seance, timeList);
                            map.put(localDate, seanceListMap);
                        }
                    } catch (exception.CustomException e) {
                        exception.CustomException.addNewException(e);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.map =  map;
    }


    private boolean checkHours(int hour)
    {
        return  hour < 1 ? false : hour > 24 ?  false : true;
    }

    private boolean checkMinuts(int minut)
    {
        return  minut < 0 ? false : minut > 59 ?  false : true;
    }


}
