package View;

import Model.Doctor;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class StaffRemover extends JDialog{
    private JButton btnRemoveStaff;
    private JButton btnCancel;
    private JComboBox cbStaff;
    private JPanel staffRemover;

    ArrayList<Doctor> doctorArrayList = new ArrayList<>();

    public StaffRemover(JFrame parent) {
        super(parent);
        setTitle("Login");
        setContentPane(staffRemover);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addUsersToComboBox();

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StaffPanel staffPanel = new StaffPanel(null);
                dispose();
            }
        });
        btnRemoveStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String staff = (String) cbStaff.getSelectedItem();
                removeUserFromDatabase(staff.substring(0,1));
            }

        });

        setVisible(true);
    }
    private void addUsersToComboBox(){
        final String DB_URL = "jdbc:sqlite:identifier.sqlite";
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "SELECT d_id,firstname,lastname FROM doctors";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.id = resultSet.getString("d_id");
                doctor.firstName = resultSet.getString("firstname");
                doctor.lastName = resultSet.getString("lastname");
                doctorArrayList.add(doctor);
            }
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        for (Doctor doctor : doctorArrayList) {
            cbStaff.addItem(doctor.id + ' ' + doctor.firstName + ' ' + doctor.lastName);
        }
    }

    private void removeUserFromDatabase(String id) {
        final String DB_URL = "jdbc:sqlite:identifier.sqlite";
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM doctors WHERE d_id = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,id);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(StaffRemover.this,
                    "Congratulations Staff Removed",
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
