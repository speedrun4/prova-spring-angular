package prova.java.angular.backend.currencyes.service.impl;

import prova.java.angular.backend.currencyes.model.currency;
import prova.java.angular.backend.currencyes.repository.currencyRepository;
import prova.java.angular.backend.currencyes.service.IcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class currencyService implements IcurrencyService {

    @Autowired
    private currencyRepository repository;

    @Override
    public List<currency> listAll(String q) {
        return repository.listAll(q);
    }

    @Override
    public Optional<currency> findById(Long id) {
        return repository.findById(id);
    }
}
