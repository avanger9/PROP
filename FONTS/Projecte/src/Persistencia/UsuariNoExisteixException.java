package Persistencia;
public class UsuariNoExisteixException extends Exception {
	UsuariNoExisteixException(String file) {
		super(file);
	}
}
