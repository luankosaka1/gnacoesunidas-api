package lkosaka.gnacoesunidas.dto.student;

public record AddressRequestDto(
        String street,
        String number,
        String complement,
        String neighborhood,
        String city,
        String state,
        String zipCode
) {
}
