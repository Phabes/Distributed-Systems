# excercise 3
# 3.2 Modify method invoke to return a random int value between [5, 25]


import logging
import random
import time

import ray

if ray.is_initialized:
    ray.shutdown()
ray.init(logging_level=logging.ERROR)
# ray.init(address="auto", ignore_reinit_error=True, logging_level=logging.ERROR)

CALLERS = ["A", "B", "C"]


@ray.remote
class MethodStateCounter:
    def __init__(self):
        self.invokers = {"A": 0, "B": 0, "C": 0}
        self.invokers_values = {"A": [], "B": [], "C": []}

    def invoke(self, name):
        # pretend to do some work here
        time.sleep(0.5)
        # update times invoked
        self.invokers[name] += 1
        # add computed value
        value = random.randint(5, 25)
        self.invokers_values[name].append(value)
        return value

    def get_invoker_state(self, name):
        # return the state of the named invoker
        return self.invokers[name]

    def get_all_invoker_state(self):
        # reeturn the state of all invokers
        return self.invokers

    def get_invoker_values(self, name):
        # return the count of the named invoker
        return self.invokers_values[name]

    def get_all_invoker_values(self):
        # return the count of all invokers
        return self.invokers_values


worker_invoker = MethodStateCounter.remote()
print(worker_invoker)

for _ in range(10):
    name = random.choice(CALLERS)
    worker_invoker.invoke.remote(name)

for _ in range(5):
    random_name_invoker = random.choice(CALLERS)
    times_invoked = ray.get(worker_invoker.invoke.remote(random_name_invoker))
    print(f"Named caller: {random_name_invoker} called {times_invoked}")

# Fetch the count of all callers
print(f"Count: {ray.get(worker_invoker.get_all_invoker_state.remote())}")
# Fetch the values of all callers
print(f"Values: {ray.get(worker_invoker.get_all_invoker_values.remote())}")
# Fetch the count and values computed by each caller
for caller in CALLERS:
    print(f"{caller} was called: {ray.get(worker_invoker.get_invoker_state.remote(caller))}")
    print(f"{caller} values computed: {ray.get(worker_invoker.get_invoker_values.remote(caller))}")

ray.shutdown()
