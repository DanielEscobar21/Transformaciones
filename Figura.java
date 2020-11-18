
import java.awt.*;

public class Figura {

    double fig[][], figz[][];
    double copia[][];

    public Figura(double f[][]) {
        copia = f;
        fig = new double[f.length][f[0].length];
        restablecer();
    }

    public void restablecer() {
        for (int i = 0; i < copia.length; i++) {
            fig[i][0] = copia[i][0];
            fig[i][1] = copia[i][1];
        }
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < fig.length - 1; i++) {
            g.drawLine((int) fig[i][0], (int) fig[i][1], (int) fig[i + 1][0], (int) fig[i + 1][1]);
        }
    }

    public void zoom() {
        double Sx = (double) (800 - 550) / 800;
        double Sy = (double) (600 - 450) / 600;

        figz = new double[fig.length][fig[0].length];

        for (int i = 0; i < fig.length; i++) {
            figz[i][0] = (550 + fig[i][0] * Sx);
            figz[i][1] = (450 + fig[i][1] * Sy);
        }

    }

    public void escalar(double ex, double ey) {
        for (int i = 0; i < fig.length; i++) {
            fig[i][0] = (ex * fig[i][0]);
            fig[i][1] = (ey * fig[i][1]);
        }
    }

    public void escalar_punto(double ex, double ey) {

        double Tx = fig[0][0], Ty = fig[0][1];

        this.Trasladar(-Tx, -Ty);
        this.escalar(ex, ey);
        this.Trasladar(Tx, Ty);

    }

    public void Deformar(double C, double D) {
        for (int i = 0; i < fig.length; i++) {
            fig[i][0] = (fig[i][0] + C * fig[i][1]);
            fig[i][1] = (D * fig[i][0] + fig[i][1]);
        }

    }

    public void deformar_punto(double C, double D) {
        double Tx = fig[0][0], Ty = fig[0][1];
        this.Trasladar(-Tx, -Ty);
        this.Deformar(C, D);
        this.Trasladar(Tx, Ty);

    }

    public void rotar_contra(int ang) {
        double ang_rad = Math.toRadians(ang);
        double coseno = Math.cos(ang_rad);
        double seno = Math.sin(ang_rad);
        for (int i = 0; i < fig.length; i++) {
            double x = fig[i][0];
            double y = fig[i][1];
            fig[i][0] = (x * coseno - y * seno);
            fig[i][1] = (x * seno + y * coseno);
        }

    }

    public void rotar_cp(int ang) {
        double Tx = fig[0][0], Ty = fig[0][1];
        this.Trasladar(-Tx, -Ty);
        this.rotar_contra(ang);
        this.Trasladar(Tx, Ty);

    }

    public void rotar_sentido(int ang) {
        double ang_rad = Math.toRadians(ang);
        double coseno = Math.cos(ang_rad);
        double seno = Math.sin(ang_rad);
        for (int i = 0; i < fig.length; i++) {
            double x = fig[i][0];
            double y = fig[i][1];
            fig[i][0] = (x * coseno + y * seno);
            fig[i][1] = (-x * seno + y * coseno);
        }

    }

    public void rotar_sp(int ang) {
        double Tx = fig[0][0], Ty = fig[0][1];
        this.Trasladar(-Tx, -Ty);
        this.rotar_sentido(ang);
        this.Trasladar(Tx, Ty);

    }

    public void Trasladar(double Tx, double Ty) {
        for (int i = 0; i < fig.length; i++) {
            fig[i][0] += Tx;
            fig[i][1] += Ty;
        }

    }

    public void reflex(double rx, double ry) {
        for (int i = 0; i < fig.length; i++) {
            fig[i][0] *= rx;
            fig[i][1] *= ry;
        }

    }

    public void reflex_punto(double rx, double ry) {
        double Tx = fig[0][0], Ty = fig[0][1];
        this.Trasladar(-Tx, -Ty);
        this.reflex(rx, ry);
        this.Trasladar(Tx, Ty);
    }

    public void escalar_H(double escx, double escy) {
        double Tx = fig[0][0];
        double Ty = fig[0][1];
        for (int i = 0; i < fig.length; i++) {
            fig[i][0] = (fig[i][0] * escx - Tx * escx + Tx);
            fig[i][1] = (fig[i][1] * escy - Ty * escy + Ty);
        }

    }

    public void reflex_H(double rx, double ry) {
        double Tx = fig[0][0];
        double Ty = fig[0][1];
        for (int i = 0; i < fig.length; i++) {
            fig[i][0] = fig[i][0] * rx - Tx * rx + Tx;
            fig[i][1] = fig[i][1] * ry - Ty * ry + Ty;
        }

    }

    public void rotacion_ch(int ang) {
        double rad = Math.toRadians(ang);
        double seno = Math.sin(rad);
        double coseno = Math.cos(rad);
        double Tx = fig[0][0];
        double Ty = fig[0][1];
        for (int i = 0; i < fig.length; i++) {
            double x = fig[i][0];
            double y = fig[i][1];
            fig[i][0] = (int) (x * coseno - y * seno - Tx * coseno + Ty * seno + Tx);
            fig[i][1] = (int) (x * seno + y * coseno - Tx * seno - Ty * coseno + Ty);
        }

    }

    public void rotacion_sh(int ang) {
        double rad = Math.toRadians(ang);
        double seno = Math.sin(rad);
        double coseno = Math.cos(rad);
        double Tx = fig[0][0];
        double Ty = fig[0][1];
        for (int i = 0; i < fig.length; i++) {
            double x = fig[i][0];
            double y = fig[i][1];
            fig[i][0] = (x * coseno + y * seno - Tx * coseno - Ty * seno + Tx);
            fig[i][1] = (-x * seno + y * coseno + Tx * seno - Ty * coseno + Ty);
        }

    }

    public void deformar_h(double D, double C) {
        double Tx = fig[0][0];
        double Ty = fig[0][1];
        for (int i = 0; i < fig.length; i++) {
            double x = fig[i][0];
            double y = fig[i][1];
            fig[i][0] = (x + y * D - Ty * D);
            fig[i][1] = (x * C + y - Tx * C);
        }

    }
    double minx = 0, miny = 0, maxx = 0, maxy = 0;

    public void encminmax() {
        minx = fig[0][0];
        miny = fig[0][1];
        for (int i = 1; i < fig.length; i++) {
            if (fig[i][0] < minx) {
                minx = fig[i][0];
            }
            if (fig[i][1] < miny) {
                miny = fig[i][1];
            }

        }
        for (int i = 0; i < fig.length; i++) {
            if (fig[i][0] > maxx) {
                maxx = fig[i][0];
            }
            if (fig[i][1] > maxy) {
                maxy = fig[i][1];
            }
        }
    }

}
