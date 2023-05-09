package calculator;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: calculator.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AdvancedCalculatorGrpc {

  private AdvancedCalculatorGrpc() {}

  public static final String SERVICE_NAME = "calculator.AdvancedCalculator";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<calculator.PythagoreanTripleCheckerArguments,
      calculator.PythgoreanTripleCheckerResult> getPythagoreanTripleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PythagoreanTriple",
      requestType = calculator.PythagoreanTripleCheckerArguments.class,
      responseType = calculator.PythgoreanTripleCheckerResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<calculator.PythagoreanTripleCheckerArguments,
      calculator.PythgoreanTripleCheckerResult> getPythagoreanTripleMethod() {
    io.grpc.MethodDescriptor<calculator.PythagoreanTripleCheckerArguments, calculator.PythgoreanTripleCheckerResult> getPythagoreanTripleMethod;
    if ((getPythagoreanTripleMethod = AdvancedCalculatorGrpc.getPythagoreanTripleMethod) == null) {
      synchronized (AdvancedCalculatorGrpc.class) {
        if ((getPythagoreanTripleMethod = AdvancedCalculatorGrpc.getPythagoreanTripleMethod) == null) {
          AdvancedCalculatorGrpc.getPythagoreanTripleMethod = getPythagoreanTripleMethod =
              io.grpc.MethodDescriptor.<calculator.PythagoreanTripleCheckerArguments, calculator.PythgoreanTripleCheckerResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PythagoreanTriple"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  calculator.PythagoreanTripleCheckerArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  calculator.PythgoreanTripleCheckerResult.getDefaultInstance()))
              .setSchemaDescriptor(new AdvancedCalculatorMethodDescriptorSupplier("PythagoreanTriple"))
              .build();
        }
      }
    }
    return getPythagoreanTripleMethod;
  }

  private static volatile io.grpc.MethodDescriptor<calculator.ComplexArithmeticOpArguments,
      calculator.ComplexArithmeticOpResult> getComplexOperationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ComplexOperation",
      requestType = calculator.ComplexArithmeticOpArguments.class,
      responseType = calculator.ComplexArithmeticOpResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<calculator.ComplexArithmeticOpArguments,
      calculator.ComplexArithmeticOpResult> getComplexOperationMethod() {
    io.grpc.MethodDescriptor<calculator.ComplexArithmeticOpArguments, calculator.ComplexArithmeticOpResult> getComplexOperationMethod;
    if ((getComplexOperationMethod = AdvancedCalculatorGrpc.getComplexOperationMethod) == null) {
      synchronized (AdvancedCalculatorGrpc.class) {
        if ((getComplexOperationMethod = AdvancedCalculatorGrpc.getComplexOperationMethod) == null) {
          AdvancedCalculatorGrpc.getComplexOperationMethod = getComplexOperationMethod =
              io.grpc.MethodDescriptor.<calculator.ComplexArithmeticOpArguments, calculator.ComplexArithmeticOpResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ComplexOperation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  calculator.ComplexArithmeticOpArguments.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  calculator.ComplexArithmeticOpResult.getDefaultInstance()))
              .setSchemaDescriptor(new AdvancedCalculatorMethodDescriptorSupplier("ComplexOperation"))
              .build();
        }
      }
    }
    return getComplexOperationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdvancedCalculatorStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdvancedCalculatorStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdvancedCalculatorStub>() {
        @java.lang.Override
        public AdvancedCalculatorStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdvancedCalculatorStub(channel, callOptions);
        }
      };
    return AdvancedCalculatorStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdvancedCalculatorBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdvancedCalculatorBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdvancedCalculatorBlockingStub>() {
        @java.lang.Override
        public AdvancedCalculatorBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdvancedCalculatorBlockingStub(channel, callOptions);
        }
      };
    return AdvancedCalculatorBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdvancedCalculatorFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdvancedCalculatorFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdvancedCalculatorFutureStub>() {
        @java.lang.Override
        public AdvancedCalculatorFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdvancedCalculatorFutureStub(channel, callOptions);
        }
      };
    return AdvancedCalculatorFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void pythagoreanTriple(calculator.PythagoreanTripleCheckerArguments request,
        io.grpc.stub.StreamObserver<calculator.PythgoreanTripleCheckerResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPythagoreanTripleMethod(), responseObserver);
    }

    /**
     */
    default void complexOperation(calculator.ComplexArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<calculator.ComplexArithmeticOpResult> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getComplexOperationMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AdvancedCalculator.
   */
  public static abstract class AdvancedCalculatorImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AdvancedCalculatorGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AdvancedCalculator.
   */
  public static final class AdvancedCalculatorStub
      extends io.grpc.stub.AbstractAsyncStub<AdvancedCalculatorStub> {
    private AdvancedCalculatorStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdvancedCalculatorStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdvancedCalculatorStub(channel, callOptions);
    }

    /**
     */
    public void pythagoreanTriple(calculator.PythagoreanTripleCheckerArguments request,
        io.grpc.stub.StreamObserver<calculator.PythgoreanTripleCheckerResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPythagoreanTripleMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void complexOperation(calculator.ComplexArithmeticOpArguments request,
        io.grpc.stub.StreamObserver<calculator.ComplexArithmeticOpResult> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getComplexOperationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AdvancedCalculator.
   */
  public static final class AdvancedCalculatorBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AdvancedCalculatorBlockingStub> {
    private AdvancedCalculatorBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdvancedCalculatorBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdvancedCalculatorBlockingStub(channel, callOptions);
    }

    /**
     */
    public calculator.PythgoreanTripleCheckerResult pythagoreanTriple(calculator.PythagoreanTripleCheckerArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPythagoreanTripleMethod(), getCallOptions(), request);
    }

    /**
     */
    public calculator.ComplexArithmeticOpResult complexOperation(calculator.ComplexArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getComplexOperationMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AdvancedCalculator.
   */
  public static final class AdvancedCalculatorFutureStub
      extends io.grpc.stub.AbstractFutureStub<AdvancedCalculatorFutureStub> {
    private AdvancedCalculatorFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdvancedCalculatorFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdvancedCalculatorFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<calculator.PythgoreanTripleCheckerResult> pythagoreanTriple(
        calculator.PythagoreanTripleCheckerArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPythagoreanTripleMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<calculator.ComplexArithmeticOpResult> complexOperation(
        calculator.ComplexArithmeticOpArguments request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getComplexOperationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PYTHAGOREAN_TRIPLE = 0;
  private static final int METHODID_COMPLEX_OPERATION = 1;

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
        case METHODID_PYTHAGOREAN_TRIPLE:
          serviceImpl.pythagoreanTriple((calculator.PythagoreanTripleCheckerArguments) request,
              (io.grpc.stub.StreamObserver<calculator.PythgoreanTripleCheckerResult>) responseObserver);
          break;
        case METHODID_COMPLEX_OPERATION:
          serviceImpl.complexOperation((calculator.ComplexArithmeticOpArguments) request,
              (io.grpc.stub.StreamObserver<calculator.ComplexArithmeticOpResult>) responseObserver);
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
          getPythagoreanTripleMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              calculator.PythagoreanTripleCheckerArguments,
              calculator.PythgoreanTripleCheckerResult>(
                service, METHODID_PYTHAGOREAN_TRIPLE)))
        .addMethod(
          getComplexOperationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              calculator.ComplexArithmeticOpArguments,
              calculator.ComplexArithmeticOpResult>(
                service, METHODID_COMPLEX_OPERATION)))
        .build();
  }

  private static abstract class AdvancedCalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdvancedCalculatorBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return calculator.CalculatorProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AdvancedCalculator");
    }
  }

  private static final class AdvancedCalculatorFileDescriptorSupplier
      extends AdvancedCalculatorBaseDescriptorSupplier {
    AdvancedCalculatorFileDescriptorSupplier() {}
  }

  private static final class AdvancedCalculatorMethodDescriptorSupplier
      extends AdvancedCalculatorBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdvancedCalculatorMethodDescriptorSupplier(String methodName) {
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
      synchronized (AdvancedCalculatorGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdvancedCalculatorFileDescriptorSupplier())
              .addMethod(getPythagoreanTripleMethod())
              .addMethod(getComplexOperationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
