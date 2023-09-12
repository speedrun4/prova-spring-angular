package prova.java.angular.backend.documentos.controller;

import prova.java.angular.backend.documentos.model.Documento;
import prova.java.angular.backend.documentos.service.IDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("currencyes/{currencyId}/quotations/{quotationId}/documentos")
public class DocumentoController {

    @Autowired
    private IDocumentoService service;

    @GetMapping
    public List<Documento> listAll(
            @PathVariable Long currencyId,
            @PathVariable Long quotationId,
            @RequestParam(defaultValue = "") String q) {
        return service.listAll(currencyId, quotationId, q);
    }

    @GetMapping("{id}")
    public Documento findById(
            @PathVariable Long currencyId,
            @PathVariable Long quotationId,
            @PathVariable Long id) {
        return service.findById(currencyId, quotationId, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public Documento insert(
            @PathVariable Long currencyId,
            @PathVariable Long quotationId,
            @RequestBody Documento novo) {
        return service.insert(currencyId, quotationId, novo);
    }

    @PutMapping
    public Documento update(
            @PathVariable Long currencyId,
            @PathVariable Long quotationId,
            @RequestBody Documento documento) {
        return service.update(currencyId, quotationId, documento);
    }
}
