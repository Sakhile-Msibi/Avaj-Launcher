#!/bin/sh
javac -d . **/*.java
#javac -d . /src/com/avajLauncher/aircraft/*.java
java -cp . simulator.Simulator $@
