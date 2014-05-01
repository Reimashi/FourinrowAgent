
import jason.environment.grid.GridWorldView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FourinrowBoardView extends GridWorldView {
    private static final Logger logger = Logger.getLogger("conecta4.mas2j." + FourinrowBoardView.class.getName());

    private static final String window_title = "Conecta 4";
    private static final int window_width = 800;
    private static final int chip_margin = 3;
    private static final int chip_border = 12;
    
    private int woffset;
    private int hoffset;
    private int wsize;
    private int hsize;
    private int wboxsize;
    private int hboxsize;
    private int wchipsize;
    private int hchipsize;

    public FourinrowBoardView (FourinrowBoardModel model) {
        super(model, window_title, window_width);

        this.defaultFont = new Font("Arial", Font.BOLD, 18);
        
        this.setResizable(false);
        this.setEnabled(true);
        this.setVisible(true);
        this.repaint();

        logger.log(Level.INFO, "Se ha instanciado la clase: " + logger.getName());
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    @Override
    public void paint(Graphics g) {
        /* Calculos de las medidas para el dibujado */
        this.woffset = this.getX();
        this.hoffset = this.getY();
        this.wsize = this.getWidth() - this.woffset;
        this.hsize = this.getHeight() - this.hoffset;
        this.wboxsize = this.wsize / model.getWidth();
        this.hboxsize = this.hsize / model.getHeight();
        this.wchipsize = this.wboxsize - 2 * chip_margin;
        this.hchipsize = this.hboxsize - 2 * chip_margin;
        
        super.paint(g);
    }
    
    @Override
    public void update() {
        super.update();
    }
    
    @Override
    public void update(int x, int y) {
        super.update(x, y);
    }

    @Override
    public void draw(Graphics g, int x, int y, int id) {

        if (id == FourinrowChip.BLUE.getMask()) {
            drawFicha(g, x, y, Color.blue);
        }
        else if (id == FourinrowChip.RED.getMask()) {
            drawFicha(g, x, y, Color.red);
        }
        else {
            super.draw(g, x, y, id);
        }
    }

    @Override
    public void drawAgent(Graphics g, int x, int y, Color c, int id) {
        super.drawAgent(g, x, y, c, id);
    }

    @Override
    public void drawObstacle(Graphics g, int x, int y) {
        super.drawObstacle(g, x, y);
    }

    @Override
    public void drawEmpty(Graphics g, int x, int y) {
        super.drawEmpty(g, x, y);
    }

    public void drawFicha(Graphics g, int x, int y, Color col) {
        g.setColor(col.darker());
        g.fillOval(wboxsize * x, hboxsize * y, wchipsize, hchipsize);
        
        g.setColor(col);
        g.fillOval(wboxsize * x + chip_border, hboxsize * y + chip_border, wchipsize - 2 * chip_border, hchipsize - 2 * chip_border);
        
        g.setColor(Color.BLACK);
        g.drawOval(wboxsize * x, hboxsize * y, wchipsize, hchipsize);
        g.drawOval(wboxsize * x + chip_border, hboxsize * y + chip_border, wchipsize - 2 * chip_border, hchipsize - 2 * chip_border);
    }
}
