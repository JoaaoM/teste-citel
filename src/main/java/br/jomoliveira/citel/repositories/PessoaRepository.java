package br.jomoliveira.citel.repositories;

import br.jomoliveira.citel.dtos.CandidatosPorEstadoDTO;
import br.jomoliveira.citel.dtos.ImcMedioPorFaixaEtariaDTO;
import br.jomoliveira.citel.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query(value="SELECT NEW br.jomoliveira.citel.dtos.CandidatosPorEstadoDTO(p.endereco.estado, count(p)) FROM Pessoa p GROUP BY p.endereco.estado")
    List<CandidatosPorEstadoDTO> candidatosPorEstado ();

    @Query("SELECT " +
            "NEW br.jomoliveira.citel.dtos.ImcMedioPorFaixaEtariaDTO(" +
            "CASE WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 0 AND 10 THEN '0-10' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 11 AND 20 THEN '11-20' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 21 AND 30 THEN '21-30' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 31 AND 40 THEN '31-40' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 41 AND 50 THEN '41-50' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 41 AND 50 THEN '51-60' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 41 AND 50 THEN '61-70' " +
            "ELSE '71+' END, " +
            "AVG(p.peso / (p.altura * p.altura))) " +
            "FROM Pessoa p " +
            "GROUP BY " +
            "CASE WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 0 AND 10 THEN '0-10' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 11 AND 20 THEN '11-20' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 21 AND 30 THEN '21-30' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 31 AND 40 THEN '31-40' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 41 AND 50 THEN '41-50' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 41 AND 50 THEN '51-60' " +
            "WHEN YEAR(CURRENT_DATE()) - YEAR(p.dataNascimento) BETWEEN 41 AND 50 THEN '61-70' " +
            "ELSE '71+' END ")
    List<ImcMedioPorFaixaEtariaDTO> calcularIMCMedioPorFaixaIdade();
}