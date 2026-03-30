package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.*;
import edu.sdccd.cisc191.repository.*;
import edu.sdccd.cisc191.util.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        // TODO initialize database
        DatabaseInitializer.initialize();

        // TODO create student service and repositories
        StudentRepository studentRepo = new JdbcStudentRepository();
        CourseRepository courseRepo = new JdbcCourseRepository();

        // TODO add at least 3 students
        studentRepo.save(new Student(1, "Blossom", 3.8));
        studentRepo.save(new Student(2, "Bubbles", 3.5));
        studentRepo.save(new Student(3, "Buttercup", 3.9));

        // TODO add at least 3 courses linked to students
        courseRepo.save(new Course(1, "Pre-Calc", 1));
        courseRepo.save(new Course(2, "Biology", 1));
        courseRepo.save(new Course(3, "AP Gov", 2));

        // TODO print all students
        System.out.println(studentRepo.findAll());

        // TODO find one student by ID
        System.out.println(studentRepo.findById(1));

        // TODO print courses for a student
        System.out.println(courseRepo.findByStudentId(1));

        // TODO update one GPA
        studentRepo.updateGpa(1, 4.0);

        // TODO delete one student
        studentRepo.deleteById(2);

        // TODO print remaining students and courses
        System.out.println("After updates:");
        System.out.println(studentRepo.findAll());
        System.out.println(courseRepo.findAll());
    }
}