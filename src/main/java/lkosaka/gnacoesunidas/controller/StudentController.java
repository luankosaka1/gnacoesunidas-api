package lkosaka.gnacoesunidas.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lkosaka.gnacoesunidas.dto.student.CreateStudentRequestDto;
import lkosaka.gnacoesunidas.dto.student.StudentResponseDto;
import lkosaka.gnacoesunidas.dto.student.UpdateStudentRequestDto;
import lkosaka.gnacoesunidas.domain.student.Address;
import lkosaka.gnacoesunidas.domain.student.Contact;
import lkosaka.gnacoesunidas.domain.student.Student;
import lkosaka.gnacoesunidas.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid CreateStudentRequestDto dto, UriComponentsBuilder uriComponentsBuilder) {
        var result = repository.save(new Student(
                dto.name(),
                dto.email(),
                dto.birthDay(),
                new Address(
                        dto.address().street(),
                        dto.address().number(),
                        dto.address().complement(),
                        dto.address().neighborhood(),
                        dto.address().city(),
                        dto.address().state(),
                        dto.address().zipCode()
                ),
                new Contact(
                        dto.contact().phone1(),
                        dto.contact().phone2(),
                        dto.contact().cellphone1(),
                        dto.contact().cellphone2()
                )
        ));

        var uri = uriComponentsBuilder.path("/student/{id}").buildAndExpand(result.getIdStudent()).toUri();
        return ResponseEntity.created(uri).body(new StudentResponseDto(result));
    }

    @GetMapping
    public ResponseEntity<Page<StudentResponseDto>> getAll(@PageableDefault(sort = {"name"}) Pageable pagination) {
        return ResponseEntity.ok(repository.findAllByStatusTrue(pagination).map(StudentResponseDto::new));
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(new StudentResponseDto(repository.getReferenceById(id)));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateStudentRequestDto dto) {
        var student = repository.getReferenceById(dto.idStudent());
        student.setName(dto.name());
        student.setEmail(dto.email());
        student.setAddress(dto.address());
        student.setContact(dto.contact());

       return ResponseEntity.ok(new StudentResponseDto(repository.save(student)));
    }

    @PatchMapping("/{id}/disable")
    @Transactional
    public ResponseEntity disable(@PathVariable("id") Long id) {
        Student student = repository.getReferenceById(id);
        student.setStatus(false);
        repository.save(student);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/enable")
    @Transactional
    public ResponseEntity enable(@PathVariable("id") Long id) {
        Student student = repository.getReferenceById(id);
        student.setStatus(true);

        return ResponseEntity.ok(new StudentResponseDto(repository.save(student)));
    }
}
