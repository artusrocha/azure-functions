# Generated by the gRPC Python protocol compiler plugin. DO NOT EDIT!
"""Client and server classes corresponding to protobuf-defined services."""
import grpc

from azure_functions_worker.protos import FunctionRpc_pb2 as FunctionRpc__pb2


class FunctionRpcStub(object):
    """Interface exported by the server.
    """

    def __init__(self, channel):
        """Constructor.

        Args:
            channel: A grpc.Channel.
        """
        self.EventStream = channel.stream_stream(
                '/AzureFunctionsRpcMessages.FunctionRpc/EventStream',
                request_serializer=FunctionRpc__pb2.StreamingMessage.SerializeToString,
                response_deserializer=FunctionRpc__pb2.StreamingMessage.FromString,
                )


class FunctionRpcServicer(object):
    """Interface exported by the server.
    """

    def EventStream(self, request_iterator, context):
        """Missing associated documentation comment in .proto file."""
        context.set_code(grpc.StatusCode.UNIMPLEMENTED)
        context.set_details('Method not implemented!')
        raise NotImplementedError('Method not implemented!')


def add_FunctionRpcServicer_to_server(servicer, server):
    rpc_method_handlers = {
            'EventStream': grpc.stream_stream_rpc_method_handler(
                    servicer.EventStream,
                    request_deserializer=FunctionRpc__pb2.StreamingMessage.FromString,
                    response_serializer=FunctionRpc__pb2.StreamingMessage.SerializeToString,
            ),
    }
    generic_handler = grpc.method_handlers_generic_handler(
            'AzureFunctionsRpcMessages.FunctionRpc', rpc_method_handlers)
    server.add_generic_rpc_handlers((generic_handler,))


 # This class is part of an EXPERIMENTAL API.
class FunctionRpc(object):
    """Interface exported by the server.
    """

    @staticmethod
    def EventStream(request_iterator,
            target,
            options=(),
            channel_credentials=None,
            call_credentials=None,
            insecure=False,
            compression=None,
            wait_for_ready=None,
            timeout=None,
            metadata=None):
        return grpc.experimental.stream_stream(request_iterator, target, '/AzureFunctionsRpcMessages.FunctionRpc/EventStream',
            FunctionRpc__pb2.StreamingMessage.SerializeToString,
            FunctionRpc__pb2.StreamingMessage.FromString,
            options, channel_credentials,
            insecure, call_credentials, compression, wait_for_ready, timeout, metadata)
