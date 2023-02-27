
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Сервер запущен, ожидаем соединение....");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился к серверу!");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            University gb = new University();

            while (true) {
                String clientRequest = dataInputStream.readUTF();
                if (clientRequest.equals("0")) {
                    break;
                }

                else if (clientRequest.equals("1")) {
                    dataOutputStream.writeUTF("Введите имя студента: ");
                    String clientName = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Введите номер телефона студента: ");
                    int clientPhone = Integer.parseInt(dataInputStream.readUTF());
                    dataOutputStream.writeUTF("Введите группу студента: ");
                    int clientGroup = Integer.parseInt(dataInputStream.readUTF());

                    dataOutputStream.writeUTF(gb.listStudent(clientName, clientPhone, clientGroup));

                } else if (clientRequest.equals("2")) {
                    dataOutputStream.writeUTF("Введите имя студента: ");
                    String clientName = dataInputStream.readUTF();
                    dataOutputStream.writeUTF("Введите номер телефона студента: ");
                    int clientPhone = Integer.parseInt(dataInputStream.readUTF());

                    dataOutputStream.writeUTF(gb.delFromList(clientName, clientPhone));

                } else if (clientRequest.equals("3")) {
                    dataOutputStream.writeUTF("Введите номер группы: ");
                    int clientGroup = Integer.parseInt(dataInputStream.readUTF());
                    dataOutputStream.writeUTF(gb.getList(clientGroup));

                } else {
                    dataOutputStream.writeUTF("Некорректный запрос.");
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
