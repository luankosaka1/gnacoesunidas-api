package lkosaka.gnacoesunidas.dto.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequestDto(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
