package Persistencia;
public class FileAlreadyExistsException extends Exception {
	FileAlreadyExistsException(String file) {
		super(file);
	}
}