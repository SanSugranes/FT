import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream inputStream = new DataInputStream(this.socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(this.socket.getOutputStream());

            int exitCode = 0;

            do {
                System.out.println(this.socket.isClosed());
                byte message = inputStream.readByte();
                System.out.println(message);

                switch (message) {
                    // Request Indexing
                    case 0 -> {
                        int pathLength = inputStream.readInt();
                        byte[] pathData = new byte[pathLength];

                        inputStream.readFully(pathData, 0, pathLength);

                        // Send to MachineClient
                        outputStream.writeByte(2);
                        outputStream.writeInt(pathLength);
                        outputStream.write(pathData);
                    }

                    // Result indexing
                    case 1 -> {
                        int resLength = inputStream.readInt();
                        byte[] res = new byte[resLength];

                        inputStream.readFully(res, 0, resLength);
                        String resString = new String(res);
                        System.out.println(resString);
                    }
                }
            } while (!this.socket.isClosed());

            this.socket.close();
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
