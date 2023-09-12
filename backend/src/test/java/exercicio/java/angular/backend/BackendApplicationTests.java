package prova.java.angular.backend;

import prova.java.angular.backend.documentos.model.Documento;
import prova.java.angular.backend.documentos.model.Situacao;
import prova.java.angular.backend.documentos.repository.DocumentoRepository;
import prova.java.angular.backend.documentos.repository.SituacaoRepository;
import prova.java.angular.backend.documentos.service.IDocumentoService;
import prova.java.angular.backend.quotations.model.quotation;
import prova.java.angular.backend.quotations.service.IquotationService;
import prova.java.angular.backend.currencyes.model.currency;
import prova.java.angular.backend.currencyes.service.IcurrencyService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class BackendApplicationTests {

    @Autowired
    private IcurrencyService currencyService;

    @Autowired
    private IquotationService quotationService;

    @Autowired
    private IDocumentoService documentoService;

    @Autowired
    private SituacaoRepository situacaoRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void deveriaListarcurrencyes() {
        List<currency> result = currencyService.listAll("");

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(3, result.size());
    }

    @Test
    public void deveriaRecuperarcurrencyVendas() {
        currency result = currencyService.findById(2L).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Vendas", result.getNome());
    }

    @Test
    public void deveriaListarquotations() {
        List<quotation> result = quotationService.listAll(1L, "");

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(2, result.size());
    }

    @Test
    public void deveriaRecuperarquotationPrioritaria() {
        quotation result = quotationService.findById(2L, 3L).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals("Prioritário", result.getNome());
    }

    @Test
    public void deveriaCriarDocumento() {
        Documento doc = new Documento();
        doc.setTitulo("documento de teste");
        Documento result = documentoService.insert(1L, 1L, doc);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertNotNull(result.getSituacao());
        Assertions.assertNotNull(result.getquotation());
    }

    @Test
    public void deveriaListarDocumentos() {
        Documento doc = new Documento();
        doc.setTitulo("documento de teste");
        documentoService.insert(1L, 1L, doc);

        List<Documento> result = documentoService.listAll(1L, 1L, "");

        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    public void deveriaRecuperarUmDocumento() {
        Documento doc = new Documento();
        doc.setTitulo("documento de teste");
        doc = documentoService.insert(1L, 1L, doc);

        Documento result = documentoService.findById(1L, 1L, doc.getId()).get();

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
    }

    @Test
    @Transactional
    public void deveriaAtualizarUmDocumento() {
        Documento doc = new Documento();
        doc.setTitulo("documento de teste");
        documentoService.insert(1L, 1L, doc);

        documentoService.update(1L, 2L, doc);

        Situacao transferido = situacaoRepository.findById(Situacao.TRANSFERIDO).get();

        Documento result = documentoRepository.getReferenceById(doc.getId());

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(transferido, result.getSituacao());

    }

}
