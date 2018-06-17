#!/usr/bin/env bash
sh ./kill.sh;
principal=0
numservers=2
mvn clean install;
for i in `seq 0 $numservers`; do
    gnome-terminal --title="PORT: 808$i" --working-directory=WORK_DIR -x bash -c "cd /home/luis/Documents/projetos/sd_consistency;mvn exec:java -Dexec.args="\""$i 808$i $numservers"\""; bash"
done
