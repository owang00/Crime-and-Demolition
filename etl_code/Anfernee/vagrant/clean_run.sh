
java -version
yarn classpath
javac -classpath `yarn classpath` -d . CleanMapper.java

javac -classpath `yarn classpath` -d . CleanReducer.java

javac -classpath `yarn classpath`:. -d . Clean.java

jar -cvf clean.jar *.class

hdfs dfs -rm -r hw7/clean_output

hadoop jar clean.jar Clean /user/aj2985/hw7/input/pluto_21v4.csv /user/aj2985/hw7/clean_output
