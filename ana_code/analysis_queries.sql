# Preparing to merge

# Adding the header
hdfs dfs -get analysis/prezip.csv
sed -i '1s/^/precinct,zip\n/' prezip.csv
hdfs dfs -put -f prezip.csv analysis/prezip.csv


# Create table for precinct with Hive
use aj2985;
create table precinct (precinct_no INT, zipcode INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;
load data inpath 'hdfs://horton.hpc.nyu.edu:8020/user/aj2985/analysis/prezip.csv' overwrite into table precinct;

# Prep vacant dataset
hdfs dfs -get hw7/clean_output/part-r-00000
mv part-r-00000 vacant.csv
sed -i '1s/^/borough,zip,vacant_type\n/' vacant.csv
hdfs dfs -put vacant.csv analysis/


# Creating table for vacant build data
use aj2985; 
create table vacant (borough VARCHAR(2), zipcode INT, bldgCode VARCHAR(2)) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;


# Creating table for permits
create table permits (borough VARCHAR(14), perm_type VARCHAR(3), zipcode INT, perm_status VARCHAR(11), exp_date DATE, start_date DATE) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;

load data inpath 'hdfs://horton.hpc.nyu.edu:8020/user/aj2985/analysis/permits_clean.csv' overwrite into table permits;


# Creating table for arrests data
reate table arrests (arrest_date DATE, borough STRING, precinct INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' STORED AS TEXTFILE;


# Join arrests with precinct for zipcode
CREATE TABLE arrests_zip AS SELECT arrests.arrest_date, arrests.borough, precinct.zipcode FROM arrests JOIN precinct ON (precinct.precinct_no = arrests.precinct);


# Finding zip codes to focus on
CREATE TABLE vacant_zip AS SELECT zipcode, count(1) AS vacant_count from vacant group by zipcode;
SELECT * from vacant_zip order by vacant_count desc limit 20; # top 20 crime zip codes


# Arrests per month by zip code
CREATE TABLE arrest_per_month_by_zip AS select date_format(arrest_date, 'MM') as Month, count(1), zipcode from arrests_zip group by date_format(arrest_date, 'MM'), zipcode;


# Merge arrests and vacant buildings on zipcode
CREATE TABLE vacant_arrests AS select arrests_per_zip.zipcode, arrests_per_zip.arrest_count, vacant_zip.vacant_count from arrests_per_zip join vacant_zip where (arrests_per_zip.zipcode = vacant_zip.zipcode);
