package server.servants;

import generator.*;
import generator.NumbersGeneratorGrpc.NumbersGeneratorImplBase;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class GeneratorI extends NumbersGeneratorImplBase {
    @Override
    public void generatePrimeNumbers(GeneratorArguments request, StreamObserver<GeneratorResults> responseObserver) {
        GenerationType type = request.getType();
        List<Integer> numbers = new ArrayList<>();
        if (type == GenerationType.To) {
            System.out.println("Generating prime numbers max to " + request.getCount());
            for (int i = 0; i < request.getCount(); i++) {
                if (isPrime(i))
                    numbers.add(i);
            }
        } else if (type == GenerationType.Amount) {
            System.out.println("Generating " + request.getCount() + " prime numbers");
            int number = 0;
            while (numbers.size() != request.getCount()) {
                if (isPrime(number))
                    numbers.add(number);
                number++;
            }
        }
        GeneratorResults result = GeneratorResults.newBuilder().addAllNumbers(numbers).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void generateFibonacciNumbers(GeneratorArguments request, StreamObserver<GeneratorResults> responseObserver) {
        GenerationType type = request.getType();
        List<Integer> numbers = new ArrayList<>();
        if (type == GenerationType.To) {
            System.out.println("Generating Fibonacci numbers max to " + request.getCount());
            int a = 1;
            int b = 1;
            numbers.add(a);
            while (b < request.getCount()) {
                numbers.add(b);
                int tmp = b;
                b = a + b;
                a = tmp;
            }
        } else if (type == GenerationType.Amount) {
            System.out.println("Generating " + request.getCount() + " Fibonacci numbers");
            int a = 1;
            int b = 1;
            numbers.add(a);
            while (numbers.size() < request.getCount()) {
                numbers.add(b);
                int tmp = b;
                b = a + b;
                a = tmp;
            }
        }
        GeneratorResults result = GeneratorResults.newBuilder().addAllNumbers(numbers).build();
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    private boolean isPrime(int number) {
        if (number <= 1)
            return false;
        for (int i = 2; i < Math.sqrt(number); i++)
            if (number % i == 0)
                return false;
        return true;
    }
}
