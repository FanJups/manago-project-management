package manago.com.restbackend.shared.response;

import lombok.Data;

import java.util.Date;

@Data
public class HistoryResponse {
    Long historyId;
    String operationId;
    Date createdAt;
    Date updatedAt;
}
