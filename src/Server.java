import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/* Message Bytes
* Server
*   0: Request indexing from Indexer Client
*   1: Result file from Machine Client
* Machine Client
*   2: Request indexing from Server
* */

public class Server {
    public static void main(String[] args) {
        int port = 3030;
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New socket connected");

                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
