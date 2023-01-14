package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends JDialog{
    private JButton btnStaff;
    private JButton btnPacients;
    private JPanel MainForm;
    private JButton btnShutDown;

    public MainForm(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(MainForm);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StaffPanel staffPanel = new StaffPanel(null);
                dispose();
            }
        });

        btnShutDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnPacients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PacientPanel pacientPanel = new PacientPanel(null);
                dispose();
            }
        });
        setVisible(true);

    }
}
