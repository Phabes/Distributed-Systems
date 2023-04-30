# excercise 3
# 3.3 Take a look on implement parralel Pi computation
# based on https://docs.ray.io/en/master/ray-core/examples/highly_parallel.html
#
# Implement calculating pi as a combination of actor (which keeps the
# state of the progress of calculating pi as it approaches its final value)
# and a task (which computes candidates for pi)

import logging
import random
import time

import ray

if ray.is_initialized:
    ray.shutdown()
ray.init(logging_level=logging.ERROR)
# ray.init(address="auto", ignore_reinit_error=True, logging_level=logging.ERROR)

print(f"{'-' * 10} PI estimate {'-' * 10}")


@ray.remote
class PiActor:
    def __init__(self):
        self.values = []

    def add(self, fraction):
        self.values.append(fraction)

    def get_pi(self):
        return sum(self.values) / len(self.values)


@ray.remote
def pi4_sample(sample_count):
    in_count = 0
    for i in range(sample_count):
        x = random.random()
        y = random.random()
        if x * x + y * y <= 1:
            in_count += 1
    return actor.add.remote((in_count / sample_count) * 4)


actor = PiActor.remote()
SAMPLE_COUNT = 1000 * 1000
FULL_SAMPLE_COUNT = 1000 * 1000 * 100  # billion samples!
BATCHES = int(FULL_SAMPLE_COUNT / SAMPLE_COUNT)
print(f"Doing {BATCHES} batches")

sleep_time = 1
start = time.time()
results = [pi4_sample.remote(SAMPLE_COUNT) for _ in range(BATCHES)]

ready, remaining = ray.wait(results, num_returns=len(results), timeout=sleep_time)
while len(remaining) > 0:
    print(f"Total progress: {(len(ready) / BATCHES) * 100:.2f}%")
    ready, remaining = ray.wait(results, num_returns=len(results), timeout=sleep_time)
    # time.sleep(sleep_time)
print(f"Total progress: {(len(ready) / BATCHES) * 100:.2f}%")

print(f"Pi estimate: {ray.get(actor.get_pi.remote())}")
print(f"Time: {time.time() - start}")

ray.shutdown()
