import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Smith extends Agent {

    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {  // Period is set to 1 second
            protected void onTick() {
                try {
                    Socket socket = new Socket("127.0.0.1", 8080);
                    System.out.println(getLocalName() + " connected to server");

                    // Sending request to the server (if needed, otherwise just wait for response)
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

                    // Choose random number for the request (between 10 and 20)
                    int number = (int) (Math.random() * 10 + 10);

                    writer.write("Fibonacci(" + number + ")\n");
                    writer.flush();

                    // Waiting for the response from the server
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String serverResponse = reader.readLine();
                    System.out.println(getLocalName() + " received response: " + serverResponse);

                    // Closing the connection after receiving the response
                    socket.close();
                    System.out.println(getLocalName() + " disconnected");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
