../kafka-topics.sh --zookeeper zookeeper:2181 --create --topic sensor-data-input --partitions 1 --replication-factor 1
../kafka-topics.sh --zookeeper zookeeper:2181 --create --topic sensor-data-input-partitioned --partitions 2 --replication-factor 1
../kafka-topics.sh --zookeeper zookeeper:2181 --create --topic sensor-data-output --partitions 1 --replication-factor 1

