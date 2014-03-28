ssh racoonsoft.ru -l root '/etc/init.d/tomcat7 stop;  rm -rf /var/lib/tomcat7/webapps/ROOT.war;  rm -rf /var/lib/tomcat7/webapps/ROOT; exit;'
cd "C:\Users\AlThar\Documents\Racoon Projects\SuperOwl\out\artifacts\ROOT"
scp -r ROOT.war root@racoonsoft.ru:/var/lib/tomcat7/webapps
ssh racoonsoft.ru -l root 'export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64; export CATALINA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms1536m -Xmx1536m -XX:NewSize=1024m -XX:MaxNewSize=1024m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:+DisableExplicitGC"; export JAVA_OPTS="-Djava.awt.headless=true -Dfile.encoding=UTF-8 -server -Xms1536m -Xmx1536m -XX:NewSize=1024m -XX:MaxNewSize=1024m -XX:PermSize=1024m -XX:MaxPermSize=1024m -XX:+DisableExplicitGC"; /etc/init.d/tomcat7 start; exit'
pause