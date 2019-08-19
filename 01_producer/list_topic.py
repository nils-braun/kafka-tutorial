from json import dumps
from random import random, randint

from kafka import KafkaConsumer

if __name__ == "__main__":
    # Create a consumer connecting to a kafka broker
    consumer = KafkaConsumer(bootstrap_servers=['localhost:9092'])

    # Print the topics
    print(consumer.topics())
    