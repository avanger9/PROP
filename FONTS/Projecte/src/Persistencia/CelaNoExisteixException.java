package Persistencia;
public class CelaNoExisteixException extends Exception {
	CelaNoExisteixException(String file) {
		super(file);
	}
}
