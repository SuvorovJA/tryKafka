# tryKafka
### run Zookeeper
```shell script
$ cd /d/bin/apache-zookeeper-3.5.8-bin/
$ cat conf/zoo.cfg | grep Dir
dataDir=/d/specialist/zookeeper
$ ./bin/zkEnv.sh
$ ./bin/zkServer.sh start
ZooKeeper JMX enabled by default
Using config: D:\bin\apache-zookeeper-3.5.8-bin\conf\zoo.cfg
Starting zookeeper ... STARTED
```
### run Kafka
```shell script
$ cd /d/bin/kafka_2.13-2.5.0/
$ cat ./config/server.properties | grep dirs
log.dirs=/d/specialist/kafka-logs
$ ./bin/kafka-server-start.sh ./config/server.properties
```
### create topic
```shell script
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic mymessages
```
### build
```shell script
$ cd tryKafka
$ mvn clean package
```
### run receiver
```shell script
$ java -jar target/sender-jar-with-dependencies.jar
```
### run sender
```shell script
$ java -jar target/receiver-jar-with-dependencies.jar
```
press button "Start Send"

