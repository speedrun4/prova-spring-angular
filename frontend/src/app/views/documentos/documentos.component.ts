import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";

import {DadosTransferencia, Documento, quotation, currency} from "../../models";
import {ApiService} from "../../api/api.service";
import {
  DocumentoDialogComponent
} from "../../shared/documento-dialog/documento-dialog.component";
import {
  TransferirDialogComponent
} from "../../shared/transferir-dialog/transferir-dialog.component";

@Component({
  selector: 'app-documentos',
  templateUrl: './documentos.component.html',
  styleUrls: ['./documentos.component.sass']
})
export class DocumentosComponent implements OnInit {

  q = "";
  quotation: quotation = {nome: "Carregando..."};
  documentos: Documento[] = [];
  currencyId: number = 0;
  quotationId: number = 0;

  constructor(
    private route: ActivatedRoute,
    private api: ApiService,
    public dialog: MatDialog) {
  }

  async ngOnInit() {
    this.listar();
  }

  listar(): void {
    this.route.paramMap.subscribe(async next => {
      this.currencyId = parseInt(<string>next.get("currencyId"));
      this.quotationId = parseInt(<string>next.get("quotationId"));
      this.quotation = await this.api.getquotation(this.currencyId, this.quotationId);
      this.documentos = await this.api.getDocumentos(this.currencyId, this.quotationId, this.q);
    });
  }

  salvar(doc: Documento = {titulo: "Novo documento"}): void {
    this.dialog.open(DocumentoDialogComponent, {
      data: ({...doc}),
      width: "50%"
    }).afterClosed().subscribe(async result => {
      if (result) {
        await this.api.salvarDocumento(this.currencyId, this.quotationId, result);
        this.listar();
      }
    });
  }

  transferir(doc: Documento) {
    this.dialog.open(TransferirDialogComponent, {
      data: ({...doc}),
      width: "50%"
    }).afterClosed().subscribe(async (result: DadosTransferencia) => {
      if (result) {
        const {currency, quotation, documento} = result;
        await this.api.salvarDocumento(<number>currency.id, <number>quotation.id, documento);
        this.listar();
      }
    });
  }

}
