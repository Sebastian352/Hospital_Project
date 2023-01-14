package View;

import Model.Doctor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class PacientAdder extends JDialog{
    private JButton btnCancel;
    private JButton btnAddPacient;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfAge;
    private JTextField tfDisease;
    private JComboBox cbDoctor;
    private JPanel pacientAdder;

    ArrayList<Doctor> doctorArrayList = new ArrayList<>();


    public PacientAdder(JFrame parent) {
        super(parent);
        setTitle("Pacient Adder");
        setContentPane(pacientAdder);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            btnCancel.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    PacientPanel pacientPanel = new PacientPanel(null);
                    dispose();
                }
            });
        addUsersToComboBox();
        btnAddPacient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                String doctor_id = cbDoctor.toString();
                String age = tfAge.getText();
                String disease = tfDisease.getText();
                String doctor = (String) cbDoctor.getSelectedItem();

                addUserToDatabase(firstName,lastName,doctor.substring(0,1),age,disease);
            }
        });

        setVisible(true);
    }
    private boolean addUserToDatabase(String firstName, String lastName, String doctor_id, String age, String disease) {
        final String DB_URL = "jdbc:sqlite:identifier.sqlite";
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql1 = "SELECT COUNT(*) FROM pacients";
            String sql = "INSERT INTO pacients values(?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            int size = Integer.parseInt(resultSet.getString("COUNT(*)"));
            Date date = new Date();

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Integer.toString(size+1));
            preparedStatement.setString(2, doctor_id);
            preparedStatement.setString(3, "0");
            preparedStatement.setString(4, disease);
            preparedStatement.setString(5, age);
            preparedStatement.setString(6, date.toString());
            preparedStatement.setString(7, firstName);
            preparedStatement.setString(8, lastName);

            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(PacientAdder.this,
                    "Congratulations Pacient Added",
                    "Congratulations",
                    JOptionPane.INFORMATION_MESSAGE
            );
            stmt.close();
            conn.close();
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
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
            cbDoctor.addItem(doctor.id + ' ' + doctor.firstName + ' ' + doctor.lastName);
        }
    }
}
