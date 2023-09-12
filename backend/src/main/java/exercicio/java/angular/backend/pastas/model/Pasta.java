package prova.java.angular.backend.quotations.model;

import prova.java.angular.backend.currencyes.model.currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="quotations")
public class quotation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="currencyes_id")
    private currency currency;

    private String nome;
}
