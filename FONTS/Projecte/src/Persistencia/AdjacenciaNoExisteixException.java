package Persistencia;
public class AdjacenciaNoExisteixException extends Exception {
	AdjacenciaNoExisteixException(String file) {
		super(file);
	}
}
