
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TestPaint {
    private static final FourinrowBoardModel m = new FourinrowBoardModel(8,8,2);
    private static final FourinrowBoardView v = new FourinrowBoardView(m);
    
    public TestPaint () {
        v.setVisible(true);
    }
    
    public static void main(String args[])
    {
        TestPaint main = new TestPaint();
        
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());
        handler.setLevel(Level.ALL);
        Logger.getLogger("conecta4.mas2j." + FourinrowBoardModel.class.getName()).addHandler(handler);
        Logger.getLogger("conecta4.mas2j." + FourinrowBoardView.class.getName()).addHandler(handler);
        Logger.getLogger("conecta4.mas2j." + FourinrowBoard.class.getName()).addHandler(handler);
        
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TestPaint.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        m.put(FourinrowChip.RED, 3);
        
        m.put(FourinrowChip.BLUE, 4);
        
        m.put(FourinrowChip.BLUE, 3);
        
        m.put(FourinrowChip.RED, 4);
        
        m.put(FourinrowChip.BLUE, 5);
        m.put(FourinrowChip.BLUE, 5);
        m.put(FourinrowChip.RED, 5);
        m.put(FourinrowChip.BLUE, 6);
        m.put(FourinrowChip.BLUE, 6);
        m.put(FourinrowChip.BLUE, 6);
        m.put(FourinrowChip.RED, 6);
    }
}