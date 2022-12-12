# Airline Company Project

## Compiling
```bash
 mkdir bin
 javac -d bin src/Main.java
 javac -d bin src/models/*
 javac -d bin src/controllers/*
 javac -d bin src/views/*
```
## Executing
```bash
 java -cp bin src.Main
```

## Generating documentation
```bash
javadoc -d javadoc/main src 
javadoc -d javadoc/models src.models
javadoc -d javadoc/controllers src.controllers 
javadoc -d javadoc/views src.views
```