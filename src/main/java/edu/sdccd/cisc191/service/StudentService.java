package edu.sdccd.cisc191.service;

import edu.sdccd.cisc191.model.Student;
import edu.sdccd.cisc191.repository.StudentRepository;

import java.util.List;

public class StudentService {
    // ??????
    // This should not be empty.
    // Also, this is the best place to implement error-checking.
    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(Student student) {
        this.repository.save(student);
    }

    public Student getStudent(int id) {
        if (id < 1) throw new IllegalArgumentException("ID must be > 0");
        return this.repository.findById(id);
    }

    public List<Student> getAllStudents() {
        return this.repository.findAll();
    }

    public void changeGpa(int id, double newGpa) {
        if (id < 1) throw new IllegalArgumentException("ID must be > 0");
        if (newGpa < 0.0 || newGpa > 4.0) throw new IllegalArgumentException("Invalid GPA");

        this.repository.updateGpa(id, newGpa);
    }

    public void removeStudent(int id) {
        if (id < 1) throw new IllegalArgumentException("ID must be > 0");
        this.repository.deleteById(id);
    }
}
