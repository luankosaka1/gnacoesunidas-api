package lkosaka.gnacoesunidas.repository;

import lkosaka.gnacoesunidas.domain.student.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<Student> findAllByStatusTrue(Pageable pagination);
}
