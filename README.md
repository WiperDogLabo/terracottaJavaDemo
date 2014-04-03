terracottaJavaDemo
==================

Quartz Scheduler + Terracotta Job Store demo
(bootstrap)  

1. prepare dependencies by using 'setup_dependency.sh'
2. Start terracotta server(using a dedicated terminal):  
        ./start_server.sh
The terracotta server configurations are written in the file ./tc-config.xml .
3. execute tests:  
How to execute:    
(Quartz sample)  

        mvn exec:java -P test1
        mvn exec:java -P test2
        mvn exec:java -P test3
        mvn exec:java -P test4
        mvn exec:java -P test5

(Terracotta + Quartz sample)  
    test1, use 2 terminal, invoke following 2 commands respectively.  
        mvn exec:java -P ttest11
        mvn exec:java -P ttest12

    test2, not defined yet...

