package prova.java.angular.backend.documentos.repository;

import prova.java.angular.backend.documentos.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long> {

    @Query("select d from Documento d where d.quotation.currency.id = :currencyId and d.quotation.id = :quotationId and upper(d.titulo) like '%'||upper(:q)||'%'")
    List<Documento> listAll(Long currencyId, Long quotationId, String q);

    @Query("select d from Documento d where d.quotation.currency.id = :currencyId and d.quotation.id = :quotationId and d.id = :id")
    Optional<Documento> findById(Long currencyId, Long quotationId, Long id);
}
