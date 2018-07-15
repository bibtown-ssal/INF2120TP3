import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.function.BiFunction;

public class IFS extends ArrayList< FonctionLineaire > implements BiFunction< Point2D.Double, Double, Point2D.Double > {
    public static final long serialVersionUID = 1L;

    protected ArrayList< Double > _distribution;

    public IFS() {
        super();
        _distribution = new ArrayList<>();
    }

    public void calculerDistribution() {
        _distribution.clear();

        double somme = 0.0;
        int nbrZero = 0;

        for( FonctionLineaire f : this ) {
            double surface = f.surface();

            _distribution.add( surface );
            somme += surface;

            if( 0.0 == surface ) {
                ++ nbrZero;
            }
        }

        double petit = somme / 100.0;
        somme += ( nbrZero * petit );
        int i = 0;
        double plafond = 0.0;

        for( i = 0; i < size(); ++ i ) {
            plafond += ( ( ( 0.0 == _distribution.get( i ) ? petit : _distribution.get( i ) ) ) / somme );

            _distribution.set( i, plafond );
        }
    }

    public FonctionLineaire get( double position ) {
        int i = 0;

        while( _distribution.get( i ) < position ) ++ i;

        return get( i );
    }

    @Override
    public Point2D.Double apply( Point2D.Double point, Double random ) {
        return get( random ).apply( point );
    }
}
