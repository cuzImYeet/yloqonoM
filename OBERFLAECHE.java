//Autor Diyar Karatas
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class OBERFLAECHE
{

        public OBERFLAECHE() 
        {
        //Startoberfläche
        JFrame fenster1 = new JFrame("Startbildschirm");
        fenster1.setBounds(300, 300, 500, 300);

        //Schriftzug
        JPanel hauptPanel = new JPanel(new BorderLayout());
        
       
        

        //Erzeugt den Namen des Spiels im Hauptfenster
        JLabel label1 = new JLabel("YLOPONOM", SwingConstants.CENTER);
        label1.setFont(new Font("Arial", Font.BOLD, 70));
        hauptPanel.add(label1, BorderLayout.NORTH);

        
        //Knöpfe
        
        JPanel zentralesPanel = new JPanel(new GridBagLayout());
        
        

        JPanel start = new JPanel();
        start.setLayout(new BoxLayout(start, BoxLayout.Y_AXIS));
        
        
        

        JButton knopf1 = new JButton("Starten");
        knopf1.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf2 = new JButton("Optionen");
        knopf2.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf3 = new JButton("Beenden");
        knopf3.setFont(new Font("Arial", Font.BOLD, 50));
        
         knopf3.addActionListener
        (
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        }
        );


        start.add(knopf1);
        start.add(Box.createVerticalStrut(10));
        start.add(knopf2);
        start.add(Box.createVerticalStrut(10));
        start.add(knopf3);

        zentralesPanel.add(start, new GridBagConstraints());

       
        hauptPanel.add(zentralesPanel, BorderLayout.CENTER);

        
        fenster1.setContentPane(hauptPanel);
        fenster1.setVisible(true);
        
        //Optionen
        
        JFrame fenster2 = new JFrame("Optionen");
        fenster2.setBounds(300, 300, 500, 300);
        
        JPanel hauptPanelOptionen = new JPanel(new BorderLayout());
        
        JPanel nebenPanel1 = new JPanel(new BorderLayout());
        
        JPanel optionen = new JPanel();
        optionen.setLayout(new BoxLayout(optionen, BoxLayout.X_AXIS));
        nebenPanel1.add(optionen, BorderLayout.NORTH);
        
        JPanel centerPanel2 = new JPanel(new GridBagLayout());

        JButton knopf4 = new JButton("Steuerung");
        knopf4.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf5 = new JButton("Spielregeln");
        knopf5.setFont(new Font("Arial", Font.BOLD, 50));
        JButton knopf6 = new JButton("Zurück");
        knopf6.setFont(new Font("Arial", Font.BOLD, 50));
        
        //Macht zweites Fenster Sichtbar
        knopf2.addActionListener
        (
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fenster2.setVisible(true);
            }
        }
        );
        
        knopf6.addActionListener
        (
        new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fenster2.setVisible(false);
            }
        }
        );
        optionen.add(knopf4);
        optionen.add(Box.createVerticalStrut(10));
        optionen.add(knopf5);
        optionen.add(Box.createVerticalStrut(10));
        optionen.add(knopf6);
        
        
        
        fenster2.setContentPane(nebenPanel1);
        fenster2.setVisible(false);

    }
    

    public static void main(String[] args) {
        new OBERFLAECHE();
    }
}
   
        
        
        

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

