package prova.java.angular.backend.documentos.repository;

import prova.java.angular.backend.documentos.model.Situacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituacaoRepository extends JpaRepository<Situacao, Long> {
}
