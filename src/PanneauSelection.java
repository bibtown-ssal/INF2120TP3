import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;

import java.util.ArrayList;

/**
 * Section du GUI creee par Flame. Contient 2 boutons (ajouter et Dessiner) ainsi que la logique d'ajout (par le
 * bouton afficher) et de retrait de panneaux de fonction lineaire, qui sont afficher a l'interieur de cette section
 * de GUI.
 *
 * Doit absolument etre dans un Flame (extends JFrame)
 */
public class PanneauSelection extends JPanel {
    private JButton boutonAjouter;
    private JButton boutonDessiner;
    private ArrayList<PanneauFonctionLineaire> fonctions;
    private int nbFonctionLineaire = 0;
    private int cptIdentifiant = 0;

    /**
     * Listener sur le bouton ajouter. Ajoute un "PanneauFonctionLineaire" si il y en a moins de 8, ne fait rien sinon.
     */
    private ActionListener ajouterReaction = ( e ) -> {
        if(nbFonctionLineaire < Constantes.NB_MAX_FONCTION){
            cptIdentifiant++;
            PanneauFonctionLineaire panneau = new PanneauFonctionLineaire(cptIdentifiant);
            nbFonctionLineaire++;
            GridBagConstraints c = new GridBagConstraints();
            c.gridy = cptIdentifiant;
            c.gridx = 0;
            c.gridwidth = 2;
            add(panneau, c);
            this.revalidate();
            this.repaint();
            this.fonctions.add(panneau);
        }
    };

    /**Retire un PanneauFonctionLineaire de l'affichage et de la liste interne de Panneau
     *
     * @param aRetirer Le panneau a retirer
     */
    public void retirer(PanneauFonctionLineaire aRetirer){
        remove(aRetirer);
        for(int i = 0; i < this.fonctions.size(); i++){
            if(fonctions.get(i).getIdentifiant() == aRetirer.getIdentifiant()){
                fonctions.remove(i);
                nbFonctionLineaire--;
                this.revalidate();
                this.repaint();
            }
        }
    }

    /**
     * Cree 2 boutons, Ajouter et Dessiner, pour utilisation en conjonction avec "Flame"
     */
    public PanneauSelection(){
        fonctions = new ArrayList<>();

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        boutonAjouter = new JButton(Constantes.BOUTON_AJOUTER_LIBELE);
        gbc.gridx = Constantes.BOUTON_AJOUTER_POSITION_X;
        gbc.gridy = Constantes.BOUTON_AJOUTER_POSITION_Y;
        add(boutonAjouter,gbc);

        boutonAjouter.addActionListener(ajouterReaction);

        boutonDessiner = new JButton(Constantes.BOUTON_DESSINER_LIBELE);
        gbc.gridx = Constantes.BOUTON_DESSINER_POSITION_X;
        gbc.gridy = Constantes.BOUTON_DESSINER_POSITION_Y;
        add(boutonDessiner,gbc);

        boutonDessiner.addActionListener((e) -> {
            ((Flame)(this.getParent().getParent().getParent().getParent())).redessiner(this.fonctions);
        });
    }
}
