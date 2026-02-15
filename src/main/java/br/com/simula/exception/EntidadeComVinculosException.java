package br.com.simula.exception;

/**
 * Lançada quando uma exclusão não pode ser realizada pois existem outros
 * registros vinculados à entidade (violação de integridade referencial).
 */
public class EntidadeComVinculosException extends RuntimeException {

    public EntidadeComVinculosException(String message) {
        super(message);
    }
}
