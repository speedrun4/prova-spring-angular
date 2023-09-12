package prova.java.angular.backend.quotations.service.impl;

import prova.java.angular.backend.quotations.model.quotation;
import prova.java.angular.backend.quotations.repository.quotationRepository;
import prova.java.angular.backend.quotations.service.IquotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class quotationService implements IquotationService {

    @Autowired
    private quotationRepository repository;

    @Override
    public List<quotation> listAll(Long currencyId, String q) {
        return repository.listAll(currencyId, q);
    }

    @Override
    public Optional<quotation> findById(Long currencyId, Long id) {
        return repository.findById(currencyId, id);
    }
}
