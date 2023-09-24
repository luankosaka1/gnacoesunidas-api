package lkosaka.gnacoesunidas.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateStudentRequestDto(
        @NotBlank
        String name,
        @Email
        String email,
        @NotNull
        LocalDate birthDay,
        AddressRequestDto address,
        ContactRequestDto contact
) {
}