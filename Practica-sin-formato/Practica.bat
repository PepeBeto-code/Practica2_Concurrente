::Ejemplo de Archivo .bat
::Sunny Mirael
@echo off
echo Vamos a compilar
javac -d bin -cp src src/principal/Principal.java
echo Vamos a ejecutar el compilado
java -cp bin principal/Principal
pause
exit
