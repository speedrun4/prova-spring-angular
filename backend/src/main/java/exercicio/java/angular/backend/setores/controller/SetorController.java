package prova.java.angular.backend.currencyes.controller;

import prova.java.angular.backend.currencyes.model.currency;
import prova.java.angular.backend.currencyes.service.IcurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("currencyes")
public class currencyController {

    @Autowired
    private IcurrencyService service;

    @GetMapping
    public List<currency> listAll(@RequestParam(defaultValue = "") String q) {
        return service.listAll(q);
    }

    @GetMapping("{id}")
    public currency findById(@PathVariable("id") Long id) {
        return service.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
