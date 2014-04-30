import jason.NoValueForVarException;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FourinrowBoard extends Environment {
    /* Tamaño del tablero */
    private static final int GSize = 8;
    
    /* Nombres de los agentes */
    private static final String JugadorPrimario = "jugadorPrimario";
    private static final String JugadorSecundario = "jugadorSecundario";
    
    /* Comandos */
    private static final String tPut = "put";

    /* Variables internas de la clase */
    private static final Logger logger = Logger.getLogger("conecta4.mas2j." + FourinrowBoard.class.getName());
	
    private final FourinrowBoardModel model;
    private final FourinrowBoardView  view;
    
    public FourinrowBoard() {
        /* Crear un modelo */
        this.model = new FourinrowBoardModel(FourinrowBoard.GSize, FourinrowBoard.GSize, 2);
        
        /* Crear la vista con ese modelo */
        this.view  = new FourinrowBoardView(this.model);
        
        /* Asociar la vista con el modelo */
        this.model.setView(this.view);
        
        this.updatePercepts();
    }
    
    @Override
    public void init(String[] args) {
        this.view.setVisible(true);
    }
	
    @Override
    public boolean executeAction(String ag, Structure action) {
        logger.log(Level.INFO, "El agente <" + ag + "> ejecuta la acción <" + action.getFunctor() + ">");
        
        if (action.getFunctor().equals(FourinrowBoard.tPut)) {
            try {
                int x = (int)((NumberTerm)action.getTerm(0)).solve();

                switch (ag) {
                    case JugadorPrimario:
                        model.put(FourinrowChip.BLUE, x);
                        break;
                    case JugadorSecundario:
                        model.put(FourinrowChip.RED, x);
                        break;
                    default:
                        logger.log(Level.SEVERE, "No se reconoce el nombre del agente <" + ag + ">");
                        return false;
                }
            }
            catch (NoValueForVarException e) {
                logger.log(Level.SEVERE, e.getMessage());
                return false;
            }
        }
        else {
            logger.log(Level.SEVERE, "El agente <" + ag + "> ejecutó la acción no reconocida <" + action.getFunctor() + ">");
            return false;
        }
        
        this.updatePercepts();
            
        /* Se espera un tiempo para evitar errores inesperados */
        try {
            Thread.sleep(200);
        }
        catch (InterruptedException e) {}
        
        /* TODO: Comprobar si el juego ha finalizado */
        
        informAgsEnvironmentChanged();
        
        return true;
    }
    
    private void updatePercepts() {
        this.clearPercepts();
        
        /* TODO: Notificar posicion de las fichas */
    }
    
    public int getSize() {
        return GSize;
    }
}

