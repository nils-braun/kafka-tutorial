from json import dumps
from random import random, randint
from time import sleep
from kafka import KafkaProducer

if __name__ == "__main__":
    # Create a producer connecting to a kafka broker
    producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                             value_serializer=lambda x: dumps(x).encode('utf-8'))

    while True:
        # Create some random data
        data = {"sensorData1": random(), "sensorData2": random()}

        # Choose a random identity
        random_id = randint(0, 6)

        # Send the message
        message = producer.send('sensor-data-input', value=data,
                                key=str(random_id).encode())

        # Wait for it to be sent
        message.get()

        # Sleep some time
        sleep(1)
