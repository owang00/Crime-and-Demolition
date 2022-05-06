
java -version
yarn classpath
javac -classpath `yarn classpath` -d . PrecinctMapper.java

javac -classpath `yarn classpath` -d . PrecinctReducer.java

javac -classpath `yarn classpath`:. -d . Precinct.java

jar -cvf precinct.jar *.class

hdfs dfs -rm -r hw8/precinct_output

hadoop jar precinct.jar Precinct /user/aj2985/hw8/input/precincts.csv /user/aj2985/hw8/precinct_output
