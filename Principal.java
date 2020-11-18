
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Principal extends JPanel implements ActionListener, MouseWheelListener {

    JFrame vent;
    boolean mover = false;
    int e = 0;
    Container cont;
    Figura F;
    JPopupMenu popup;
    JMenuBar BarraM;
    JMenu Trans;
    JMenuItem Escalar, Restaurar, Rotarc, Rotars, Reflexion, Deformar, Trasladar, RestaurarM;
    JToolBar BarraH;
    int max = 0, may = 0, mix = 0, miy = 0;
    double emx = 2.2, emy = 2.2;

    public Principal(String Tit, double fig[][]) {
        addKeyListener(new TecladoListener());
        setFocusable(true);

        vent = new JFrame(Tit);
        cont = vent.getContentPane();
        vent.setSize(900, 700);
        this.setSize(900, 700);
        vent.setResizable(false);
        BarraM = new JMenuBar();
        vent.setJMenuBar(BarraM);
        Trans = new JMenu("Transformaciones");
        BarraM.add(Trans);

        RestaurarM = new JMenuItem("Restaurar");
        RestaurarM.setToolTipText("Restaura la figura a las coordenadas originales");
        RestaurarM.addActionListener(this);
        Trans.add(RestaurarM);

        Restaurar = new JMenuItem("Restaurar");
        Restaurar.setToolTipText("Restaura la figura a las coordenadas originales");
        Restaurar.addActionListener(this);

        Escalar = new JMenuItem("Escalar");
        Escalar.setToolTipText("Escala la figura mostrada");
        Escalar.addActionListener(this);
        Trans.add(Escalar);

        Deformar = new JMenuItem("Deformar");
        Deformar.setToolTipText("Deforma la figura mostrada");
        Deformar.addActionListener(this);
        Trans.add(Deformar);

        Rotarc = new JMenuItem("Rotar en Contra");
        Rotarc.setToolTipText("Rota la figura en contra de la manecillas del reloj");
        Rotarc.addActionListener(this);
        Trans.add(Rotarc);

        Rotars = new JMenuItem("Rotar en el Sentido");
        Rotars.setToolTipText("Rota la figura en el sentido de las manecillas del reloj");
        Rotars.addActionListener(this);
        Trans.add(Rotars);

        Trasladar = new JMenuItem("Trasladar");
        Trasladar.setToolTipText("Traslada la figura mostrada");
        Trasladar.addActionListener(this);
        Trans.add(Trasladar);

        Reflexion = new JMenuItem("Refleja la figura");
        Reflexion.setToolTipText("Rota la figura en el sentido de las manecillas del reloj");
        Reflexion.addActionListener(this);
        Trans.add(Reflexion);



        popup = new JPopupMenu();
        popup.add(Restaurar);

        this.setBackground(Color.WHITE);
        cont.setLayout(new BorderLayout());
        cont.add(this, BorderLayout.CENTER);
        F = new Figura(fig);

        // barra de herramientas
        BarraH = new JToolBar("Transformaciones", JToolBar.HORIZONTAL);
        cont.add(BarraH, BorderLayout.SOUTH);

        //Botón Escalar+
        Action A1 = new AbstractAction("Escalar + ") {
            public void actionPerformed(ActionEvent arg0) {
                F.escalar_punto(1.1, 1.1);
                repaint();
            }
        };
        A1.putValue(Action.SHORT_DESCRIPTION, "Esclar la figura un cantidad especifica");

        //Botón Escalar+
        Action A2 = new AbstractAction("Escalar - ") {
            public void actionPerformed(ActionEvent arg0) {
                F.escalar_punto(.9, .9);
                repaint();
            }
        };
        A2.putValue(Action.SHORT_DESCRIPTION, "Disminuye el tamaño de la figura");

        //Botón Restaurar
        Action A3 = new AbstractAction("Restaurar") {
            public void actionPerformed(ActionEvent arg0) {
                F.restablecer();
                repaint();
            }
        };
        A3.putValue(Action.SHORT_DESCRIPTION, "Restaura a original");

        //botón Rotación en contra del Reloj
        Action A4 = new AbstractAction("Rotación - ") {
            public void actionPerformed(ActionEvent arg0) {
                F.rotar_sp(5);
                repaint();
            }
        };
        A4.putValue(Action.SHORT_DESCRIPTION, "Rota la figura en contra de las manecillas");

        //botón Rotación en Sentido del Reloj
        Action A5 = new AbstractAction("Rotación +") {
            public void actionPerformed(ActionEvent arg0) {
                F.rotar_cp(5);
                repaint();
            }
        };
        A5.putValue(Action.SHORT_DESCRIPTION, "Rota la figura en el sentido de las manecillas");

        //Deformar
        Action A6 = new AbstractAction("Deformar") {
            public void actionPerformed(ActionEvent arg0) {
                F.deformar_punto(-.1, -.1);
                repaint();
            }
        };
        A6.putValue(Action.SHORT_DESCRIPTION, "Deforma la figura cada click");

        // Trasladar Figura
        Action A7 = new AbstractAction("Trasladar") {
            public void actionPerformed(ActionEvent arg0) {
                F.Trasladar(50, 0);
                repaint();
            }
        };
        A7.putValue(Action.SHORT_DESCRIPTION, "Traslada la figura");

        //Reflexión
        Action A8 = new AbstractAction("Reflexion") {
            public void actionPerformed(ActionEvent arg0) {
                Reflex_Dialogo obj = new Reflex_Dialogo(vent, true);
                int vec[] = obj.mostrar();
                F.reflex_punto(vec[0], vec[1]);
                repaint();
            }
        };
        A8.putValue(Action.SHORT_DESCRIPTION, "Reflexion de la figura");

        BarraH.add(A3);
        BarraH.add(A1);
        BarraH.add(A2);
        BarraH.add(A4);
        BarraH.add(A5);
        BarraH.add(A6);
        BarraH.add(A7);
        BarraH.add(A8);

        this.addMouseListener(new RatonListener(this));
        this.addMouseMotionListener(new Arrastre(this));
        this.addMouseWheelListener(this);
        addMouseListener(new MouseAdapter() {

            public void mouseReleased(MouseEvent Me) {
                if (Me.isPopupTrigger()) {
                    popup.show(Me.getComponent(), Me.getX(), Me.getY());
                }
            }
        });
        vent.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vent.setVisible(true);
        vent.setLocationRelativeTo(null);
    }

    public void processMouseEvent(MouseEvent event) {
        if (event.isPopupTrigger()) {
            popup.show(event.getComponent(), event.getX(), event.getY());
        }
        super.processMouseEvent(event);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        F.dibujar(g);
        F.zoom();
    }

    class TecladoListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent ke) {
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            switch (ke.getKeyCode()) {
                case KeyEvent.VK_UP: //Mover hacia arriba
                    F.Trasladar(0, -50);
                    repaint();
                    break;
                case KeyEvent.VK_DOWN://Mover hacia abajo
                    F.Trasladar(0, 50);
                    repaint();
                    break;
                case KeyEvent.VK_LEFT://Mover hacia izquierda
                    F.Trasladar(-50, 0);
                    repaint();
                    break;
                case KeyEvent.VK_RIGHT://Mover hacia derecha
                    F.Trasladar(50, 0);
                    repaint();
                    break;
                case KeyEvent.VK_R://Rotar 45° manecillas del reloj
                    F.rotar_cp(45);
                    repaint();
                    break;
                case KeyEvent.VK_D://Deformar la Figura
                    F.deformar_punto(-.1, -.1);
                    repaint();
                    break;
                case KeyEvent.VK_F://Reflejar Figura
                    F.reflex_punto(-1, 1);
                    repaint();
                    break;
                case KeyEvent.VK_PLUS://Escalar la figura +
                    F.escalar_punto(1.1, 1.1);
                    repaint();
                    break;
                case KeyEvent.VK_MINUS://Escalar la Figura -
                    F.escalar_punto(.9, .9);
                    repaint();
                    break;
                case KeyEvent.VK_SPACE://Restaurar la Figura
                    F.restablecer();
                    repaint();
                    break;

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            repaint();
        }

    }

    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == Restaurar || arg0.getSource() == RestaurarM) {
            F.restablecer();
        } else if (arg0.getSource() == Escalar) {
            String cantx = JOptionPane
                    .showInputDialog("Cantidad a escalar en x");
            String canty = JOptionPane
                    .showInputDialog("Cantidad a escalar en y");
            double cx = 0.0, cy = 0.0;
            try {
                cx = Double.parseDouble(cantx);
                cy = Double.parseDouble(canty);
            } catch (NumberFormatException e) {
                cx = 1.0;
                cy = 1.0;
            }
            F.escalar_H(cx, cy);
        } else if (arg0.getSource() == Deformar) {
            String cantx = JOptionPane
                    .showInputDialog("Cantidad a deformar en x");
            String canty = JOptionPane
                    .showInputDialog("Cantidad a deformar en y");
            double cx = 0.0, cy = 0.0;
            try {
                cx = Double.parseDouble(cantx);
                cy = Double.parseDouble(canty);
            } catch (NumberFormatException e) {
                cx = 0.0;
                cy = 0.0;
            }
            F.deformar_h(cx, cy);
        } else if (arg0.getSource() == Rotarc) {
            String cantx = JOptionPane.showInputDialog("Cantidad a rotar");
            int ang = 0;
            try {
                ang = Integer.parseInt(cantx);
            } catch (NumberFormatException e) {
                ang = 1;
            }
            F.rotacion_sh(ang);
        }
        if (arg0.getSource() == Rotars) {
            String cantx = JOptionPane.showInputDialog("Cantidad a rotar");
            int ang = 0;
            try {
                ang = Integer.parseInt(cantx);
            } catch (NumberFormatException e) {
                ang = 1;
            }
            F.rotacion_ch(ang);
        } else if (arg0.getSource() == Trasladar) {
            String cantx = JOptionPane
                    .showInputDialog("Cantidad a trasladar en x");
            String canty = JOptionPane
                    .showInputDialog("Cantidad a trasladar en y");
            int Tx = 0, Ty = 0;
            try {
                Tx = Integer.parseInt(cantx);
                Ty = Integer.parseInt(canty);
            } catch (NumberFormatException e) {
                Tx = 0;
                Ty = 0;
            }
            F.Trasladar(Tx, Ty);
        } else if (arg0.getSource() == Reflexion) {
            String cantx = JOptionPane
                    .showInputDialog("-1 para Reflexion en x y 1 si no");
            String canty = JOptionPane
                    .showInputDialog("-1 para Reflexion en y y 1 si no");
            int rx = 0, ry = 0;
            try {
                rx = Integer.parseInt(cantx);
                ry = Integer.parseInt(canty);
            } catch (NumberFormatException e) {
                rx = 1;
                ry = 1;
            }
            F.reflex_H(rx, ry);
        }
        repaint();
    }

    public void mouseWheelMoved(MouseWheelEvent arg0) {
        int movimiento = arg0.getWheelRotation();
        if (movimiento < 0) {
            F.escalar_punto(1.1, 1.1);
        } else {
            F.escalar_punto(.9, .9);
        }
        repaint();
    }
}

