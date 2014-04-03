#!/bin/sh

SKIPTEST=-DskipTests

#
# prepare source tree of terracotta opensource.
if [ ! -d terracotta ]; then
   mkdir terracotta
fi

cd terracotta
if [ ! -d quartz ];then
    git clone https://github.com/TerracottaUnofficialRepositories/quartz.git
fi
if [ ! -d ehcache ]; then
    git clone https://github.com/TerracottaUnofficialRepositories/ehcache.git
fi
if [ ! -d terracotta-runtime ];then
    git clone https://github.com/TerracottaUnofficialRepositories/terracotta-runtime.git
    (cd terracotta-runtime; git checkout 4.1.1-buildable)
fi


# do build
which mvn > /dev/null 2>&1 || (echo "No mvn installed in the path";exit 1)

(cd quartz;mvn install $SKIPTEST)
(cd ehcache;mvn install $SKIPTEST)
(cd terracotta-runtime;mvn install $SKIPTEST)


