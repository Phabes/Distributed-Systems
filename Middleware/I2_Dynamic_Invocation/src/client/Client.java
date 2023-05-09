/*
 * Copyright 2015, Google Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *    * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *
 *    * Neither the name of Google Inc. nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package client;

import calculator.AdvancedCalculatorGrpc;
import calculator.ComplexArithmeticOpArguments;
import calculator.ComplexArithmeticOpResult;
import calculator.OperationType;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;


import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;


public class Client
{
    private final AdvancedCalculatorGrpc.AdvancedCalculatorBlockingStub advCalcBlockingStub;
    private final ManagedChannel channel;



    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public Client(String remoteHost, int remotePort)
    {
        channel = ManagedChannelBuilder.forAddress(remoteHost, remotePort)
                .usePlaintext() // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid needing certificates.
                .build();

//        calcBlockingStub = CalculatorGrpc.newBlockingStub(channel);
//        calcNonBlockingStub = CalculatorGrpc.newStub(channel);
//        calcFutureStub = CalculatorGrpc.newFutureStub(channel);

        advCalcBlockingStub = AdvancedCalculatorGrpc.newBlockingStub(channel);

//        streamTesterBlockingStub = StreamTesterGrpc.newBlockingStub(channel);
//        streamTesterNonBlockingStub = StreamTesterGrpc.newStub(channel); //Blocking stubs do not support client-streaming or bidirectional-streaming RPCs.

    }



    public static void main(String[] args) throws Exception
    {
        Client client = new Client("localhost", 50050);
        client.test();
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public void test() throws InterruptedException
    {
        String line = null;
        java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));

        do
        {
            try
            {
                System.out.print("==> ");
                System.out.flush();
                line = in.readLine();
                switch (line) {
                    case "complex-sum": {
                        ComplexArithmeticOpArguments request = ComplexArithmeticOpArguments.newBuilder()
                                .setType(OperationType.Sum).addAllNumbers(Arrays.asList(4.0, 5.0, 3.1415926))
                                .build();
                        ComplexArithmeticOpResult result = advCalcBlockingStub.complexOperation(request);
                        System.out.println(result.getResult());
                        break;
                    }
                    default:
                        System.out.println("???");
                        break;
                }
            }
            catch (java.io.IOException ex)
            {
                System.err.println(ex);
            }
        }
        while (!line.equals("x"));

        shutdown();
    }
}


//class PrimeNumbersFinderExecutor extends Thread
//{
//    StreamTesterBlockingStub streamTesterBlockingStub;
//
//    PrimeNumbersFinderExecutor(StreamTesterBlockingStub streamTesterBlockingStub)
//    {
//        this.streamTesterBlockingStub = streamTesterBlockingStub;
//    }
//
//    public void run()
//    {
//        Task request = Task.newBuilder().setMax(28).build();
//
//        Iterator<Number> numbers;
//        try {
//            System.out.println("Calling GeneratePrimeNumbers(" + request.getMax() + ")...");
//            numbers = streamTesterBlockingStub.generatePrimeNumbers(request); //rpc GeneratePrimeNumbers(Task) returns (stream Number) {}
//            while(numbers.hasNext())
//            {
//                //wypisuje się z odstępami czasowymi, więc strumieniowanie DZIAŁA
//                Number num = numbers.next();
//                System.out.println("Service returned: " + num.getValue());
//            }
//            System.out.println("GeneratePrimeNumbers completed");
//        } catch (StatusRuntimeException ex) {
//            System.err.println("RPC failed: " + ex.getStatus());
//            return;
//        }
//    }
//}
//
//
//class PrimeCounterExecutor extends Thread
//{
//    StreamTesterStub streamTesterNonBlockingStub;
//
//    PrimeCounterExecutor(StreamTesterStub streamTesterNonBlockingStub)
//    {
//        this.streamTesterNonBlockingStub = streamTesterNonBlockingStub;
//    }
//
//    public void run()
//    {
//        StreamObserver<Report> responseObserver = new StreamObserver<Report>()
//        {
//            int count = -1;
//            @Override public void onNext(Report result)	{ //wołane tylko raz - na końcu wywołania
//                count = result.getCount();
//            }
//            @Override public void onError(Throwable t) {
//                System.out.println("RPC ERROR");
//            }
//            @Override public void onCompleted()	{
//                System.out.println("Result received: found " + count + " prime numbers");
//            }
//        };
//
//        StreamObserver<Number> requestObserver = streamTesterNonBlockingStub.countPrimeNumbers(responseObserver); //rpc CountPrimeNumbers(stream Number) returns (Report) {}
//        try {
//            for (int i = 0; i < 100; ++i) {
//                if(isPrime(i)) {
//                    Number number = Number.newBuilder().setValue(i).build();
//                    System.out.println("Streaming data to the service... (" + number.getValue() + ")");
//                    requestObserver.onNext(number);
//                }
//            }
//        } catch (RuntimeException e) {
//            // Cancel RPC
//            requestObserver.onError(e);
//            throw e;
//        }
//        // Mark the end of requests
//        requestObserver.onCompleted();
//    }
//
//
//
//    private boolean isPrime(int val)
//    {
//        if(val % 2 == 0) return false; //oczywiście to nieprawda ;)
//        try { Thread.sleep(1000); } catch(java.lang.InterruptedException ex) { }
//        return true; //oczywiście to nieprawda ;)
//    }
//
//}
