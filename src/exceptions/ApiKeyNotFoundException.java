package exceptions;

public class ApiKeyNotFoundException extends RuntimeException {
    private final String message;

    public ApiKeyNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
