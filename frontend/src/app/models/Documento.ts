import quotation from "./quotation";
import Situacao from "./Situacao";

export default interface Documento {
  id?: number;
  titulo: string;
  quotation?: quotation;
  situacao?: Situacao;
}
