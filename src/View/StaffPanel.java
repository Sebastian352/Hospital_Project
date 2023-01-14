package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffPanel extends JDialog {
    private JButton addStaffButton;
    private JButton btnRemoveStaff;
    private JButton cancelButton;
    private JPanel staffPanel;

    public StaffPanel(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(staffPanel);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainForm mainForm = new MainForm(null);
                dispose();
            }
        });
        addStaffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StaffAdder staffAdder = new StaffAdder(null);
                dispose();
            }
        });

        btnRemoveStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StaffRemover staffRemover = new StaffRemover(null);
                dispose();
            }
        });
        setVisible(true);
    }
}
