package infnet.spring.boot.assessment.domain.repository;

import infnet.spring.boot.assessment.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query(value = "SELECT DISTINCT a FROM tb_aluno a JOIN a.notas n WHERE n.disciplina.id = :disciplinaId AND n.valor >= 7.0")
    List<Aluno> findAlunosPorDisciplinaComNotaMaiorOuIgualSete(@Param("disciplinaId") Long disciplinaId);

    @Query(value = "SELECT DISTINCT a FROM tb_aluno a JOIN a.notas n WHERE n.disciplina.id = :disciplinaId AND n.valor < 7.0")
    List<Aluno> findAlunosPorDisciplinaComNotaMenorQueSete(@Param("disciplinaId") Long disciplinaId);
}
