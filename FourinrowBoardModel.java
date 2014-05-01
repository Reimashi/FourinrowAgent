
import jason.environment.grid.GridWorldModel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FourinrowBoardModel extends GridWorldModel {
    /* Tiempo de espera entre jugadas */
    private static final long turnTime = 1200;
    
    /* Variables internas de la clase */
    private static final Logger logger = Logger.getLogger("conecta4.mas2j." + FourinrowBoardModel.class.getName());

    public FourinrowBoardModel(int xgsize, int ygsize, int numagents) {
        super(xgsize, ygsize, 2);
        
        logger.log(Level.INFO, "Se ha instanciado la clase: " + logger.getName());
    }

    public void put(FourinrowChip f, int x) {
        int ycoord = -1;
        
        for (int colindex = this.getHeight() - 1; colindex >= 0; colindex--) {
            if (this.data[x][colindex] == 0) {
                ycoord = colindex;
                break;
            }
        }
        
        if (ycoord != -1) {
            this.set(f.getMask(), x, ycoord);
            
            /* Se espera un tiempo para poder ver las jugadas */
            try {
                Thread.sleep(FourinrowBoardModel.turnTime);
            }
            catch (InterruptedException e) {}
        }
        else {
            logger.log(Level.SEVERE, "Se está intentando insertar una ficha en una columna completa <" + x + ">");
        }
    }
}
