# kata04
Solutions and Answers to the Kata04 Data Munging - Coding Challenge

There are a three projects in this repo: kata04-weather, kata04-football, 
kata04-combined.

All three can be compiled by running:

```
mvn package
```

inside each directory. This 
will create .jar files inside the target directory which can be run by calling
java -jar kata04-weather.jar (for example).

All four main methods read from standard in, so the best way to run the
programs are from the command line and redirect stdin to the .dat files. Here
are examples for unix-like systems:

/kata04-weather

```
java -jar ${path_to}/kata04-weather.jar < ${path_to}/weather.dat
```

/kata04-football

```
java -jar ${path_to}/kata04-football.jar < ${path_to}/football.dat
```

/kata04-combined

This Project has two executables and maven will create both and they are names 
the same as the above two (except they will be located in the target directory 
of this project).
