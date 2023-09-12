import {Injectable} from '@angular/core';
import {Axios} from "axios";

import {environment} from "../../environments/environment";
import {Documento, quotation, currency} from "../models";

@Injectable()
export class ApiService {

  private readonly cli: Axios;

  constructor() {
    this.cli = new Axios({
      baseURL: environment.apiBaseUrl,
      headers: {
        "Accept": "application/json",
        "Content-Type": "application/json",
      }
    })
  }

  async getcurrencyes(): Promise<Array<currency>> {
    const result = await this.cli.get("/currencyes")
    return JSON.parse(result.data);
  }

  async getquotation(currencyId: number, quotationId: number): Promise<quotation> {
    const result = await this.cli.get(`/currencyes/${currencyId}/quotations/${quotationId}`)
    return JSON.parse(result.data);
  }

  async getquotations(currencyId?: number): Promise<Array<quotation>> {
    const result = await this.cli.get(`/currencyes/${currencyId}/quotations`)
    return JSON.parse(result.data);
  }

  async getDocumentos(currencyId: number, quotationId: number, q = ""): Promise<Array<Documento>> {
    const result = await this.cli.get(`/currencyes/${currencyId}/quotations/${quotationId}/documentos?q=${q}`)
    return JSON.parse(result.data);
  }

  async salvarDocumento(currencyId: number, quotationId: number, doc: any): Promise<Documento> {
    const path = `/currencyes/${currencyId}/quotations/${quotationId}/documentos`;
    const result = await this.cli[doc.id ? "put" : "post"](path, JSON.stringify(doc));
    return JSON.parse(result.data);
  }
}
