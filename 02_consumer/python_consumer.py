from json import loads
from random import random, randint

from kafka import KafkaConsumer

if __name__ == "__main__":
    # Create a consumer connecting to a kafka broker
    consumer = KafkaConsumer(bootstrap_servers=['localhost:9092'],
                             # assign to a specific group
                             group_id="group",
                             # checkpoint the offsets
                             enable_auto_commit=True,
                             # consume from the beginning
                             auto_offset_reset="earliest",
                             value_deserializer=lambda x: loads(x.decode('utf-8')))


    # Subscribe to the correct topic
    consumer.subscribe("sensor-data-partitioned")

    # Print all messages
    for message in consumer:
        print(message)
