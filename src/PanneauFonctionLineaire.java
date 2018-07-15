import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;


import static java.awt.GridBagConstraints.RELATIVE;
import static java.awt.GridBagConstraints.REMAINDER;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

/**
 * Panneau de construction de fonction lineaire qui doit absolument etre placee dans un PanneauSelection
 * Affiche 6 TextField ou l'utilisateur entre des doubles et un bouton pour retirer le PanneauFonctionLineaire
 * du PanneauSelection.
 *
 * Affiche un message d'erreur a l'ecran si l'utilisateur entre du texte qui ne correspond pas a un double.
 */
public class PanneauFonctionLineaire extends JPanel {
    private JButton boutonRetirer;
    private JLabel libeleA;
    private JLabel libeleB;
    private JLabel libeleC;
    private JLabel libeleD;
    private JLabel libeleE;
    private JLabel libeleF;
    private JLabel libeleFonction;
    private JTextField champsTexteA;
    private JTextField champsTexteB;
    private JTextField champsTexteC;
    private JTextField champsTexteD;
    private JTextField champsTexteE;
    private JTextField champsTexteF;
    private int identifiant;
    public FonctionLineaire fonction = new FonctionLineaire();
    //private PanneauSelection parent = (PanneauSelection)(this.getParent());

    public int getIdentifiant() {
        return identifiant;
    }

    /**
     * Methode qui rafraichi libeleFonction selon les valeurs de la FonctionLineaire fonction.
     *
     * Utilise le .toString de FonctionLineaire.
     */
    private void  rafraichirFonction(){
        libeleFonction.setText(fonction.toString());
    }

    /**Methode qui prend un event d'un JTextComponant et retourne la valeur du text contene par ce JText en double.
     *
     * La methode utilise l'event pour aller chercher la String contenu par le JTextComponant, la transforme en double.
     * Si la transformation est impossible, le methode affiche une fenetre d'erreur a l'utilisateur, ecrase le contenut
     * du JTextComponant pour y inserer la valeur par defaut des champs de texte (defini dans la class Constantes).
     * retourne le double ecrit s'il est valide, la valeur par defaut sinon.
     *
     * @param e     un ActionEvent dans une composante qui herite de JTextComponant
     * @return      la valeur double du champs de texte, ou la valeur par defaut si le champs n'est pas
     *              transformable en double.
     */
    private double JTextComponantVersDouble ( ActionEvent e){
        double valeur;
        try{
            valeur = Double.valueOf( ( (JTextComponent) e.getSource() ) .getText());
        }catch(NumberFormatException f){
            JOptionPane.showMessageDialog(champsTexteA, Constantes.MESS_ERR_PAS_UN_CHIFFRE,
                    Constantes.MESS_TITRE_ERR_CHIFFRE, ERROR_MESSAGE);
            ( (JTextComponent) e.getSource() ).setText(Constantes.CHAMPS_DEFAULT);
            valeur = Double.valueOf(Constantes.CHAMPS_DEFAULT);
        }
        return valeur;
    }

