echo 'starting......'
echo $(dirname $0) 
for city in * ; do 
  for date in "$city"/* ; do
	 awk 'FNR==1 && NR!=1{next;}{print}' "$date"/percentils/percentils*.csv >> "$date"-percentils.csv
	 awk 'FNR==1 && NR!=1{next;}{print}' "$date"/raw/raw*.csv >> "$date"-raw.csv
	 awk 'FNR==1 && NR!=1{next;}{print}' "$date"/statistics/statistics*.csv >> "$date"-statistics.csv
  done
done

for city in * ; do 
	rm -r "$city"/*/
done

