package prova.java.angular.backend.quotations.repository;

import prova.java.angular.backend.quotations.model.quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface quotationRepository extends JpaRepository<quotation,Long> {

    @Query("select p from quotation p where p.currency.id = :currencyId and upper(p.nome) like '%'||upper(:q)||'%'")
    List<quotation> listAll(Long currencyId, String q);

    @Query("select p from quotation p where p.currency.id = :currencyId and  p.id = :id")
    Optional<quotation> findById(Long currencyId, Long id);
}
