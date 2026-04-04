package edu.sdccd.cisc191.app;

import edu.sdccd.cisc191.model.*;
import edu.sdccd.cisc191.repository.*;
import edu.sdccd.cisc191.service.StudentService;
import edu.sdccd.cisc191.util.DatabaseInitializer;

public class Main {
    public static void main(String[] args) {
        DatabaseInitializer.initialize();

        // Call StudentService instead of a StudentRepository
        StudentService studentService = new StudentService(new JdbcStudentRepository());
        CourseRepository courseRepo = new JdbcCourseRepository();

        studentService.addStudent(new Student(1, "Blossom", 3.8));
        studentService.addStudent(new Student(2, "Bubbles", 3.5));
        studentService.addStudent(new Student(3, "Buttercup", 3.9));

        courseRepo.save(new Course(1, "Pre-Calc", 1));
        courseRepo.save(new Course(2, "Biology", 1));
        courseRepo.save(new Course(3, "AP Gov", 2));

        System.out.println(studentService.getAllStudents());

        System.out.println(studentService.getStudent(1));

        System.out.println(courseRepo.findByStudentId(1));

        studentService.changeGpa(1, 4.0);

        studentService.removeStudent(2);

        System.out.println("After updates:");
        System.out.println(studentService.getAllStudents());
        System.out.println(courseRepo.findAll());
    }
}