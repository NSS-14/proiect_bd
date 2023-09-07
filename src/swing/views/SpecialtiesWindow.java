package swing.views;

import db.DBConnectionManager;
import db.DBGenerator;
import db.DBOperator;
import db.dto.Specialty;
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

public class SpecialtiesWindow extends JFrame {
    private JPanel mainPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JTextField nameField;
    private JTable table;

    public SpecialtiesWindow(JFrame parent) {
        createTable();
        addActionListeners();
        this.setTitle("Specialties");
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
                    Specialty specialtyToAdd = new Specialty(
                            0,
                            nameField.getText());
                    DBOperator.insertRowInATable("specialty", specialtyToAdd.getPairs(), statement);
                    int id = DBOperator.obtainIdOfARow("specialty", specialtyToAdd.getPairs(), statement);
                    statement.close();
                    connection.close();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{
                            id,
                            specialtyToAdd.name});
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
                    DBOperator.deleteById("specialty", id, statement);
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
            numberOfRows = DBOperator.obtainRowsCountFromATable("specialty", statement);
            resultSet = DBOperator.obtainRowsFromATable("specialty", null, statement);
            rows = Specialty.toTable(resultSet, numberOfRows);
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel(rows, Specialty.getFieldsName());
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
                        fieldName = "name";
                    }
                    default -> fieldName = "id";
                }
                try {
                    statement = connection.createStatement();
                    AttributeValuePair pairToModify = new AttributeValuePair(fieldName, (String) model.getValueAt(row, column));
                    DBOperator.updateField("specialty", (int) model.getValueAt(row, 0), pairToModify, statement);
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });
    }
}
