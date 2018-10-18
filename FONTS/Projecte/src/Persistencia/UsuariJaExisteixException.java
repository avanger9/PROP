package Persistencia;
public class UsuariJaExisteixException extends Exception {
	UsuariJaExisteixException(String file) {
		super(file);
	}
}
