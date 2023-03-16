import signal
import socket
import struct
import threading

filename = "ascii_art.txt"

hostname = "localhost"
port = 9876
coding = "utf-8"

tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
tcp_socket.connect((hostname, port))

tcp_address, tcp_port = tcp_socket.getsockname()

udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
udp_socket.bind((hostname, tcp_port))
# udp_socket.connect((hostname, port))

multicast_group = "224.1.1.1"
multicast_port = 5007

mult_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)
mult_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
mult_socket.bind(("", multicast_port))
# mult_socket.bind((multicast_group, multicast_port))
mult_req = struct.pack("4sl", socket.inet_aton(multicast_group), socket.INADDR_ANY)
mult_socket.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, mult_req)


def receive_message_tcp():
    while True:
        message = str(tcp_socket.recv(1024), coding)
        print("[TCP]: " + message)


def receive_message_udp():
    while True:
        message = str(udp_socket.recv(1024), coding)
        print("[UDP]: " + message)


def receive_message_mult():
    while True:
        message = str(mult_socket.recv(1024), coding)
        print("[MULT]: " + message)


def handler(signum, frame):
    close_connection()


def close_connection():
    message = "EXIT"
    tcp_socket.send(bytes(message, coding))


print("CLIENT UP")
client_tcp = threading.Thread(target=receive_message_tcp)
client_tcp.daemon = True
client_tcp.start()
client_udp = threading.Thread(target=receive_message_udp)
client_udp.daemon = True
client_udp.start()
client_mult = threading.Thread(target=receive_message_mult)
client_mult.daemon = True
client_mult.start()

signal.signal(signal.SIGINT, handler)

while True:
    send_message = input()
    if send_message == "U":
        file = open(filename)
        udp_socket.sendto(bytes(file.read(), coding), (hostname, port))
        file.close()
    elif send_message == "M":
        file = open(filename)
        mult_socket.sendto(bytes(file.read(), coding), (multicast_group, multicast_port))
        file.close()
    elif send_message == "EXIT":
        close_connection()
        break
    else:
        tcp_socket.send(bytes(send_message, coding))
