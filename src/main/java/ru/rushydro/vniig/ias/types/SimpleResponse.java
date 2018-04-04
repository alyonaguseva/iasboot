package ru.rushydro.vniig.ias.types;

public class SimpleResponse {

    private String message;

    private boolean result;

    public SimpleResponse(boolean result) {
        this.result = result;
    }

    public SimpleResponse(boolean result, String message) {
        this.message = message;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
