#mvn compile
java -classpath "$PWD/target/classes:$HOME/.m2/repository/com/googlecode/lanterna/lanterna/3.0.1/lanterna-3.0.1.jar" uk.ac.warwick.dcs.chess.Chess $@ 2> chess.err
