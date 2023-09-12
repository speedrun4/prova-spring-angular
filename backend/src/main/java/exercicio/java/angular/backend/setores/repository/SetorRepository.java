package prova.java.angular.backend.currencyes.repository;

import prova.java.angular.backend.currencyes.model.currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface currencyRepository extends JpaRepository<currency, Long> {

    @Query("select s from currency s where upper(s.nome) like '%'||upper(:q)||'%'")
    List<currency> listAll(String q);
}
