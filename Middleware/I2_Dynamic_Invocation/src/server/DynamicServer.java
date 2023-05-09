package server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import server.servants.AdvancedCalculatorI;
import server.servants.GeneratorI;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;

public class DynamicServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        String address = "localhost";
        int port = 50050;
        SocketAddress socket = new InetSocketAddress(InetAddress.getByName(address), port);
        Server server = NettyServerBuilder.forAddress(socket).executor(Executors.newFixedThreadPool(10))
                .addService(ProtoReflectionService.newInstance())
                .addService(new GeneratorI())
                .addService(new AdvancedCalculatorI())
                .build()
                .start();
//        Server server = ServerBuilder
//                .forPort(port)
//                .addService(ProtoReflectionService.newInstance())
//                .addService(new GeneratorI())
//                .addService(new AdvancedCalculatorI())
//                .build()
//                .start();

        System.out.println("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            server.shutdown();
            System.err.println("*** server shut down");
        }));

        server.awaitTermination();
    }
}
