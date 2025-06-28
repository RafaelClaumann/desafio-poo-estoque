package org.claumann.view;

import javax.swing.JButton;
import javax.swing.JPanel;


public class StockManager {
    private JPanel contentPane;
    private JButton saveProduct;
    private JButton excluirProdutoButton;
    private JButton listarProdutosButton;
    private JButton modificarProdutoButton;

    public StockManager() {
        saveProduct.addActionListener(actionEvent -> {
            InsertProductDialog dialog = new InsertProductDialog();
            dialog.setLocationRelativeTo(contentPane);
            dialog.setVisible(true);
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public JPanel getContentPane() {
        return contentPane;
    }

}
