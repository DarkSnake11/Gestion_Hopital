/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphisme;

/**
 *
 * @author rapin
 */
import Main.Main;
import Main.connexion;
import aactionType.BrowseElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LocalHosto extends Graphisme {

    private JPanel panLocalhost;
    private JLabel nomDatabase, loginDatabase, passwordDatabase, affich, image;
    private JFormattedTextField textDB, textuserDB;
    private JPasswordField textpwDB;
    private JButton valider, annuler;
    private String DBNAME, DBUSER, DBPW;
    private BrowseElement bienOuej;
    private TypeRequete rech;
    private boolean onAfficheouPas;

    public LocalHosto() {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panLocalhost = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("connexion.jpg"));

        //Instanciation des JLabel
        nomDatabase = new JLabel();
        loginDatabase = new JLabel();
        passwordDatabase = new JLabel();
        affich = new JLabel();

        //Instanciation des Textfield
        textDB = new JFormattedTextField();
        textuserDB = new JFormattedTextField();
        textpwDB = new JPasswordField();

        //Instanciation des Boutons
        valider = new JButton();
        annuler = new JButton();

        //Users ECE
        nomDatabase.setLocation(200, 150);
        nomDatabase.setText("Nom Database :");
        nomDatabase.setSize(100, 35);

        textDB.setSize(200, 35);
        textDB.setLocation(340, 150);

        //Password ECE
        loginDatabase.setLocation(200, 225);
        loginDatabase.setText("Login Database :");
        loginDatabase.setSize(120, 35);

        textuserDB.setSize(200, 35);
        textuserDB.setLocation(340, 225);

        //User SQL
        passwordDatabase.setLocation(200, 300);
        passwordDatabase.setText("Password Database :");
        passwordDatabase.setSize(150, 35);

        textpwDB.setSize(200, 35);
        textpwDB.setLocation(340, 300);

        //Bouton Validation
        valider.setLocation(450, 500);
        valider.setText("Valider");
        valider.setSize(100, 35);

        //Bouton Annuler
        annuler.setLocation(250, 500);
        annuler.setText("Annuler");
        annuler.setSize(100, 35);

        affich.setLocation(320, 50);
        affich.setSize(200, 35);
        affich.setText("Connexion via Localhost");
    }

    public JPanel connectionLocalhostGraphique(boolean onAfficheouPas) {

        this.add(affich);

        this.add(nomDatabase);
        this.add(textDB);

        this.add(loginDatabase);
        this.add(textuserDB);

        this.add(passwordDatabase);
        this.add(textpwDB);

        this.add(valider);
        this.add(annuler);

        this.add(image);
        image.setVisible(true);

        this.setVisible(onAfficheouPas);
        repaint();

        return panLocalhost;
    }

    public void getFields() {

        class ConnexionThread extends Thread {

            public void run() {
                try {
                    connexion connectSQL = new connexion(DBNAME, DBUSER, DBPW);
                    if (connectSQL.coco()) {
                        rech = new TypeRequete(connectSQL);

                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBNAME = textDB.getText();
                DBUSER = textuserDB.getText();
                DBPW = new String(textpwDB.getPassword());

                new ConnexionThread().start();
                panLocalhost = connectionLocalhostGraphique(false);

            }
        });

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.mainWindow.afficherMenu(true);

                panLocalhost = connectionLocalhostGraphique(false);
            }
        });

    }

}
