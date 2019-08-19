from json import dumps
from random import random, randint

from kafka import KafkaProducer

if __name__ == "__main__":
    # Create a producer connecting to a kafka broker
    producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                             value_serializer=lambda x: dumps(x).encode('utf-8'))

    # Choose a random identity
    random_id = randint(0, 6)

    # Create some random data
    data = {"sensorData1": random(), "sensorData2": random(), "id": random_id}

    # Send the message
    message = producer.send('sensor-data-partitioned', value=data,
                            key=str(random_id).encode())

    # Wait for it to be sent
    message.get()
