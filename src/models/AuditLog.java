package models;

import java.time.LocalDateTime;

public class AuditLog {

    private int logId;

    private String action;
    private String entityType;
    private String description;

    private LocalDateTime createdOn;

    public AuditLog(int logId,
                    String action,
                    String entityType,
                    String description) {

        this.logId = logId;
        this.action = action;
        this.entityType = entityType;
        this.description = description;

        this.createdOn = LocalDateTime.now();
    }

    @Override
    public String toString() {

        return "Log ID : " + logId +
                "\nAction : " + action +
                "\nEntity : " + entityType +
                "\nDescription : " + description +
                "\nCreated On : " + createdOn +
                "\n";
    }
}