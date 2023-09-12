import currency from "./currency";
import quotation from "./quotation";
import {Documento} from "./index";

export default interface DadosTransferencia {
  currency: currency;
  quotation: quotation;
  documento: Documento;
}
