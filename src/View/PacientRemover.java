package View;

import Model.Doctor;
import Model.Pacient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class PacientRemover extends JDialog{
    private JButton btnCancel;
    private JButton btnRemovePacient;
    private JPanel PacientRemover;
    private JComboBox cbPacients;
    ArrayList<Pacient> pacientArrayList = new ArrayList<>();


    public PacientRemover(JFrame parent) {
        super(parent);
        setTitle("Pacient Panel");
        setContentPane(PacientRemover);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addUsersToComboBox();

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                PacientPanel pacientPanel = new PacientPanel(null);
                dispose();
            }
        });


        btnRemovePacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pacient = (String) cbPacients.getSelectedItem();
                removeUserFromDatabase(pacient.substring(0,1));
            }
        });
        setVisible(true);

    }
    private void addUsersToComboBox(){
        final String DB_URL = "jdbc:sqlite:identifier.sqlite";
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "SELECT d_id,firstname,lastname FROM pacients";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Pacient pacient = new Pacient();
                pacient.id = resultSet.getString("d_id");
                pacient.firstName = resultSet.getString("firstname");
                pacient.lastName = resultSet.getString("lastname");
                pacientArrayList.add(pacient);
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Pacient pacient : pacientArrayList) {
            cbPacients.addItem(pacient.id + ' ' + pacient.firstName + ' ' + pacient.lastName);
        }
    }
    private void removeUserFromDatabase(String id) {
        final String DB_URL = "jdbc:sqlite:identifier.sqlite";
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM pacients WHERE d_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(PacientRemover.this,
                    "Congratulations Pacients Removed",
                    "Congratulations",
                    JOptionPane.INFORMATION_MESSAGE
            );
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
