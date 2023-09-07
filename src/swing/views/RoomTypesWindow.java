package swing.views;

import db.DBConnectionManager;
import db.DBGenerator;
import db.DBOperator;
import db.dto.RoomType;
import db.helperstructures.AttributeValuePair;
import swing.viewmodels.RoomTypesVM;

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

public class RoomTypesWindow extends JFrame {
    private RoomTypesVM windowVM;
    private JPanel mainPanel;
    private JTable table;
    private JTextField typeField;
    private JButton addButton;
    private JButton deleteButton;

    public RoomTypesWindow(JFrame parent) {
        createTable();
        addActionListeners();
        windowVM = new RoomTypesVM();
        this.setTitle("Room types");
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
                    RoomType roomTypeToAdd = new RoomType(
                            0,
                            typeField.getText());
                    DBOperator.insertRowInATable("roomtype", roomTypeToAdd.getPairs(), statement);
                    int id = DBOperator.obtainIdOfARow("roomtype", roomTypeToAdd.getPairs(), statement);
                    statement.close();
                    connection.close();
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.addRow(new Object[]{
                            id,
                            roomTypeToAdd.type});
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
                    DBOperator.deleteById("roomtype", id, statement);
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
            numberOfRows = DBOperator.obtainRowsCountFromATable("roomtype", statement);
            resultSet = DBOperator.obtainRowsFromATable("roomtype", null, statement);
            rows = RoomType.toTable(resultSet, numberOfRows);
            statement.close();
            connection.close();
        } catch (SQLException sqlException) {
            return;
        }

        DefaultTableModel model = new DefaultTableModel(rows, RoomType.getFieldsName());
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
                        fieldName = "type";
                    }
                    default -> fieldName = "id";
                }
                try {
                    statement = connection.createStatement();
                    AttributeValuePair pairToModify = new AttributeValuePair(fieldName, (String) model.getValueAt(row, column));
                    DBOperator.updateField("roomtype", (int) model.getValueAt(row, 0), pairToModify, statement);
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException) {
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }
        });
    }
}
