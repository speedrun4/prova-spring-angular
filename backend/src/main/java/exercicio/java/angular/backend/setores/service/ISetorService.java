package prova.java.angular.backend.currencyes.service;

import prova.java.angular.backend.currencyes.model.currency;

import java.util.List;
import java.util.Optional;

public interface IcurrencyService {

    List<currency> listAll(String q);

    Optional<currency> findById(Long id);
}
