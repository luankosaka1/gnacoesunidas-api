package lkosaka.gnacoesunidas.dto.student;

import lkosaka.gnacoesunidas.entity.student.Address;
import lkosaka.gnacoesunidas.entity.student.Contact;
import lkosaka.gnacoesunidas.entity.student.Student;

import java.time.LocalDate;

public record StudentResponseDto(
        Long idStudent,
        String name,
        String email,
        LocalDate birthDay,
        Address address,
        Contact contact
) {
    public StudentResponseDto(Student student) {
        this(
                student.getIdStudent(),
                student.getName(),
                student.getEmail(),
                student.getBirthDay(),
                student.getAddress(),
                student.getContact()
        );
    }
}
