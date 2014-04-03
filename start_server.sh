#!/bin/sh
# You need to install "xmlstarlet" for runtime modification of pom.xml file.

TCCONFIGPATH=`pwd`/tc-config.xml

if [ -e terracotta/terracotta-runtime/deploy/target ];then
	cd terracotta/terracotta-runtime/deploy
	##############################################################
	# modify pom.xml to add additional argument to server startup.
	if ! grep tcconfigpath pom.xml > /dev/null 2>&1;then
		patch -p0 --backup --version-control=numbered <<'EOF_PATCH'
--- pom.xml
+++ pom.xml
@@ -188,6 +188,7 @@
                 <argument>-Dcom.tc.management.war=${basedir}/../management-agent/management-tsa-war/target/management-tsa-war-${project.version}.war</argument>
                 <argument>-Dcom.sun.management.jmxremote</argument>
                 <argument>-Dsun.rmi.dgc.server.gcInterval=31536000</argument>
+                <argument>-Dtc.config=${tcconfigpath}</argument>
                 <argument>-classpath</argument>
                 <classpath/>
                 <argument>com.tc.server.TCServerMain</argument>
EOF_PATCH
	fi
	##############################################################
	# execute terracotta server
    mvn exec:exec -P start-server -Dtcconfigpath=$TCCONFIGPATH
else
   echo "Please run setup.sh first"
   exit 1
fi

