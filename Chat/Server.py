import socket
import sys
import threading

hostname = "localhost"
port = 9876
coding = "utf-8"

clients = []

tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_socket.bind((hostname, port))
tcp_socket.listen(200)

udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
udp_socket.bind((hostname, port))


def client_tcp(connection, client_id):
    while True:
        try:
            msg = str(connection.recv(1024), coding)
            if msg != "EXIT":
                message = "[" + str(client_id) + "] " + msg
                send_to_all_clients_tcp(connection, message)
            else:
                clients[client_id][2] = False
                show_message("CLIENT DISCONNECTED [" + str(client_id) + "]")
                sys.exit(0)
        except ConnectionResetError:
            clients[client_id][2] = False
            show_message("CLIENT DISCONNECTED [" + str(client_id) + "]")
            sys.exit(0)


def client_udp():
    while True:
        msg, address = udp_socket.recvfrom(1024)
        message = "[" + str(find_id(address)) + "] " + str(msg, coding)
        send_to_all_clients_udp(address, message)


def send_to_all_clients_tcp(connection, message):
    for conn, addr, running in clients:
        if conn != connection and running:
            conn.send(bytes(message, coding))


def send_to_all_clients_udp(address, message):
    for conn, addr, running in clients:
        if addr != address and running:
            udp_socket.sendto(bytes(message, coding), addr)


def find_id(address):
    for index, client in enumerate(clients):
        if client[1] == address:
            return index
    return -1


def welcome_client(connection, client_id):
    show_message("NEW CLIENT [" + str(client_id) + "]")
    connection.send(bytes("WELCOME [" + str(client_id) + "]", coding))


def show_message(msg):
    print(msg)


def get_tcp_connections():
    while True:
        conn_tcp, addr_tcp = tcp_socket.accept()
        conn_id = len(clients)
        clients.append([conn_tcp, addr_tcp, True])
        welcome_client(conn_tcp, conn_id)
        tcp_client = threading.Thread(target=client_tcp, args=(conn_tcp, conn_id))
        tcp_client.start()


udp_client = threading.Thread(target=client_udp)
udp_client.start()
print("SERVER UP")
server = threading.Thread(target=get_tcp_connections)
server.start()
