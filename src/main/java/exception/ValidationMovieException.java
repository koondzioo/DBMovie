package exception;

import java.time.LocalDateTime;

public class ValidationMovieException extends CustomException {

        private String name;
        private LocalDateTime dateTime;

    public ValidationMovieException(String name, LocalDateTime dateTime) {
        this.name = name;
        this.dateTime = dateTime;
    }

    @Override
    public String getMessage() {
        return "VALIDATION MOVIE, " + name + ", " + dateTime;
    }
}
