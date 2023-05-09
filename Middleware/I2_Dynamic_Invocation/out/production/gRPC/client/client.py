import grpc
import grpc_requests
from google.protobuf.descriptor_pool import DescriptorPool
from grpc_reflection.v1alpha.proto_reflection_descriptor_database import ProtoReflectionDescriptorDatabase


def list_available_services(list_methods):
    services = list(filter(lambda s: s != "grpc.reflection.v1alpha.ServerReflection", reflection_db.get_services()))
    for service in services:
        name = service.split(".")[-1]
        print(f"Service: {name}")
        if list_methods:
            list_available_methods(service)
    return services


def list_available_methods(service):
    service_descriptor = descriptor_pool.FindServiceByName(service)
    print("\tMethods:")
    for j, method in enumerate(service_descriptor.methods):
        print(f"\t\t({j})------------------------")
        print(f"\t\tMethod name: {method.name}")
        print(f"\t\tInput message type: {method.input_type.full_name}")
        print(f"\t\tOutput message type: {method.output_type.full_name}")


def sample_requests():
    calculator_operations()
    generator_operations()


def calculator_operations():
    list_operations()
    pythagorean_triple_operations()


def generator_operations():
    prime_generator_operations()
    fibonacci_generator_operations()


def list_operations():
    numbers = [2.3, 5.4, 1.5, 3.5, 7.8]
    operations = ["Sum", "Avg", "Min", "Max"]

    for operation in operations:
        result = client.request("calculator.AdvancedCalculator", "ComplexOperation",
                                {"type": operation, "numbers": numbers})
        print(f"Operation: {operation}, Result: {result}")


def pythagorean_triple_operations():
    values = [
        [3, 4, 5],
        [1, 2, 3]
    ]

    for a, b, c in values:
        result = client.request("calculator.AdvancedCalculator", "PythagoreanTriple",
                                {"a": a, "b": b, "c": c})
        print(f"Operation: Pythagorean Triple, Result: {result}")


def prime_generator_operations():
    types = ["To", "Amount"]
    amount = 15

    for operationType in types:
        result = client.request("generator.NumbersGenerator", "GeneratePrimeNumbers",
                                {"type": operationType, "count": amount})
        print(f"Prime numbers: {result}")


def fibonacci_generator_operations():
    types = ["To", "Amount"]
    amount = 15

    for operationType in types:
        result = client.request("generator.NumbersGenerator", "GenerateFibonacciNumbers",
                                {"type": operationType, "count": amount})
        print(f"Fibonacci numbers: {result}")


if __name__ == "__main__":
    host = "localhost"
    port = 50050
    server_address = f"{host}:{port}"
    client = grpc_requests.Client.get_by_endpoint(server_address)
    channel = grpc.insecure_channel(server_address)
    reflection_db = ProtoReflectionDescriptorDatabase(channel)
    descriptor_pool = DescriptorPool(reflection_db)

    available_calculator_commands = ["Sum", "Avg", "Min", "Max"]
    available_generator_commands = ["To", "Amount"]

    while True:
        line = input("> ")
        command_args = line.split(" ")
        command, rest = command_args[0], command_args[1:]
        if line == "stop":
            break
        elif line == "sample":
            sample_requests()
        elif line == "list":
            list_available_services(False)
        elif line == "list_all":
            list_available_services(True)
        elif command == "triple":
            result = client.request("calculator.AdvancedCalculator", "PythagoreanTriple",
                                    {"a": rest[0], "b": rest[1], "c": rest[2]})
            print(f"Operation: Pythagorean Triple Checker, Result: {result}")
        elif command in available_calculator_commands:
            result = client.request("calculator.AdvancedCalculator", "ComplexOperation",
                                    {"type": command, "numbers": rest})
            print(f"Operation: {command}, Result: {result}")
        elif command == "prime":
            operation_type = rest[0]
            if operation_type in available_generator_commands:
                count = rest[1]
                result = client.request("generator.NumbersGenerator", "GeneratePrimeNumbers",
                                        {"type": operation_type, "count": count})
                print(f"Prime numbers: {result}")
        elif command == "fib":
            operation_type = rest[0]
            if operation_type in available_generator_commands:
                count = rest[1]
                result = client.request("generator.NumbersGenerator", "GenerateFibonacciNumbers",
                                        {"type": operation_type, "count": count})
                print(f"Fibonacci numbers: {result}")
        else:
            print("Invalid command")
