ssh businesswinner.ru -l root '/etc/init.d/tomcat7 stop;  rm -rf /var/lib/tomcat7/webapps/ROOT.war;  rm -rf /var/lib/tomcat7/webapps/ROOT; exit;'
cd "C:\Users\AlThar\Documents\Racoon Projects\Racoon\out\artifacts\ROOT\"
scp -r ROOT.war root@businesswinner.ru:/var/lib/tomcat7/webapps
ssh businesswinner.ru -l root 'export JAVA_HOME=/usr/lib/jvm/java-7-oracle; export CATALINA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms600m -Xmx600m -XX:NewSize=500m -XX:MaxNewSize=500m -XX:PermSize=500m -XX:MaxPermSize=500m -XX:+DisableExplicitGC"; export JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms500m -Xmx500m -XX:NewSize=500m -XX:MaxNewSize=500m -XX:PermSize=500m -XX:MaxPermSize=500m -XX:+DisableExplicitGC"; /etc/init.d/tomcat7 start; exit'
pause