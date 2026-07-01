package services;

import database.Database;
import models.AuditLog;
import utils.IdGenerator;

public class AuditService {

    public static void createLog(
            String action,
            String entityType,
            String description) {

        AuditLog log = new AuditLog(
                IdGenerator.generateLogId(),
                action,
                entityType,
                description
        );

        Database.addAuditLog(log);
    }

    public static void viewLogs() {

        if (Database.auditLogs.isEmpty()) {

            System.out.println("\nNo Audit Logs Found!");
            return;
        }

        System.out.println("\n===== AUDIT LOGS =====");

        for (AuditLog log : Database.auditLogs) {

            System.out.println(log);
            System.out.println("---------------------");
        }
    }
}