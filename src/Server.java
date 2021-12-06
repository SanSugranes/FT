import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

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

        Distributor distributor = new Distributor();

        List<ServerThread> clients = new ArrayList<>();

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New socket connected");

                ServerThread st = new ServerThread(socket, distributor);
                clients.add(st);
                st.start();
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
