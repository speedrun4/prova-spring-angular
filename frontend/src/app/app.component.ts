import {Component, Inject, OnInit} from '@angular/core';
import {ApiService} from "./api/api.service";
import {quotation, currency} from "./models";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {

  currencyes: Array<currency> = [];
  currencyAtual?: currency;
  quotations: Array<quotation> = [];

  constructor(private readonly api: ApiService) {
  }

  async ngOnInit() {
    this.currencyes = await this.api.getcurrencyes();
  }

  async getquotations(currency: currency) {
    this.currencyAtual = currency;
    this.quotations = []
    this.quotations = await this.api.getquotations(this.currencyAtual.id);
  }
}
