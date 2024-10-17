import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Architect extends Agent {
    private JFrame frame;
    private JTextField agentCountField;
    private JButton launchButton;
    private int agentCount = 0;

    protected void setup() {
        frame = new JFrame("Architect Agent");
        frame.setSize(300, 200);
        agentCountField = new JTextField("Number of Agents", 20);
        launchButton = new JButton("Launch Agents");

        launchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agentCount = Integer.parseInt(agentCountField.getText());
                launchAgents(agentCount);
            }
        });

        frame.getContentPane().add(agentCountField, "North");
        frame.getContentPane().add(launchButton, "South");
        frame.setVisible(true);
    }

    private void launchAgents(int numAgents) {
        AgentContainer container = getContainerController();
        try {
            for (int i = 0; i < numAgents; i++) {
                AgentController agent = container.createNewAgent("AgentSmith_" + i, "Smith", null);
                agent.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
