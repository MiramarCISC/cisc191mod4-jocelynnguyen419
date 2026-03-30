package edu.sdccd.cisc191.util;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseInitializer {

    public static void initialize() {
        // TODO create students table if it does not exist
        // students: id INT PRIMARY KEY, name VARCHAR(100) NOT NULL, gpa DOUBLE NOT NULL

        // TODO create courses table if it does not exist
        // courses: id INT PRIMARY KEY, title VARCHAR(100) NOT NULL, student_id INT,
        // FOREIGN KEY (student_id) REFERENCES students(id)
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS students (
                            id INT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            gpa DOUBLE NOT NULL
                        )
                    """);

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS courses (
                            id INT PRIMARY KEY,
                            title VARCHAR(100) NOT NULL,
                            student_id INT,
                            FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE
                        )
                    """);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
