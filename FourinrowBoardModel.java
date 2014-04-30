
import jason.environment.grid.GridWorldModel;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FourinrowBoardModel extends GridWorldModel {
    private static final Logger logger = Logger.getLogger("conecta4.mas2j." + FourinrowBoardModel.class.getName());

    public FourinrowBoardModel(int xgsize, int ygsize, int numagents) {
        super(xgsize, ygsize, 2);
        
        logger.log(Level.INFO, "Se ha instanciado la clase: " + logger.getName());
    }

    public void put(FourinrowChip f, int x) {
        
        int p = getHeight();

        while (p >= 0 && !isFree(x, p)) {
            p--;
        }

        if (p < 0){
            logger.log(Level.SEVERE, "Se está intentando insertar una ficha en una columna completa");
        }
        else {
            this.add(f.getMask(), x, p);

            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) { }
        }
    }
}
