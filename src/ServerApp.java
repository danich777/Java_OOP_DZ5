
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Сервер запущен, ожидаем соединение....");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился к серверу!");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            School school = new School();

            while (true) {
                String clientRequest = dataInputStream.readUTF();
                if (clientRequest.equals("0")) {
                    break;
                }

                else if (clientRequest.equals("1")) {
                    dataOutputStream.writeUTF("Введите имя учащегося: ");
                    String clientName = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Введите номер телефона учащегося: ");
                    int clientPhone = Integer.parseInt(dataInputStream.readUTF());
                    dataOutputStream.writeUTF("Введите класс учащегося: ");
                    int clientGroup = Integer.parseInt(dataInputStream.readUTF());

                    dataOutputStream.writeUTF(school.listStudent(clientName, clientPhone, clientGroup));

                } else if (clientRequest.equals("2")) {
                    dataOutputStream.writeUTF("Введите имя учащегося: ");
                    String clientName = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Введите номер телефона учащегося ");
                    int clientPhone = Integer.parseInt(dataInputStream.readUTF());

                    dataOutputStream.writeUTF(school.delFromList(clientName, clientPhone));

                } else if (clientRequest.equals("3")) {
                    dataOutputStream.writeUTF("Введите номер класса: ");
                    int clientGroup = Integer.parseInt(dataInputStream.readUTF());
                    dataOutputStream.writeUTF(school.getList(clientGroup));

                } else if (clientRequest.equals("4")) {
                    dataOutputStream.writeUTF("Введите имя учащегося для изменения информации: ");
                    String clientName = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Введите новый номер телефона учащегося: ");
                    int clientPhone = Integer.parseInt(dataInputStream.readUTF());
                    dataOutputStream.writeUTF("Введите новый класс учащегося: ");
                    int clientGroup = Integer.parseInt(dataInputStream.readUTF());

                    dataOutputStream.writeUTF(school.editStudent(clientName, clientPhone, clientGroup));
                }

                else {
                    dataOutputStream.writeUTF("Некорректный запрос.");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