class RatonListener extends MouseAdapter {

    Principal T;

    public RatonListener(Principal t) {
        T = t;
    }

    public void mouseClicked(MouseEvent me) {
        int nclicks = me.getClickCount();
        if (nclicks >= 2) {
            int rx = me.getX();
            T.F.encminmax();
            if (rx > T.F.maxx) {
                T.F.rotar_cp(5);
            } else if (rx < T.F.minx) {
                T.F.rotar_sp(5);
            }
        }
        T.repaint();
    }

    public void mousePressed(MouseEvent me) {
        int rx = me.getX();
        int ry = me.getY();
        T.F.encminmax();
        if (rx > T.F.minx && rx < T.F.maxx && ry > T.F.miny && ry < T.F.maxy) {
            T.mover = true;
        } else {
            T.mover = false;
        }
    }
}

class Arrastre extends MouseMotionAdapter {

    Principal T;

    public Arrastre(Principal t) {
        T = t;
    }

    public void mouseDragged(MouseEvent me) {
        if (T.mover) {
            int rx = me.getX();
            int ry = me.getY();
            double atx = 0, aty = 0;
            double px = T.F.fig[0][0];
            double py = T.F.fig[0][1];
            double difx = rx - px;
            double dify = ry - py;
            if (rx > T.max || ry > T.may || rx < T.mix || ry < T.miy) {
                T.F.Trasladar(atx, aty);
            } else {

                T.F.Trasladar(difx, dify);
                atx = difx;
                aty = dify;
            }
        }
        T.repaint();
    }
}

