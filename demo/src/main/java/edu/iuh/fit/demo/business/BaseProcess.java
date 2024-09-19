package edu.iuh.fit.demo.business;

import edu.iuh.fit.demo.models.Student;

import java.util.ArrayList;
import java.util.List;

public class BaseProcess {
    public List<Student> students = new ArrayList<>();



    public List<Student> getAll() {
        students.add(new Student("1", "Nguyen Van A", "q"));
        students.add(new Student("2", "Nguyen Van B", "w"));
        students.add(new Student("3", "Nguyen Van C", "e"));


        return students;
    }

    public Student getId(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public Student persist(Student student) {
        students.add(student);
        return student;
    }

}
