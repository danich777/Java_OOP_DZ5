import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

    public class ClientApp {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            try (Socket socket = new Socket("localhost", 1234)) {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    System.out.println("Выберите пункт меню: 1 - добавить студента \n 2 - удалить студента \n" +
                            "3 - показать группу \n 0 - выход.");
                    String request = scanner.nextLine();
                    dataOutputStream.writeUTF(request);
                    if (request.equals("0"))
                        break;
                        
                    System.out.println("Получили сообщение от сервера: " + dataInputStream.readUTF());
                }
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }
            finally{
                scanner.close();
            }
        }
    }
}
