package exception;

import java.time.LocalDateTime;

public class ValidationTimeException extends CustomException {

    private String name;
    private double varible;
    private LocalDateTime dateTime;

    public ValidationTimeException(String name, double varible, LocalDateTime dateTime) {
        this.name = name;
        this.varible = varible;
        this.dateTime = dateTime;
    }

    @Override
    public String getMessage() {
        return "VALIDATION TIME, " + name + ", " + varible + ", " + dateTime;
    }
}
