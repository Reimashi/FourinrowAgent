
import jason.environment.grid.GridWorldModel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FourinrowBoardModel extends GridWorldModel {
    /* Tiempo de espera entre jugadas */
    private static final long turnTime = 200;
    
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
    
    public FourinrowChip getWinner() {
        /* Comprobamos diagonales descendientes */
        for (int i = 0; i < this.getWidth() - 3; i++) {
            for (int j = 3; j < this.getHeight(); j++) {
                if (this.data[i][j] != FourinrowChip.EMPTY.getMask() && 
                        this.data[i][j] == this.data[i+1][j-1] &&
                        this.data[i][j] == this.data[i+2][j-2] &&
                        this.data[i][j] == this.data[i+3][j-3]) {
                    if (this.data[i][j] == FourinrowChip.RED.getMask()) {
                        return FourinrowChip.RED;
                    }
                    else if (this.data[i][j] == FourinrowChip.BLUE.getMask()) {
                        return FourinrowChip.BLUE;
                    }
                }
            }
        }
        
        /* Comprobamos lineas horizontales */
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 3; j < this.getHeight(); j++) {
                if (this.data[i][j] != FourinrowChip.EMPTY.getMask() && 
                        this.data[i][j] == this.data[i][j-1] &&
                        this.data[i][j] == this.data[i][j-2] &&
                        this.data[i][j] == this.data[i][j-3]) {
                    if (this.data[i][j] == FourinrowChip.RED.getMask()) {
                        return FourinrowChip.RED;
                    }
                    else if (this.data[i][j] == FourinrowChip.BLUE.getMask()) {
                        return FourinrowChip.BLUE;
                    }
                }
            }
        }
        
        /* Comprobamos diagonales ascendientes */
        for (int i = 0; i < this.getWidth() - 3; i++) {
            for (int j = 0; j < this.getHeight() - 3; j++) {
                if (this.data[i][j] != FourinrowChip.EMPTY.getMask() && 
                        this.data[i][j] == this.data[i+1][j+1] &&
                        this.data[i][j] == this.data[i+2][j+2] &&
                        this.data[i][j] == this.data[i+3][j+3]) {
                    if (this.data[i][j] == FourinrowChip.RED.getMask()) {
                        return FourinrowChip.RED;
                    }
                    else if (this.data[i][j] == FourinrowChip.BLUE.getMask()) {
                        return FourinrowChip.BLUE;
                    }
                }
            }
        }
        
        /* Comprobamos lineas verticales */
        for (int i = 0; i < this.getWidth(); i++) {
            for (int j = 0; j < this.getHeight() - 3; j++) {
                if (this.data[i][j] != FourinrowChip.EMPTY.getMask() && 
                        this.data[i][j] == this.data[i][j+1] &&
                        this.data[i][j] == this.data[i][j+2] &&
                        this.data[i][j] == this.data[i][j+3]) {
                    if (this.data[i][j] == FourinrowChip.RED.getMask()) {
                        return FourinrowChip.RED;
                    }
                    else if (this.data[i][j] == FourinrowChip.BLUE.getMask()) {
                        return FourinrowChip.BLUE;
                    }
                }
            }
        }
        
        /* Si no hay fila ganadora, se retorna ficha vacía indicando que no hay ganador */
        return FourinrowChip.EMPTY;
    }
}
