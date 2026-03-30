package edu.sdccd.cisc191.repository;

import edu.sdccd.cisc191.model.Course;
import edu.sdccd.cisc191.util.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcCourseRepository implements CourseRepository {

    @Override
    public void save(Course course) {
        // TODO use PreparedStatement INSERT
        String sql = "INSERT INTO courses (id, title, student_id) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, course.getId());
            stmt.setString(2, course.getTitle());
            stmt.setInt(3, course.getStudentId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Course> findByStudentId(int studentId) {
        // TODO query courses by student_id and map to List<Course>
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE student_id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("student_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }

    @Override
    public List<Course> findAll() {
        // TODO query all rows and map to List<Course>
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("student_id")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courses;
    }
}

// copied og before deleting everything and pasting back because indents were weird/not lining up..