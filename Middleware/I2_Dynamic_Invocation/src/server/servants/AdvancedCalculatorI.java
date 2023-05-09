package server.servants;

import calculator.AdvancedCalculatorGrpc.AdvancedCalculatorImplBase;
import calculator.ComplexArithmeticOpArguments;
import calculator.ComplexArithmeticOpResult;
import calculator.PythagoreanTripleCheckerArguments;
import calculator.PythgoreanTripleCheckerResult;
import io.grpc.stub.StreamObserver;

public class AdvancedCalculatorI extends AdvancedCalculatorImplBase {
    @Override
    public void pythagoreanTriple(PythagoreanTripleCheckerArguments request, StreamObserver<PythgoreanTripleCheckerResult> responseObserver) {
        System.out.println("Pythagorean Triple (a = " + request.getA() + ", b = " + request.getB() + ", c = " + request.getC() + ")");
        boolean result = Math.pow(request.getA(), 2) + Math.pow(request.getB(), 2) == Math.pow(request.getC(), 2);
        PythgoreanTripleCheckerResult operationResult = PythgoreanTripleCheckerResult.newBuilder().setResult(result).build();
        responseObserver.onNext(operationResult);
        responseObserver.onCompleted();
    }

    @Override
    public void complexOperation(ComplexArithmeticOpArguments request, StreamObserver<ComplexArithmeticOpResult> responseObserver) {
        System.out.println("Complex Operation (Type: " + request.getType() + ", Numbers size: " + request.getNumbersCount() + ")");

        if (request.getNumbersCount() == 0) {
            System.out.println("No arguments");
        }

        double result = 0;
        switch (request.getType()) {
            case Sum:
                for (Double number : request.getNumbersList()) result += number;
                break;
            case Avg:
                for (Double number : request.getNumbersList()) result += number;
                result /= request.getNumbersCount();
                break;
            case Min:
                result = request.getNumbersList().get(0);
                for (double number : request.getNumbersList()) result = (number < result) ? number : result;
                break;
            case Max:
                result = request.getNumbersList().get(0);
                for (double number : request.getNumbersList()) result = (number > result) ? number : result;
                break;
            case UNRECOGNIZED:
                break;
        }

        ComplexArithmeticOpResult operationResult = ComplexArithmeticOpResult.newBuilder().setResult(result).build();
        responseObserver.onNext(operationResult);
        responseObserver.onCompleted();
    }
}
