import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Distributor {
    public List<ServerThread> clients;

    public Distributor(){
        clients = new ArrayList<>();
    }

    public void addSocket(ServerThread serverThread){
        this.clients.add(serverThread);
    }

    public void distributeByte(byte b){
        for(ServerThread st : clients){

        }
    }
}
