
import java.awt.FlowLayout;
import java.awt.geom.Point2D;
import java.util.function.UnaryOperator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FonctionLineaire implements UnaryOperator< Point2D.Double > {
    public static final long serialVersionUID = 1L;

    protected double _a;
    protected double _b;
    protected double _c;
    protected double _d;
    protected double _e;
    protected double _f;

    public FonctionLineaire() {
        _a = 0.0;
        _b = 0.0;
        _c = 0.0;
        _d = 0.0;
        _e = 0.0;
        _f = 0.0;
    }

    public void setA(double _a) {
        this._a = _a;
    }

    public void setB(double _b) {
        this._b = _b;
    }

    public void setC(double _c) {
        this._c = _c;
    }

    public void setD(double _d) {
        this._d = _d;
    }

    public void setE(double _e) {
        this._e = _e;
    }

    public void setF(double _f) {
        this._f = _f;
    }

    public FonctionLineaire(double a, double b, double c, double d, double e, double f ) {
        _a = a;
        _b = b;
        _c = c;
        _d = d;
        _e = e;
        _f = f;
    }

    @Override
    public Point2D.Double apply( Point2D.Double point ) {
        return new Point2D.Double( _a * point.x + _b * point.y + _e,
                _c * point.x + _d * point.y + _f );
    }

    public double surface() {
        return Math.abs( _a * _d - _b * _c );
    }

    @Override
    public String toString() {
        return "( " + _a + "x + " + _b + "y + " + _e + ", " + _c + "x + " + _d + "y + " + _f + " )";
    }
}
