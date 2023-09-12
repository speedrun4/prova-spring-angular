package prova.java.angular.backend.quotations.controller;

import prova.java.angular.backend.quotations.model.quotation;
import prova.java.angular.backend.quotations.service.IquotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("currencyes/{currencyId}/quotations")
public class quotationController {

    @Autowired
    private IquotationService service;

    @GetMapping
    public List<quotation> listAll(
            @PathVariable(name = "currencyId") Long currencyId,
            @RequestParam(defaultValue = "") String q) {
        return service.listAll(currencyId, q);
    }

    @GetMapping("{id}")
    public quotation findById(
            @PathVariable(name = "currencyId") Long currencyId,
            @PathVariable(name = "id") Long id) {
        return service.findById(currencyId, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
