import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientView {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream inputStream = null;
        DataOutputStream outputStream = null;

        try {
            socket = new Socket("localhost", 3030);
            inputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String path = "C:\\";
            byte[] pathData = path.getBytes();
            int pathDataLength = pathData.length;

            assert outputStream != null;
            outputStream.writeByte(0);
            outputStream.writeInt(pathDataLength);
            outputStream.write(pathData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