    /**
     * Construit un panneau permettant d'entrer 6 doubles comme parametres (A a F), d'afficher les fonctions construites
     * par ceux ci, et un button permettant de retirer ces fonctions
     */
    public PanneauFonctionLineaire(int id){
        identifiant = id;

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        libeleA = new JLabel(Constantes.LIBELE_A);
        gbc.gridx = Constantes.LIBELE_A_POSITION_X;
        gbc.gridy = Constantes.LIBELE_A_POSITION_Y;
        add(libeleA, gbc);

        libeleB = new JLabel(Constantes.LIBELE_B);
        gbc.gridx = Constantes.LIBELE_B_POSITION_X;
        gbc.gridy = Constantes.LIBELE_B_POSITION_Y;
        add(libeleB, gbc);

        libeleC = new JLabel(Constantes.LIBELE_C);
        gbc.gridx = Constantes.LIBELE_C_POSITION_X;
        gbc.gridy = Constantes.LIBELE_C_POSITION_Y;
        add(libeleC, gbc);

        libeleD = new JLabel(Constantes.LIBELE_D);
        gbc.gridx = Constantes.LIBELE_D_POSITION_X;
        gbc.gridy = Constantes.LIBELE_D_POSITION_Y;
        add(libeleD, gbc);

        libeleE = new JLabel(Constantes.LIBELE_E);
        gbc.gridx = Constantes.LIBELE_E_POSITION_X;
        gbc.gridy = Constantes.LIBELE_E_POSITION_Y;
        add(libeleE, gbc);

        libeleF = new JLabel(Constantes.LIBELE_F);
        gbc.gridx = Constantes.LIBELE_F_POSITION_X;
        gbc.gridy = Constantes.LIBELE_F_POSITION_Y;
        add(libeleF, gbc);

        champsTexteA = new JTextField(Constantes.CHAMPS_DEFAULT, Constantes.CHAMPS_DEFAULT_COLUMN);
        gbc.gridx = Constantes.CHAMPS_A_POSITION_X;
        gbc.gridy = Constantes.CHAMPS_A_POSITION_Y;
        add(champsTexteA, gbc);

        champsTexteA.addActionListener((e) -> {
            fonction.setA(JTextComponantVersDouble(e));
            rafraichirFonction();
        });

        champsTexteB = new JTextField(Constantes.CHAMPS_DEFAULT, Constantes.CHAMPS_DEFAULT_COLUMN);
        gbc.gridx = Constantes.CHAMPS_B_POSITION_X;
        gbc.gridy = Constantes.CHAMPS_B_POSITION_Y;
        add(champsTexteB, gbc);

        champsTexteB.addActionListener((e) -> {
            fonction.setB(JTextComponantVersDouble(e));
            rafraichirFonction();
        });

        champsTexteC = new JTextField(Constantes.CHAMPS_DEFAULT, Constantes.CHAMPS_DEFAULT_COLUMN);
        gbc.gridx = Constantes.CHAMPS_C_POSITION_X;
        gbc.gridy = Constantes.CHAMPS_C_POSITION_Y;
        add(champsTexteC, gbc);

        champsTexteC.addActionListener((e) -> {
            fonction.setC(JTextComponantVersDouble(e));
            rafraichirFonction();
        });

        champsTexteD = new JTextField(Constantes.CHAMPS_DEFAULT, Constantes.CHAMPS_DEFAULT_COLUMN);
        gbc.gridx = Constantes.CHAMPS_D_POSITION_X;
        gbc.gridy = Constantes.CHAMPS_D_POSITION_Y;
        add(champsTexteD, gbc);

        champsTexteD.addActionListener((e) -> {
            fonction.setD(JTextComponantVersDouble(e));
            rafraichirFonction();
        });

        champsTexteE = new JTextField(Constantes.CHAMPS_DEFAULT, Constantes.CHAMPS_DEFAULT_COLUMN);
        gbc.gridx = Constantes.CHAMPS_E_POSITION_X;
        gbc.gridy = Constantes.CHAMPS_E_POSITION_Y;
        add(champsTexteE, gbc);

        champsTexteE.addActionListener((e) -> {
            fonction.setE(JTextComponantVersDouble(e));
            rafraichirFonction();
        });

        champsTexteF = new JTextField(Constantes.CHAMPS_DEFAULT, Constantes.CHAMPS_DEFAULT_COLUMN);
        gbc.gridx = Constantes.CHAMPS_F_POSITION_X;
        gbc.gridy = Constantes.CHAMPS_F_POSITION_Y;
        add(champsTexteF, gbc);

        champsTexteF.addActionListener((e) -> {
            fonction.setF(JTextComponantVersDouble(e));
            rafraichirFonction();
        });

        boutonRetirer = new JButton(Constantes.BOUTON_RETIRER_LIBELE);
        gbc.gridx = Constantes.BOUTON_RETIRER_POSITION_X;
        gbc.gridy = Constantes.BOUTON_RETIRER_POSITION_Y;
        gbc.gridheight = REMAINDER;
        add(boutonRetirer, gbc);
        gbc.gridheight = 1;

        boutonRetirer.addActionListener((e) -> {
            JButton temp = (JButton) e.getSource();
            PanneauFonctionLineaire temp2 = (PanneauFonctionLineaire)temp.getParent();
            ((PanneauSelection)(this.getParent())).retirer(temp2);
        });

        libeleFonction = new JLabel(fonction.toString());
        gbc.gridx = Constantes.LIBELE_FONCTION_POSITION_X;
        gbc.gridy = Constantes.LIBELE_FONCTION_POSITION_Y;
        gbc.gridwidth = RELATIVE;
        add(libeleFonction, gbc);
        gbc.gridwidth = 1;
    }

}
