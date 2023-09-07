package swing.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuWindow extends JFrame {
    private JPanel mainPanel;
    private JButton roomTypeButton;
    private JButton departmentButton;
    private JButton floorButton;
    private JButton diagnosticButton;
    private JButton addressButton;
    private JButton specialtyButton;
    private JButton personButton;
    private JButton roomButton;
    private JButton employeeButton;
    private JButton doctorButton;
    private JButton consultationButton;
    private JButton recordButton;
    private JButton surgeryButton;

    public MainMenuWindow(JFrame parent) {
        this.setTitle("Main menu");
        this.setContentPane(mainPanel);
        this.setSize(450, 380);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(parent);
        this.setVisible(true);

        addActionListeners();
    }

    private void nextWindow(WindowName windowName) {
        switch (windowName) {
            case RoomTypes -> {
                RoomTypesWindow nextWindow = new RoomTypesWindow(this);
            }
            case Addresses -> {
                AddressesWindow addressesWindow = new AddressesWindow(this);
            }
            case Departments -> {
                DepartmentsWindow departmentsWindow = new DepartmentsWindow(this);
            }
            case Floors -> {
                FloorsWindow floorsWindow = new FloorsWindow(this);
            }
            case Specialties -> {
                SpecialtiesWindow specialtiesWindow = new SpecialtiesWindow(this);
            }
            case Diagnotics -> {
                DiagnosticsWindow diagnosticsWindow = new DiagnosticsWindow(this);
            }
            default -> {
                JOptionPane.showMessageDialog(this, "Not implemented yet.");
            }
        }
    }

    private void addActionListeners() {
        roomTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.RoomTypes);
            }
        });
        departmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Departments);
            }
        });
        floorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Floors);
            }
        });
        diagnosticButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Diagnotics);
            }
        });
        addressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Addresses);
            }
        });
        specialtyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Specialties);
            }
        });
        personButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Persons);
            }
        });
        roomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Rooms);
            }
        });
        employeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Employees);
            }
        });
        doctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Doctors);
            }
        });
        consultationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Consultations);
            }
        });
        recordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Records);
            }
        });
        surgeryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextWindow(WindowName.Surgeries);
            }
        });
    }

    public enum WindowName {
        RoomTypes,
        Departments,
        Floors,
        Diagnotics,
        Addresses,
        Specialties,
        Persons,
        Rooms,
        Employees,
        Doctors,
        Consultations,
        Records,
        Surgeries
    }
}
