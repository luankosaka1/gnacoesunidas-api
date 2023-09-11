package lkosaka.gnacoesunidas.dto.student;

import java.time.LocalDate;

public record CreateStudentRequestDto(
        String name,
        String email,
        LocalDate birthDay,
        AddressRequestDto address,
        ContactRequestDto contact
) {
}