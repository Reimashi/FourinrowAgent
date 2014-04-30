
import jason.environment.grid.GridWorldView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FourinrowBoardView extends GridWorldView {
    private static final Logger logger = Logger.getLogger("conecta4.mas2j." + FourinrowBoardView.class.getName());

    private static final String window_title = "Conecta 4";
    private static final int window_width = 800;
    private static final int chip_margin = 3;
    private static final int chip_border = 5;
    
    private final int wsize;
    private final int hsize;

    public FourinrowBoardView (FourinrowBoardModel model) {
        super(model, window_title, window_width);

        this.defaultFont = new Font("Arial", Font.BOLD, 18);
        
        this.wsize = window_width / model.getWidth();
        this.hsize = window_width / model.getHeight();
                
        this.setEnabled(true);
        this.setVisible(true);
        this.repaint();

        logger.log(Level.INFO, "Se ha instanciado la clase: " + logger.getName());
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
        g.fillOval(this.wsize * x + chip_margin, 
                this.hsize * y + chip_margin, 
                this.wsize - (chip_margin * 2), 
                this.hsize - (chip_margin * 2));
        
        g.setColor(col);
        g.fillOval(this.wsize * x + chip_margin + chip_border, 
                this.hsize * y + chip_margin + chip_border, 
                this.wsize - (chip_margin * 2) - (chip_border * 2), 
                this.hsize - (chip_margin * 2) - (chip_border * 2));
        
        g.setColor(Color.BLACK);
        g.drawOval(this.wsize * x + chip_margin, 
                this.hsize * y + chip_margin, 
                this.wsize - (chip_margin * 2), 
                this.hsize - (chip_margin * 2));
    }
}
