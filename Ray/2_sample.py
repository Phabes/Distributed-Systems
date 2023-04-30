# Excercises 2.1) Create large lists and python dictionaries,
# put them in object store. Write a Ray task to process them.
import logging
import random
import time
import ray

if ray.is_initialized:
    ray.shutdown()
ray.init(logging_level=logging.ERROR)
# ray.init(address="auto", ignore_reinit_error=True, logging_level=logging.ERROR)


def find_all_dividents(factor, x):
    dividents = []
    for e in x:
        if e % factor == 0:
            dividents.append(e)
    return dividents


@ray.remote
def process_data_lists(data: list):
    time.sleep(1)
    dividents = []
    for i in range(2, 4):
        dividents.append(find_all_dividents(i, data))
    return dividents


@ray.remote
def process_data_dicts(data: dict):
    time.sleep(1)
    dividents = []
    values = data.values()
    for i in range(2, 4):
        dividents.append({i: find_all_dividents(i, values)})
    return dividents


n = 5
m = 5
k = 100000

big_lists = [[random.randint(1000, 100000) for _ in range(k)] for _ in range(n)]
big_dicts = [{"key" + str(i + 1): random.randint(1000, 100000) for i in range(k)} for _ in range(m)]

list_refs = [ray.put(big_list) for big_list in big_lists]
dict_refs = [ray.put(big_dict) for big_dict in big_dicts]

list_result_refs = [process_data_lists.remote(list_ref) for list_ref in list_refs]
dict_result_refs = [process_data_dicts.remote(dict_ref) for dict_ref in dict_refs]

start = time.time()
list_results = ray.get(list_result_refs)
# print(list_results)
print("LISTS TIME: ", time.time() - start)
start = time.time()
dict_results = ray.get(dict_result_refs)
# print(dict_results)
print("DICTS TIME: ", time.time() - start)
ray.shutdown()
