package lkosaka.gnacoesunidas.domain.student;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
    private String phone1;
    private String phone2;
    private String cellphone1;
    private String cellphone2;
}
