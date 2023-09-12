package prova.java.angular.backend.documentos.service.impl;

import prova.java.angular.backend.documentos.model.Documento;
import prova.java.angular.backend.documentos.model.Situacao;
import prova.java.angular.backend.documentos.repository.DocumentoRepository;
import prova.java.angular.backend.documentos.repository.SituacaoRepository;
import prova.java.angular.backend.documentos.service.IDocumentoService;
import prova.java.angular.backend.quotations.model.quotation;
import prova.java.angular.backend.quotations.repository.quotationRepository;
import prova.java.angular.backend.currencyes.model.currency;
import prova.java.angular.backend.currencyes.repository.currencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService implements IDocumentoService {

    @Autowired
    private DocumentoRepository repository;

    @Autowired
    private quotationRepository quotationRepository;

    @Autowired
    private currencyRepository currencyRepository;

    @Autowired
    private SituacaoRepository situacaoRepository;

    @Override
    public List<Documento> listAll(Long currencyId, Long quotationId, String q) {
        return repository.listAll(currencyId, quotationId, q);
    }

    @Override
    public Optional<Documento> findById(Long currencyId, Long quotationId, Long id) {
        return repository.findById(currencyId, quotationId, id);
    }

    @Override
    @Transactional
    public Documento insert(Long currencyId, Long quotationId, Documento documento) {
        quotation quotation = validacoes(currencyId, quotationId);
        documento.setquotation(quotation);
        Situacao novo = situacaoRepository.findById(Situacao.NOVO)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "situação inválida"));
        documento.setSituacao(novo);
        return repository.save(documento);
    }

    @Override
    @Transactional
    public Documento update(Long currencyId, Long quotationId, Documento documento) {
        quotation quotation = validacoes(currencyId, quotationId);
        Documento existente = repository.findById(documento.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "documento não existe"));
        if (!existente.getquotation().equals(quotation)) {
            Situacao transferido = situacaoRepository.findById(Situacao.TRANSFERIDO)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "situação inválida"));
            documento.setSituacao(transferido);
        }
        existente.setTitulo(documento.getTitulo());
        return repository.save(existente);
    }

    private quotation validacoes(Long currencyId, Long quotationId) {
        currency currency = currencyRepository.findById(currencyId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "currency inválido"));
        quotation quotation = quotationRepository.findById(quotationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "quotation inválida"));
        if (!quotation.getcurrency().equals(currency))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quotation de currency diferente");
        return quotation;
    }

}
