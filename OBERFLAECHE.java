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
        
        
        //Spielregeln
        JFrame fenster4 = new JFrame("Spielregeln");
        fenster4.setBounds(300, 300, 500, 300);
        
        JButton knopf7 = new JButton("Zurück");
        knopf7.setFont(new Font("Arial", Font.BOLD, 50));
        //Hier kommt der Text hin
        JLabel Regeln = new JLabel("1.Spielrichtung Das Spiel verläuft gegen den Uhrzeigersinn. ");
        

        
        knopf5.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    fenster4.setVisible(true);
                }
            }
        );
        
        knopf7.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    fenster4.setVisible(false);
                }
            }
        );
        
        
        Container zeichenflaeche1 = fenster4.getContentPane();
        fenster4.setBounds(300,300,500,200);
        
        zeichenflaeche1.add(Regeln, "North");


        //Spielfeld experimentell

        JFrame fenster3 = new JFrame("Optionen");
        fenster3.setBounds(300, 300, 500, 300);
        JPanel spielPanel = new JPanel(new BorderLayout());
        //Experiment für Spielfeld
        int size = 11;
        JPanel gridPanel = new JPanel(new GridLayout(size, size));

        JPanel zentrum = new JPanel();
        zentrum.setOpaque(false);
        zentrum.setLayout(new GridBagLayout());
        JLabel zentrenLabel = new JLabel("YLOPONOM");

        zentrenLabel.setFont(new Font("Arial", Font.BOLD, 80)); // Großer Text!
        zentrenLabel.setForeground(Color.BLACK);
        zentrum.add(zentrenLabel);

        // Panel über das Grid legen
        spielPanel.setLayout(new OverlayLayout(spielPanel));
        spielPanel.add(zentrum);
        spielPanel.add(gridPanel);

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                JPanel feld = new JPanel();
                feld.setPreferredSize(new Dimension(60, 60));

                boolean istRand = (y == 0 || y == size - 1 || x == 0 || x == size - 1);

                if (istRand) {
                    feld.setBackground(Color.LIGHT_GRAY);
                    feld.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    JLabel beschriftung = new JLabel("Feld");
                    beschriftung.setFont(new Font("Arial", Font.PLAIN, 9));
                    feld.add(beschriftung);
                } else {
                    feld.setBackground(Color.WHITE);

                }
                gridPanel.add(feld);
                
            }

        }

        spielPanel.add(gridPanel, BorderLayout.CENTER);
        fenster3.setContentPane(spielPanel);

        knopf1.addActionListener
        (
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    fenster3.setVisible(true);
                }
            }
        );

    }

    public static void main(String[] args) {
        new OBERFLAECHE();
    }
}

    
        

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

