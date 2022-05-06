
# To get vacant buildings data
wget -O pluto.csv https://www1.nyc.gov/assets/planning/download/zip/data-maps/open-data/nyc_pluto_21v4_csv.zip

hdfs dfs -put pluto.csv analysis/data/