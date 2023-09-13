package lkosaka.gnacoesunidas.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lkosaka.gnacoesunidas.domain.student.Address;
import lkosaka.gnacoesunidas.domain.student.Contact;

import java.time.LocalDate;

public record UpdateStudentRequestDto(
        @NotNull
        Long idStudent,
        @NotBlank
        String name,
        String email,
        @NotNull
        LocalDate birthDay,
        @NotNull
        Address address,
        @NotNull
        Contact contact
) {
}
