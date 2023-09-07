package swing.views;

import db.DBConnectionManager;
import db.DBGenerator;
import db.DBOperator;
import db.dto.Diagnostic;
import db.helperstructures.AttributeValuePair;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DiagnosticsWindow extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField nameField;
    private JTable table;

    public DiagnosticsWindow(JFrame parent) {
        createTable();
        addActionListeners();
        this.setTitle("Diagnostics");
        this.setContentPane(mainPanel);
        this.setSize(450, 380);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void addActionListeners() {
        addButton.addActionListener(e -> {
            DBConnectionManager dbcm = DBGenerator.getInstance().getDbcm();
            Connection connection = dbcm.getConnection();
            Statement statement;

            try {
                statement = connection.createStatement();
                Diagnostic diagnosticToAdd = new Diagnostic(
                        0,
                        nameField.getText());
                DBOperator.insertRowInATable("diagnostic", diagnosticToAdd.getPairs(), statement);
                int id = DBOperator.obtainIdOfARow("diagnostic", diagnosticToAdd.getPairs(), statement);
                statement.close();
                connection.close();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{
                        id,
                        diagnosticToAdd.name});
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Connection lost!");
            }
        });
        deleteButton.addActionListener(e -> {
            DBConnectionManager dbcm = DBGenerator.getInstance().getDbcm();
            Connection connection = dbcm.getConnection();
            Statement statement;

            try {
                statement = connection.createStatement();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int id = (int) model.getValueAt(table.getSelectedRow(), 0);
                DBOperator.deleteById("diagnostic", id, statement);
                statement.close();
                connection.close();
                model.removeRow(table.getSelectedRow());
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Connection lost!");
            }
        });
    }

    private void createTable() {
        DBConnectionManager dbcm = DBGenerator.getInstance().getDbcm();
        Connection connection = dbcm.getConnection();
        Statement statement;
        int numberOfRows;
        ResultSet resultSet;

        Object[][] rows;
        try {
            statement = connection.createStatement();
            numberOfRows = DBOperator.obtainRowsCountFromATable("diagnostic", statement);
            resultSet = DBOperator.obtainRowsFromATable("diagnostic", null, statement);
            rows = Diagnostic.toTable(resultSet, numberOfRows);
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel(rows, Diagnostic.getFieldsName());
        table.setModel(model);
        model.addTableModelListener(e -> {
            int column = e.getColumn();
            if (column == -1 || column == 0) {
                return;
            }
            int row = e.getFirstRow();

            DBConnectionManager dbcm1 = DBGenerator.getInstance().getDbcm();
            Connection connection1 = dbcm1.getConnection();
            Statement statement1;

            String fieldName;
            switch (column) {
                case 1 -> {
                    fieldName = "name";
                }
                default -> fieldName = "id";
            }
            try {
                statement1 = connection1.createStatement();
                AttributeValuePair pairToModify = new AttributeValuePair(fieldName, (String) model.getValueAt(row, column));
                DBOperator.updateField("diagnostic", (int) model.getValueAt(row, 0), pairToModify, statement1);
                statement1.close();
                connection1.close();
            } catch (SQLException sqlException) {
                JOptionPane.showMessageDialog(null, "Error");
            }
        });
    }
}
