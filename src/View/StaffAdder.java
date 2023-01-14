package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StaffAdder extends JDialog{
    private JButton cancelButton1;
    private JButton btnAddStaff;
    private JTextField tfFirstName;
    private JTextField tfLastName;
    private JTextField tfSpecialisation;
    private JPanel staffAdder;

    public StaffAdder(JFrame parent) {
        super(parent);
        setTitle("StaffAdder");
        setContentPane(staffAdder);
        setMinimumSize(new Dimension(450, 475));
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        cancelButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                StaffPanel staffPanel = new StaffPanel(null);
                dispose();
            }
        });

        btnAddStaff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = tfFirstName.getText();
                String lastName = tfLastName.getText();
                String specialisation = tfSpecialisation.getText();
                addUserToDatabase(firstName,lastName,specialisation);
            }
        });
        setVisible(true);
    }
    private boolean addUserToDatabase(String firstName, String lastName, String specialisation) {
        final String DB_URL = "jdbc:sqlite:identifier.sqlite";
        try{
            Connection conn = DriverManager.getConnection(DB_URL);
            Statement stmt = conn.createStatement();
            String sql1 = "SELECT COUNT(*) FROM doctors";
            String sql = "INSERT INTO doctors values(?,?,?,?)";
            PreparedStatement preparedStatement1 = conn.prepareStatement(sql1);
            ResultSet resultSet = preparedStatement1.executeQuery();
            int size = Integer.parseInt(resultSet.getString("COUNT(*)"));

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, Integer.toString(size+1));
            preparedStatement.setString(3,firstName);
            preparedStatement.setString(4,lastName);
            preparedStatement.setString(2,specialisation);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(StaffAdder.this,
                    "Congratulations Staff Added",
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
}
