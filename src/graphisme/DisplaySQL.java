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
import Main.connexion;
import aactionType.BrowseElement;
import aactionType.RechercheElement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DisplaySQL extends JFrame {

    private final JLabel image;
    private final JLabel bravo;
    private JButton annuler;
    private JPanel panSQL;
    private JTable maTable;
    private BrowseElement bienOuej;
    private RechercheElement chercher;

    public DisplaySQL(connexion connexion, String table, String colonne, int nbElem) {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSQL = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        bravo = new JLabel();
        bravo.setLocation(320, 80);
        bravo.setText("Resultat de votre recherche :");
        bravo.setSize(500, 35);

        //Bouton Annuler
        annuler = new JButton();
        annuler.setLocation(350, 500);
        annuler.setText("Retour");
        annuler.setSize(100, 35);

        panSQL = displayQueries(true, table, colonne, nbElem, connexion);

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                bienOuej = new BrowseElement(connexion);
                bienOuej.setCheckbox(connexion);

            }
        });
    }

    public DisplaySQL(connexion connexion, String choiceTab, String finalColonne, int nbElem, String affinage) {
        setTitle("Gestion d'un centre hospitalier");
        setSize(800, 600);
        setLocation(425, 200);
        panSQL = new JPanel(); // instancier le panneau

        image = new JLabel(new ImageIcon("hopital.jpg"));

        bravo = new JLabel();
        bravo.setLocation(320, 80);
        bravo.setText("Resultat de votre recherche :");
        bravo.setSize(500, 35);

        //Bouton Annuler
        annuler = new JButton();
        annuler.setLocation(350, 500);
        annuler.setText("Retour");
        annuler.setSize(100, 35);

        panSQL = displayQueriesSearch(true, choiceTab, finalColonne, nbElem, connexion, affinage);

        annuler.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                chercher = new RechercheElement(connexion);
                chercher.setCheckbox(connexion);

            }
        });
    }

    public JPanel displayQueriesSearch(boolean onAfficheouPas, String table,
            String colonne, int nbElem, connexion connexion, String affinage) {

        this.add(bravo);

        maTable = connexion.readDTBSearch(table, colonne, 5, true, affinage);
        JScrollPane scrollPane = new JScrollPane(maTable);
        scrollPane.setLocation(80, 130);
        scrollPane.setSize(640, 350);
        this.add(scrollPane);
        this.add(annuler);

        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSQL;
    }

    public JPanel displayQueries(boolean onAfficheouPas, String table,
            String colonne, int nbElem, connexion connexion) {

        this.add(bravo);

        maTable = connexion.readDTB(table, colonne, nbElem, true);
        JScrollPane scrollPane = new JScrollPane(maTable);
        scrollPane.setLocation(80, 130);
        scrollPane.setSize(640, 350);
        this.add(scrollPane);
        this.add(annuler);

        this.add(image);
        image.setVisible(false);

        this.setVisible(onAfficheouPas);
        repaint();

        return panSQL;
    }

}
