package manago.com.restbackend.exception.model;

import lombok.Getter;

@Getter
public enum ErrorMessages {
    MISSING_FIELD("Required field is missing"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    RECORD_NOT_FOUND("Record was not found"),
    RECORD_NOT_CREATED("Could not create record"),
    RECORD_NOT_DELETED("Could not delete record"),
    RECORD_NOT_UPDATED("Could not update record"),
    TASK_HAS_SUBTASKS("Could not delete task. There are related subtasks.");

    private String errorMessage;

    ErrorMessages(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}