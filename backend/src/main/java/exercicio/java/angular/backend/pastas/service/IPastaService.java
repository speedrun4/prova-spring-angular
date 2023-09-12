package prova.java.angular.backend.quotations.service;

import prova.java.angular.backend.quotations.model.quotation;

import java.util.List;
import java.util.Optional;

public interface IquotationService {

    List<quotation> listAll(Long currencyId, String q);

    Optional<quotation> findById(Long currencyId, Long id);
}