class Trasladar_Dialogo extends JDialog {

    JLabel et1, et2;
    JTextField ct1, ct2;
    JButton Ac, Ca;
    int coords[];

    public Trasladar_Dialogo(JFrame V, boolean modo) {
        super(V, modo);
        et1 = new JLabel("X:");
        et2 = new JLabel("Y:");
        ct1 = new JTextField(5);
        ct2 = new JTextField(5);
        Ac = new JButton("Aceptar");
        Ca = new JButton("Cancelar");
        setLayout(new FlowLayout());
        add(et1);
        add(ct1);
        add(et2);
        add(ct2);
        add(Ac);
        add(Ca);
        this.setTitle("Traslada un figura a la coordenada especificada");
        this.setSize(400, 80);
        this.setLocation(85, 480);
        coords = new int[2];
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                coords[0] = 0;
                coords[1] = 0;
                setVisible(false);
                dispose();
            }
        });
        Ac.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                String xc = ct1.getText();
                String yc = ct2.getText();
                try {
                    coords[0] = Integer.parseInt(xc);
                    coords[1] = Integer.parseInt(yc);
                } catch (NumberFormatException e) {
                    coords[0] = 0;
                    coords[1] = 0;
                }
                setVisible(false);
                dispose();
            }
        });
        Ca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                coords[0] = 0;
                coords[1] = 0;
                setVisible(false);
                dispose();
            }
        });
    }

    public int[] mostrar() {
        setVisible(true);
        dispose();
        return coords;
    }
}

