
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class PanneauImage extends JPanel {
    private static final int TAILLE_X = 600;
    private static final int TAILLE_Y = 600;
    private static final long serialVersionUID = 1L;

    private static final Point2D.Double COIN_SUPERIEUR_GAUCHE = new Point2D.Double(  0.0,  0.0 );
    private static final Point2D.Double COIN_INFERIEUR_DROIT  = new Point2D.Double(  1.0,  1.0 );

    public IFS ifs;
    public final int NBR_ITERATION = 70_000;
    public final int NBR_REJETE = 100;


    public PanneauImage() {
        this.setSize( TAILLE_X, TAILLE_Y );

        ifs = new IFS();

        ifs.add( new FonctionLineaire(  0.1950, -0.4880,  0.3440,  0.4430, 0.4431, 0.2453 ) );
        ifs.add( new FonctionLineaire(  0.4620,  0.4140, -0.2520,  0.3610, 0.2511, 0.5692 ) );
        ifs.add( new FonctionLineaire( -0.0580, -0.0700,  0.4530, -0.1110, 0.5976, 0.0969 ) );
        ifs.add( new FonctionLineaire( -0.0350,  0.0700, -0.4690, -0.0220, 0.4884, 0.5069 ) );
        ifs.add( new FonctionLineaire( -0.6370,  0.0000,  0.0000,  0.5010, 0.8562, 0.2513 ) );
    }

    @Override
    public void paint( Graphics g ) {
        Graphics2D g2 = ( Graphics2D ) g;

        if( 0 < ifs.size() ) {
            BufferedImage image = new BufferedImage( TAILLE_X, TAILLE_Y, BufferedImage.TYPE_INT_ARGB );

            Random generateurAleatoire = new Random();
            Point2D.Double point = new Point2D.Double( generateurAleatoire.nextDouble(), generateurAleatoire.nextDouble() );

            ifs.calculerDistribution();

            for( int i = 0; i < NBR_ITERATION; ++ i ) {
                point = ifs.apply( point, generateurAleatoire.nextDouble() );

                if( i > NBR_REJETE ) {
                    int x = (int)( ( Math.max( COIN_SUPERIEUR_GAUCHE.x , Math.min( COIN_INFERIEUR_DROIT.x, point.x ) ) - COIN_SUPERIEUR_GAUCHE.x )
                            *
                            ( TAILLE_X / ( COIN_INFERIEUR_DROIT.x - COIN_SUPERIEUR_GAUCHE.x ) ) );
                    int y = TAILLE_Y - (int)( ( Math.max( COIN_SUPERIEUR_GAUCHE.y , Math.min( COIN_INFERIEUR_DROIT.y, point.y ) ) - COIN_SUPERIEUR_GAUCHE.y )
                            *
                            ( TAILLE_Y / ( COIN_INFERIEUR_DROIT.y - COIN_SUPERIEUR_GAUCHE.y ) ) );
                    if( 0 <= x && x < TAILLE_X && 0 <= y && y < TAILLE_Y ) {
                        image.setRGB( x, y, Color.BLACK.getRGB() );
                    }
                }
            }

            g2.drawImage( image, 0, 0, null );
        }
    }

    @Override
    public void update(Graphics g)
    {
        g.clearRect( 0, 0, TAILLE_X, TAILLE_Y );
        paint( g );
    }
}
