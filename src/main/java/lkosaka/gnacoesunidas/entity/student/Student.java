package lkosaka.gnacoesunidas.entity.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "idStudent")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;
    @Column
    @NotBlank
    private String name;
    @Column(columnDefinition = "boolean default true")
    private Boolean status = true;
    @Column
    @NotBlank
    @Email
    private String email;
    @Column
    private LocalDate birthDay;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;

    public Student(String name, String email, LocalDate birthDay, Address address, Contact contact) {
        this.name = name;
        this.email = email;
        this.birthDay = birthDay;
        this.address = address;
        this.contact = contact;
    }
}
