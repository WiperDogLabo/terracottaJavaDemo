#!/bin/sh

if [ -e terracotta/terracotta-runtime/deploy/target ];then
	cd terracotta/terracotta-runtime/deploy
    mvn exec:exec -P start-server
else
   echo "Please run setup.sh first"
   exit 1
fi

