package prova.java.angular.backend.documentos.service;

import prova.java.angular.backend.documentos.model.Documento;

import java.util.List;
import java.util.Optional;

public interface IDocumentoService {

    List<Documento> listAll(Long currencyId, Long quotationId, String q);

    Optional<Documento> findById(Long currencyId, Long quotationId, Long id);

    Documento insert(Long currencyId, Long quotationId, Documento novo);

    Documento update(Long currencyId, Long quotationId, Documento documento);
}
