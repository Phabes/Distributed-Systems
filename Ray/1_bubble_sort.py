# Excercises 1.1)Try using local bubble sort and remote bubble sort,
# show difference
import os
import time
import logging
import numpy as np
from numpy import loadtxt
import ray

if ray.is_initialized:
    ray.shutdown()
ray.init(logging_level=logging.ERROR)


# ray.init(address="auto", ignore_reinit_error=True, logging_level=logging.ERROR)


def bubble_sort(arr):
    n = len(arr)
    swapped = False
    for i in range(n - 1):
        for j in range(n - i - 1):
            if arr[j] > arr[j + 1]:
                swapped = True
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
        if not swapped:
            return


@ray.remote
def bubble_sort_distributed(arr):
    return bubble_sort(arr)


def read_array(fn: str) -> np.array:
    arr = loadtxt(fn, comments="#", delimiter=",", unpack=False)
    return arr.astype('int')


@ray.remote
def read_array_distributed(fn: str) -> np.array:
    return read_array(fn)


def sort_array(arr, sleep):
    if sleep:
        time.sleep(2)
    arr = arr.flatten()
    bubble_sort(arr)
    return arr


@ray.remote
def sort_array_distributed(arr, sleep):
    return sort_array(arr, sleep)


def print_arrays(arr1, arr2):
    print("start")
    print(arr1)
    print()
    print(arr2)
    print("end\n")


arr1 = read_array(os.path.abspath("data/file_1.txt"))
arr2 = read_array(os.path.abspath("data/file_2.txt"))
start = time.time()
arr1 = sort_array(arr1, True)
arr2 = sort_array(arr2, True)
print(time.time() - start, "LOCAL BUBBLE SORT WITH SLEEP")
# print_arrays(arr1, arr2)

arr1 = read_array(os.path.abspath("data/file_1.txt"))
arr2 = read_array(os.path.abspath("data/file_2.txt"))
start = time.time()
arr1 = sort_array(arr1, False)
arr2 = sort_array(arr2, False)
print(time.time() - start, "LOCAL BUBBLE SORT WITHOUT SLEEP")
# print_arrays(arr1, arr2)

obj_ref_arr1 = read_array_distributed.remote(os.path.abspath("data/file_1.txt"))
obj_ref_arr2 = read_array_distributed.remote(os.path.abspath("data/file_2.txt"))
start = time.time()
arr1 = sort_array_distributed.remote(obj_ref_arr1, True)
arr2 = sort_array_distributed.remote(obj_ref_arr2, True)
arr1 = ray.get(arr1)
arr2 = ray.get(arr2)
print(time.time() - start, "DISTRIBUTED BUBBLE SORT WITH SLEEP")
# print_arrays(arr1, arr2)

obj_ref_arr1 = read_array_distributed.remote(os.path.abspath("data/file_1.txt"))
obj_ref_arr2 = read_array_distributed.remote(os.path.abspath("data/file_2.txt"))
start = time.time()
arr1 = sort_array_distributed.remote(obj_ref_arr1, False)
arr2 = sort_array_distributed.remote(obj_ref_arr2, False)
arr1 = ray.get(arr1)
arr2 = ray.get(arr2)
print(time.time() - start, "DISTRIBUTED BUBBLE SORT WITHOUT SLEEP")
# print_arrays(arr1, arr2)

ray.shutdown()

# Differences:
# Remote bubble sort runs asynchronously and thats why we have 2 seconds plus sort time
# Creating workers  also cost time
# Local bubble sort runs serially and thats why we have 4 second plus sort time
