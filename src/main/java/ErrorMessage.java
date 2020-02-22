
import javax.swing.*;

class ErrorMessage {

    static void showErrorMessage(Exception e) {
        String message = e.getClass() + "\n" + e.getMessage();
        JOptionPane.showMessageDialog(MainWindow.getGameFormFrame(), message);
    }

}
