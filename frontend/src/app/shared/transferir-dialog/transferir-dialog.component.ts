import {Component, Inject, OnInit} from '@angular/core';

import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Documento, quotation, currency} from "../../models";
import {ApiService} from "../../api/api.service";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-transferir-dialog',
  templateUrl: './transferir-dialog.component.html',
  styleUrls: ['./transferir-dialog.component.sass']
})
export class TransferirDialogComponent implements OnInit {

  currencyes: currency[] = [];
  quotations: quotation[] = [];

  currencyControl = new FormControl<currency | ''>('');
  quotationControl = new FormControl<quotation | ''>('');

  constructor(
    private api: ApiService,
    @Inject(MAT_DIALOG_DATA) public data: Documento,
    public dialogRef: MatDialogRef<TransferirDialogComponent>
  ) {
  }

  async ngOnInit() {
    this.currencyes = await this.api.getcurrencyes()

    this.currencyControl.valueChanges.subscribe(async (value) => {
      this.quotationControl.setValue("");
      if (value && value.id) {
        this.quotations = await this.api.getquotations(value.id);
      } else {
        this.quotations = [];
      }
    });

    this.quotationControl.valueChanges.subscribe(async (value) => {
      if (value && value.id) {
        this.data.quotation = value;
      }
    });
  }

  displayFn(v = {nome: ""}) {
    return v.nome;
  }

}
