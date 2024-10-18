import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Smith extends Agent {

    protected void setup() {
        addBehaviour(new TickerBehaviour(this, 3000) {  // Period is set to 1 second
            protected void onTick() {
                try {
                    String server_address = "lab4-nlb-6535abf183eca2d7.elb.eu-west-3.amazonaws.com";
                    // String server_address = "15.188.14.243";
                    int port = 8080;

                    Socket socket = new Socket(server_address, port);
                    System.out.println(getLocalName() + " connected to server");

                    // Sending request to the server (if needed, otherwise just wait for response)
                    OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());

                    // Choose random number for the request (between 10 and 20)
                    int number = (int) (Math.random() * 30 + 30);

                    writer.write("Fibonacci(" + number + ")\n");
                    writer.flush();

                    // Waiting for the response from the server
                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String serverResponse = reader.readLine();
                    System.out.println(getLocalName() + " received response: " + serverResponse);

                    // Closing the connection after receiving the response only if not closed by the server already
                    if (!socket.isClosed()) {
                        socket.close();
                        System.out.println(getLocalName() + " closed connection");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
