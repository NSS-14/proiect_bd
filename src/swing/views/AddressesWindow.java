package swing.views;

import db.DBConnectionManager;
import db.DBGenerator;
import db.DBOperator;
import db.dto.Address;
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

public class AddressesWindow extends JFrame {
    private JPanel mainPanel;
    private JTable table;
    private JButton deleteButton;
    private JButton addButton;
    private JTextField countryField;
    private JTextField countyField;
    private JTextField cityField;
    private JTextField streetField;
    private JTextField numberField;
    private JTextField buildingField;
    private JTextField stairField;
    private JTextField apartmentField;

    public AddressesWindow(JFrame parent) {
        createTable();
        addActionListeners();
        this.setTitle("Addresses");
        this.setContentPane(mainPanel);
        this.pack();
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
                    Address addressToAdd = new Address(
                            0,
                            countryField.getText(),
                            countyField.getText(),
                            cityField.getText(),
                            streetField.getText(),
                            numberField.getText(),
                            buildingField.getText(),
                            stairField.getText(),
                            apartmentField.getText());
                    DBOperator.insertRowInATable("address", addressToAdd.getPairs(), statement);
                    int id = DBOperator.obtainIdOfARow("address", addressToAdd.getPairs(), statement);
                    statement.close();
                    connection.close();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{
                            id,
                            addressToAdd.country,
                            addressToAdd.county,
                            addressToAdd.city,
                            addressToAdd.street,
                            addressToAdd.number,
                            addressToAdd.building,
                            addressToAdd.stair,
                            addressToAdd.apartment});
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
                    DBOperator.deleteById("address", id, statement);
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
            numberOfRows = DBOperator.obtainRowsCountFromATable("address", statement);
            resultSet = DBOperator.obtainRowsFromATable("address", null, statement);
            rows = Address.toTable(resultSet, numberOfRows);
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel(rows, Address.getFieldsName());
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
                        fieldName = "country";
                    }
                    case 2 -> {
                        fieldName = "county";
                    }
                    case 3 -> {
                        fieldName = "city";
                    }
                    case 4 -> {
                        fieldName = "street";
                    }
                    case 5 -> {
                        fieldName = "number";
                    }
                    case 6 -> {
                        fieldName = "building";
                    }
                    case 7 -> {
                        fieldName = "stair";
                    }
                    case 8 -> {
                        fieldName = "apartment";
                    }
                    default -> fieldName = "stair";
                }
                try {
                    statement = connection.createStatement();
                    AttributeValuePair pairToModify = new AttributeValuePair(fieldName, (String) model.getValueAt(row, column));
                    DBOperator.updateField("address", (int) model.getValueAt(row, 0), pairToModify, statement);
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });
    }
}
