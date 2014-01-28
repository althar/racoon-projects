ssh 144.76.43.138 -l root '/etc/init.d/tomcat7 stop;  rm -rf /var/lib/tomcat7/webapps/ROOT.war;  rm -rf /var/lib/tomcat7/webapps/ROOT; exit;'
cd "C:\Users\AlThar\Documents\Racoon Projects\Racoon\out\artifacts\ROOT\"
scp -r ROOT.war root@144.76.43.138:/var/lib/tomcat7/webapps
ssh 144.76.43.138 -l root 'export JAVA_HOME=/usr/lib/jvm/java-7-oracle; set CATALINA_OPTS="-Xms1024m -Xmx1024m"; export CATALINA_OPTS="-Xms1024m -Xmx1024m"; /etc/init.d/tomcat7 start; exit'
pause