class Reflex_Dialogo extends JDialog {

    JRadioButton rx, ry, rxy;
    ButtonGroup grupo;
    JButton Ac, Ca;
    int coords[];

    public Reflex_Dialogo(JFrame V, boolean modo) {
        super(V, modo);
        setLayout(new FlowLayout());
        setSize(400, 85);
        setLocation(90, 450);
        setTitle("Reflexion de la figura de acuerdo a un eje");
        rx = new JRadioButton("X", false);
        ry = new JRadioButton("Y", false);
        rxy = new JRadioButton("XY", false);
        grupo = new ButtonGroup();
        grupo.add(rx);
        grupo.add(ry);
        grupo.add(rxy);
        Ac = new JButton("Aceptar");
        Ca = new JButton("Cancelar");
        add(rx);
        add(ry);
        add(rxy);
        add(Ac);
        add(Ca);
        coords = new int[2];
        this.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent we) {
                coords[0] = 1;
                coords[1] = 1;
                setVisible(false);
                dispose();
            }
        });
        Ac.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                if (rx.isSelected()) {
                    coords[0] = -1;
                    coords[1] = 1;
                } else if (ry.isSelected()) {
                    coords[0] = 1;
                    coords[1] = -1;
                } else if (rxy.isSelected()) {
                    coords[0] = -1;
                    coords[1] = -1;
                } else {
                    coords[0] = 1;
                    coords[1] = 1;
                }
                setVisible(false);
                dispose();
            }
        });
        Ca.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                coords[0] = 1;
                coords[1] = 1;
                setVisible(false);
                dispose();
            }
        });
    }

    public int[] mostrar() {
        setVisible(true);
        dispose();
        return coords;
    }
}
