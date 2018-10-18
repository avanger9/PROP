package Persistencia;
public class FileNotExistsException extends Exception {
	FileNotExistsException(String file) {
		super(file);
	}
}
