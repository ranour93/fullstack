package com.example.fullstack.student;

import com.example.fullstack.student.exception.BadRequestException;
import com.example.fullstack.student.exception.StudentNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void addStudent(Student student) {
        if(studentRepository.selectExistsEmail(student.getEmail())){
            throw new BadRequestException(
                    "Student with email " + student.getEmail() + "already exists."
            );
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) throws StudentNotFoundException{
        if(!studentRepository.existsById(studentId)) {
            throw new StudentNotFoundException(
                    "Student with id " + studentId + " does not exist.");
        }
        studentRepository.deleteById(studentId);
    }
}
