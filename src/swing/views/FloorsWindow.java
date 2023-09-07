package swing.views;

import db.DBConnectionManager;
import db.DBGenerator;
import db.DBOperator;
import db.dto.Floor;
import db.helperstructures.AttributeValuePair;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FloorsWindow extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField floorField;
    private JTable table;

    public FloorsWindow(JFrame parent) {
        createTable();
        addActionListeners();
        this.setTitle("Floors");
        this.setContentPane(mainPanel);
        this.setSize(450, 380);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);
    }

    private void addActionListeners() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnectionManager dbcm = DBGenerator.getInstance().getDbcm();
                Connection connection = dbcm.getConnection();
                Statement statement;

                try {
                    statement = connection.createStatement();
                    Floor floorToAdd = new Floor(
                            0,
                            Integer.parseInt(floorField.getText()));
                    DBOperator.insertRowInATable("floor", floorToAdd.getPairs(), statement);
                    int id = DBOperator.obtainIdOfARow("floor", floorToAdd.getPairs(), statement);
                    statement.close();
                    connection.close();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{
                            id,
                            floorToAdd.floor});
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Connection lost!");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DBConnectionManager dbcm = DBGenerator.getInstance().getDbcm();
                Connection connection = dbcm.getConnection();
                Statement statement;

                try {
                    statement = connection.createStatement();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int id = (int) model.getValueAt(table.getSelectedRow(), 0);
                    DBOperator.deleteById("floor", id, statement);
                    statement.close();
                    connection.close();
                    model.removeRow(table.getSelectedRow());
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Connection lost!");
                }
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
            numberOfRows = DBOperator.obtainRowsCountFromATable("floor", statement);
            resultSet = DBOperator.obtainRowsFromATable("floor", null, statement);
            rows = Floor.toTable(resultSet, numberOfRows);
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel(rows, Floor.getFieldsName());
        table.setModel(model);
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                int column = e.getColumn();
                if (column == -1 || column == 0) {
                    return;
                }
                int row = e.getFirstRow();

                DBConnectionManager dbcm = DBGenerator.getInstance().getDbcm();
                Connection connection = dbcm.getConnection();
                Statement statement;

                String fieldName;
                switch (column) {
                    case 1 -> {
                        fieldName = "floor";
                    }
                    default -> fieldName = "id";
                }
                try {
                    statement = connection.createStatement();
                    AttributeValuePair pairToModify = new AttributeValuePair(fieldName, (String) model.getValueAt(row, column));
                    DBOperator.updateField("floor", (int) model.getValueAt(row, 0), pairToModify, statement);
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });
    }
}
