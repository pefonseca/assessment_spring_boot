package infnet.spring.boot.assessment.domain.repository;

import infnet.spring.boot.assessment.domain.entity.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}
