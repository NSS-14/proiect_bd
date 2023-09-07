package swing.views;

import db.DBGenerator;
import swing.viewmodels.StartWindowVM;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow extends JFrame {
    private final StartWindowVM windowVM;
    private JPanel mainPanel;
    private JTextField portField;
    private JTextField userField;
    private JButton connectButton;
    private JPasswordField passwordField;

    public StartWindow() {
        portField.setText("5432");
        userField.setText("postgres");
        windowVM = new StartWindowVM();
        this.setTitle("Hospital Ro Cure");
        this.setContentPane(mainPanel);
        this.setSize(450, 380);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                connect();
            }
        });
    }

    public static void main(String[] args) {
        StartWindow window = new StartWindow();
    }

    private void connect() {
        updateVM();
        DBGenerator dbGenerator = DBGenerator.getInstance();
        dbGenerator.setAll(windowVM.port, windowVM.user, windowVM.password);
        switch (dbGenerator.getState()) {
            case Done ->
                    JOptionPane.showConfirmDialog(this, "The connection was made!", "Done!", JOptionPane.DEFAULT_OPTION);
            case Ready -> {
                int option = JOptionPane.showConfirmDialog(
                        this,
                        "The connection was made, but we can't find a database created yet! Would you like to generate it?",
                        "Ready!",
                        JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    dbGenerator.generateDB();
                }
            }
            case DBConnectionNotEstablished ->
                    JOptionPane.showMessageDialog(this, "Try again!", "DBConnectionNotEstablished!", JOptionPane.ERROR_MESSAGE);

        }
        nextWindow();
    }

    private void updateVM() {
        windowVM.port = portField.getText();
        windowVM.user = userField.getText();
        windowVM.password = String.valueOf(passwordField.getPassword());
    }

    private void nextWindow() {
        if (DBGenerator.getInstance().getState() == DBGenerator.DBGeneratorState.Done) {
            MainMenuWindow nextWindow = new MainMenuWindow(this);
            this.dispose();
        }
    }
}
