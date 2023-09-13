package lkosaka.gnacoesunidas.dto.student;

import lkosaka.gnacoesunidas.domain.student.Address;
import lkosaka.gnacoesunidas.domain.student.Contact;
import lkosaka.gnacoesunidas.domain.student.Student;

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
