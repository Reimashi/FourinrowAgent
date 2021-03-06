import jason.NoValueForVarException;
import jason.asSyntax.Literal;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FourinrowBoard extends Environment {
    /* Tamaño del tablero */
    private static final int gSize = 8;
    
    /* Nombres de los agentes */
    private static final String JugadorPrimario = "jugadorPrimario";
    private static final String JugadorSecundario = "jugadorSecundario";
    
    /* Comandos */
    private static final String tPut = "put";

    /* Variables internas de la clase */
    private static final Logger logger = Logger.getLogger("conecta4.mas2j." + FourinrowBoard.class.getName());
	
    private final FourinrowBoardModel model;
    private final FourinrowBoardView  view;
    
    private boolean turn;
    private boolean finished;
    private FourinrowChip winner;
    
    public FourinrowBoard() {
        /* Crear un modelo */
        this.model = new FourinrowBoardModel(FourinrowBoard.gSize, FourinrowBoard.gSize, 2);
        
        /* Crear la vista con ese modelo */
        this.view  = new FourinrowBoardView(this.model);
        
        /* Asociar la vista con el modelo */
        this.model.setView(this.view);
        
        this.turn = false;
        this.finished = false;
        this.winner = FourinrowChip.EMPTY;
        
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
                        model.put(FourinrowChip.RED, x);
                        break;
                    case JugadorSecundario:
                        model.put(FourinrowChip.BLUE, x);
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
        
        /* Cambiamos el flag de turno */
        this.turn = !this.turn;
        
        this.updatePercepts();
        
        this.winner = this.model.getWinner();
        if (this.winner != FourinrowChip.EMPTY) {
            this.finished = true;
            System.out.println("Partida finalizada");
        }
        
        informAgsEnvironmentChanged();
        
        return true;
    }
    
    private void updatePercepts() {
        this.clearPercepts();
        
        /* Notificamos las posiciones de las fichas */
        for (int i = 0; i < FourinrowBoard.gSize; i++) {
            for (int j = 0; j < FourinrowBoard.gSize; j++) {
                int object = model.getAgAtPos(i, j);
                
                if (object == FourinrowChip.BLUE.getMask()) {
                    addPercept(Literal.parseLiteral("pos(blue," + i + "," + j + ")"));
                }
                else if (object == FourinrowChip.RED.getMask()) {
                    addPercept(Literal.parseLiteral("pos(red," + i + "," + j + ")"));
                }
            }
        }
        
        /* Notificamos el turno */
        if (this.turn) {
            addPercept(JugadorPrimario, Literal.parseLiteral("turn(true)"));
            addPercept(JugadorSecundario, Literal.parseLiteral("turn(false)"));
        }
        else {
            addPercept(JugadorPrimario, Literal.parseLiteral("turn(false)"));
            addPercept(JugadorSecundario, Literal.parseLiteral("turn(true)"));
        }
        
        /* Notificamos si el juego ha acabado */
        if (this.finished) {
            addPercept(Literal.parseLiteral("finished(true)"));
            if (this.winner == FourinrowChip.RED) {
                addPercept(JugadorPrimario, Literal.parseLiteral("winner(true)"));
                addPercept(JugadorSecundario, Literal.parseLiteral("winner(false)"));
            }
            else if (this.winner == FourinrowChip.BLUE) {
                addPercept(JugadorPrimario, Literal.parseLiteral("winner(false)"));
                addPercept(JugadorSecundario, Literal.parseLiteral("winner(true)"));
            }
        }
        else {
            addPercept(Literal.parseLiteral("finished(false)"));
            addPercept(Literal.parseLiteral("winner(false)"));
        }
    }
    
    public int getSize() {
        return FourinrowBoard.gSize;
    }
}

