all: class

class: Cjt_usuaris_persistencia.class

#driverCjtUsr.class: driverCjtUsr.java Cjt_usuaris_persistencia.class
#	javac driverCjtUsr.java
#DriverUsuari.class: DriverUsuari.java Usuari_pers.class Pair.class
#	javac DriverUsuari.java
	
Cjt_usuaris_persistencia.class: Cjt_usuaris_persistencia.java FileAlreadyExistsException.class Utils_pers.class
	javac Cjt_usuaris_persistencia.java
Ranking_pers.class: Ranking_pers.java FileAlreadyExistsException.class
	javac Ranking_pers.java

FileAlreadyExistsException.class: FileAlreadyExistsException.java
	javac FileAlreadyExistsException.java
FileNotExistsException.class: FileNotExistsException.java
	javac FileNotExistsException.java

Utils_pers.class: Utils_pers.java Pair.class FileNotExistsException.class FileAlreadyExistsException.class
	javac Utils_pers.java

Pair.class: Pair.java
	javac Pair.java

Usuari_pers.class: Usuari_pers.java Utils_pers.class FileNotExistsException.class 
	javac Usuari_pers.java
clean:
	rm -f *.class
cleanDades:
	rm -rf Persistencia
