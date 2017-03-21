package hemma.springboot.stock.response;

public enum ResponseMessage {
    successful("Successful"),
    invalidRequest("Invalid request"),
    internalServerError("Internal server error");

    private final String value;

    ResponseMessage(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
