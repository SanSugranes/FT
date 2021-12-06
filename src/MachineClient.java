import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MachineClient {
    private static Socket socket;
    private static DataInputStream inputStream;
    private static DataOutputStream outputStream;

    public static void main(String[] args) {
        try {
            socket = new Socket("localhost", 3030);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            sendString("Hello World");
            do {
                byte message = inputStream.readByte();

                switch (message) {
                    case 2 -> {
                        int pathLength = inputStream.readInt();
                        byte[] pathData = new byte[pathLength];

                        inputStream.readFully(pathData, 0, pathLength);

                        String path = new String(pathData);

                        // File requestedDirectory = new File(path);

                        System.out.println(path);

                        // Sending to the server
                        sendString("Received : " + path);
                    }
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendString(String string) {
        byte[] resBytes = string.getBytes();
        try {
            outputStream.writeByte(1);
            outputStream.writeInt(resBytes.length);
            outputStream.write(resBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
