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
        mvn exec:java -P ttest11 //~ This will schedule job1 and job2 with interval 10s
        mvn exec:java -P ttest11 //~ It will connect and stand-by in cluster-system
        
        //~ Now we can see. First, Terminal_1 will run job1, job2
        //~ When Terminal_2 started-up, terminal 2 will share the jobs. Ter1 runs job1, Ter2 runs job2 => Load-balancing
        //~ Try to shutdown 1 terminal, remain terminal will take control of both job1 and job2 => Failover
        //~ Feel free to start-up, shutdown each one of Terminals to see how cluster works 

    test2, not defined yet...

