package generator;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: generator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class NumbersGeneratorGrpc {

  private NumbersGeneratorGrpc() {}

  public static final String SERVICE_NAME = "generator.NumbersGenerator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generator.GeneratorArguments,
      generator.GeneratorResults> getGeneratePrimeNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GeneratePrimeNumbers",
      requestType = generator.GeneratorArguments.class,
      responseType = generator.GeneratorResults.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generator.GeneratorArguments,
      generator.GeneratorResults> getGeneratePrimeNumbersMethod() {
    io.grpc.MethodDescriptor<generator.GeneratorArguments, generator.GeneratorResults> getGeneratePrimeNumbersMethod;
    if ((getGeneratePrimeNumbersMethod = NumbersGeneratorGrpc.getGeneratePrimeNumbersMethod) == null) {
      synchronized (NumbersGeneratorGrpc.class) {
        if ((getGeneratePrimeNumbersMethod = NumbersGeneratorGrpc.getGeneratePrimeNumbersMethod) == null) {
          NumbersGeneratorGrpc.getGeneratePrimeNumbersMethod = getGeneratePrimeNumbersMethod =
              io.grpc.MethodDescriptor.<generator.GeneratorArguments, generator.GeneratorResults>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GeneratePrimeNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generator.GeneratorArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generator.GeneratorResults.getDefaultInstance()))
              .setSchemaDescriptor(new NumbersGeneratorMethodDescriptorSupplier("GeneratePrimeNumbers"))
              .build();
        }
      }
    }
    return getGeneratePrimeNumbersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generator.GeneratorArguments,
      generator.GeneratorResults> getGenerateFibonacciNumbersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GenerateFibonacciNumbers",
      requestType = generator.GeneratorArguments.class,
      responseType = generator.GeneratorResults.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generator.GeneratorArguments,
      generator.GeneratorResults> getGenerateFibonacciNumbersMethod() {
    io.grpc.MethodDescriptor<generator.GeneratorArguments, generator.GeneratorResults> getGenerateFibonacciNumbersMethod;
    if ((getGenerateFibonacciNumbersMethod = NumbersGeneratorGrpc.getGenerateFibonacciNumbersMethod) == null) {
      synchronized (NumbersGeneratorGrpc.class) {
        if ((getGenerateFibonacciNumbersMethod = NumbersGeneratorGrpc.getGenerateFibonacciNumbersMethod) == null) {
          NumbersGeneratorGrpc.getGenerateFibonacciNumbersMethod = getGenerateFibonacciNumbersMethod =
              io.grpc.MethodDescriptor.<generator.GeneratorArguments, generator.GeneratorResults>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GenerateFibonacciNumbers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generator.GeneratorArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generator.GeneratorResults.getDefaultInstance()))
              .setSchemaDescriptor(new NumbersGeneratorMethodDescriptorSupplier("GenerateFibonacciNumbers"))
              .build();
        }
      }
    }
    return getGenerateFibonacciNumbersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NumbersGeneratorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NumbersGeneratorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NumbersGeneratorStub>() {
        @java.lang.Override
        public NumbersGeneratorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NumbersGeneratorStub(channel, callOptions);
        }
      };
    return NumbersGeneratorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NumbersGeneratorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NumbersGeneratorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NumbersGeneratorBlockingStub>() {
        @java.lang.Override
        public NumbersGeneratorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NumbersGeneratorBlockingStub(channel, callOptions);
        }
      };
    return NumbersGeneratorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NumbersGeneratorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NumbersGeneratorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NumbersGeneratorFutureStub>() {
        @java.lang.Override
        public NumbersGeneratorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NumbersGeneratorFutureStub(channel, callOptions);
        }
      };
    return NumbersGeneratorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void generatePrimeNumbers(generator.GeneratorArguments request,
        io.grpc.stub.StreamObserver<generator.GeneratorResults> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGeneratePrimeNumbersMethod(), responseObserver);
    }

    /**
     */
    default void generateFibonacciNumbers(generator.GeneratorArguments request,
        io.grpc.stub.StreamObserver<generator.GeneratorResults> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGenerateFibonacciNumbersMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service NumbersGenerator.
   */
  public static abstract class NumbersGeneratorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return NumbersGeneratorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service NumbersGenerator.
   */
  public static final class NumbersGeneratorStub
      extends io.grpc.stub.AbstractAsyncStub<NumbersGeneratorStub> {
    private NumbersGeneratorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumbersGeneratorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NumbersGeneratorStub(channel, callOptions);
    }

    /**
     */
    public void generatePrimeNumbers(generator.GeneratorArguments request,
        io.grpc.stub.StreamObserver<generator.GeneratorResults> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGeneratePrimeNumbersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void generateFibonacciNumbers(generator.GeneratorArguments request,
        io.grpc.stub.StreamObserver<generator.GeneratorResults> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGenerateFibonacciNumbersMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service NumbersGenerator.
   */
  public static final class NumbersGeneratorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<NumbersGeneratorBlockingStub> {
    private NumbersGeneratorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumbersGeneratorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NumbersGeneratorBlockingStub(channel, callOptions);
    }

    /**
     */
    public generator.GeneratorResults generatePrimeNumbers(generator.GeneratorArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGeneratePrimeNumbersMethod(), getCallOptions(), request);
    }

    /**
     */
    public generator.GeneratorResults generateFibonacciNumbers(generator.GeneratorArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGenerateFibonacciNumbersMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service NumbersGenerator.
   */
  public static final class NumbersGeneratorFutureStub
      extends io.grpc.stub.AbstractFutureStub<NumbersGeneratorFutureStub> {
    private NumbersGeneratorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NumbersGeneratorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NumbersGeneratorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generator.GeneratorResults> generatePrimeNumbers(
        generator.GeneratorArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGeneratePrimeNumbersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generator.GeneratorResults> generateFibonacciNumbers(
        generator.GeneratorArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGenerateFibonacciNumbersMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GENERATE_PRIME_NUMBERS = 0;
  private static final int METHODID_GENERATE_FIBONACCI_NUMBERS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GENERATE_PRIME_NUMBERS:
          serviceImpl.generatePrimeNumbers((generator.GeneratorArguments) request,
              (io.grpc.stub.StreamObserver<generator.GeneratorResults>) responseObserver);
          break;
        case METHODID_GENERATE_FIBONACCI_NUMBERS:
          serviceImpl.generateFibonacciNumbers((generator.GeneratorArguments) request,
              (io.grpc.stub.StreamObserver<generator.GeneratorResults>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getGeneratePrimeNumbersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generator.GeneratorArguments,
              generator.GeneratorResults>(
                service, METHODID_GENERATE_PRIME_NUMBERS)))
        .addMethod(
          getGenerateFibonacciNumbersMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generator.GeneratorArguments,
              generator.GeneratorResults>(
                service, METHODID_GENERATE_FIBONACCI_NUMBERS)))
        .build();
  }

  private static abstract class NumbersGeneratorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NumbersGeneratorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generator.GeneratorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NumbersGenerator");
    }
  }

  private static final class NumbersGeneratorFileDescriptorSupplier
      extends NumbersGeneratorBaseDescriptorSupplier {
    NumbersGeneratorFileDescriptorSupplier() {}
  }

  private static final class NumbersGeneratorMethodDescriptorSupplier
      extends NumbersGeneratorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NumbersGeneratorMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (NumbersGeneratorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NumbersGeneratorFileDescriptorSupplier())
              .addMethod(getGeneratePrimeNumbersMethod())
              .addMethod(getGenerateFibonacciNumbersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
