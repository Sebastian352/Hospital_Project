package View;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacientPanel extends JDialog{
    private JButton btnAddPacient;
    private JButton btnRemovePacient;
    private JButton btnCancel;
    private JLabel Label;
    private JPanel PacientPanel;

    public PacientPanel(JFrame parent) {
        super(parent);
        setTitle("Pacient Panel");
        setContentPane(PacientPanel);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                MainForm mainForm = new MainForm(null);
                dispose();
            }
        });
        btnAddPacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PacientAdder pacientAdder = new PacientAdder(null);
                dispose();
            }
        });

        btnRemovePacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PacientRemover pacientRemover = new PacientRemover(null);
                dispose();
            }
        });
        setVisible(true);

    }
}
