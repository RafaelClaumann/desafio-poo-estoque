package org.claumann.view;

import org.claumann.config.ConnectionFactory;
import org.claumann.dao.ProductDao;
import org.claumann.dao.ProductDaoImpl;
import org.claumann.model.Product;
import org.claumann.service.ProductService;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertProductDialog extends JDialog {
    Connection connection;

    {
        try {
            connection = ConnectionFactory.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    ProductDao productDao = new ProductDaoImpl(connection);
    ProductService productService = new ProductService(productDao);

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;

    private JLabel codeJLabel;
    private JTextField codeField;

    private JLabel nameJLabel;
    private JTextField nameField;

    private JLabel quantityJLabel;
    private JTextField quantityField;

    public InsertProductDialog() {
        setContentPane(contentPane);
        setModal(true);
        pack();
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        final String code = codeField.getText().trim();
        final String name = nameField.getText().trim();
        int quantity = 0;

        if (code.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(contentPane, "Please enter a valid values.");
            return;
        }

        try {
            quantity = Integer.parseInt(quantityField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(contentPane, "Please enter a valid number.");
            return;
        }

        boolean result = productService.saveProduct(new Product(code, name, quantity));
        if (!result) {
            JOptionPane.showMessageDialog(contentPane, "Erro ao criar produto.");
            return;
        }

        JOptionPane.showMessageDialog(contentPane, "Produto salvo com sucesso!");
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

}
