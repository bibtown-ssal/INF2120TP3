
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * Cette classe fait l'affichage d'un dessin Fractal, et permet de rentrer jusqu'a 8 couple de fonctions
 * pour definir ce dessin.
 */
public class Flame extends JFrame {
    private static final String _TITRE_FENETRE = "Flame";
    private static final long serialVersionUID = 1L;

    private PanneauImage image;

    /**Prend une liste de PanneauFonctionLineaire, cree un systeme d'equation (IFS) avec les fonctions contenues dans
     * ceux-ci et affiche le fractal resultat a l'ecran
     *
     * @param liste la liste de PanneauFonctionLineaire contenant les fonctions pour le systeme
     */
    public void redessiner (ArrayList<PanneauFonctionLineaire> liste){
        IFS system = new IFS();
        for(PanneauFonctionLineaire p : liste){
            system.add(p.fonction);
        }
        image.ifs = system;
        repaint();
    }

    /**Affiche a l'ecran 2 boutons, une liste de taille dynamique (0-8) de couple de fonctions et un dessin fractal.
     *
     * @throws HeadlessException quand GraphicsEnvirenment.isHeadless est vrai
     */
    private Flame() throws HeadlessException {
        super( _TITRE_FENETRE );
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        setLayout( new BorderLayout() );

        PanneauSelection selection = new PanneauSelection();

        setSize(1000,600);
        image = new PanneauImage();

        add( image, BorderLayout.CENTER );
        add( selection, BorderLayout.WEST);
        //pack();
        setVisible( true );
    }


    public static void main( String [] argv ) {
        SwingUtilities.invokeLater( () -> new Flame() );
    }
}