#!/bin/bash
exec docker exec -it kafka /usr/bin/kafka-console-producer $@
