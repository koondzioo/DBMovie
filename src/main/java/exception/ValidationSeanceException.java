package exception;

import java.time.LocalDateTime;

public class ValidationSeanceException extends  CustomException {


    private String name;
    private String varible;
    private LocalDateTime dateTime;


    public ValidationSeanceException(String name, String varible, LocalDateTime dateTime) {
        this.name = name;
        this.varible = varible;
        this.dateTime = dateTime;
    }

    @Override
    public String getMessage() {
        return "VALIDATION SEANCE, " + name + ", " + varible + ", " + dateTime;
    }
}
