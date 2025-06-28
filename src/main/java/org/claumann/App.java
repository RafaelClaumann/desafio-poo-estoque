package org.claumann;

import org.claumann.view.StockManager;

import javax.swing.*;
import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Gerenciador de Estoque");
            StockManager sm = new StockManager();
            frame.setContentPane(sm.getContentPane());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
