package prova.java.angular.backend.documentos.model;

import prova.java.angular.backend.quotations.model.quotation;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "documentos")
public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "estados_documento_id")
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "quotations_id")
    private quotation quotation;

    private String titulo;
}
