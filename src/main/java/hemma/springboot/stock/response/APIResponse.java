package hemma.springboot.stock.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Builder(builderMethodName = "apiResponse")
@Getter
@AllArgsConstructor(access = PRIVATE)
public class APIResponse<T> {
    private final int responseCode;
    private final String responseMessage;
    private final T responseBody;
}