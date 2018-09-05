# DBMovie - custom exceptions

Simple custom exception appliaction

## Description <h2>

The primary goal of this project is how to learn creating and catching custom exception.
In project CustomException class has been used for catching and save all exceptions to map. 

## Technologies <h2>

* Java 8

## Examples code <h2>

* the first exception that appears
```java
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
```

* the most occurring exception

```java
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
```

