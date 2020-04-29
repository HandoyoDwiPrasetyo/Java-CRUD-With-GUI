import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData extends JFrame {

    Statement statement;
    ResultSet resultSet;
    JButton bUpdate, bBack;
    JLabel lTitle;
    String[][] datas = new String[500][4];
    String[] column = {"Nim","Nama","Alamat","No Telp"};
    JTable tTable;
    JScrollPane scrollPane;

    public UpdateData() throws ClassNotFoundException, SQLException {
        lTitle = new JLabel ("Seluruh Data Mahasiswa");
        lTitle.setFont(new Font("Regular", Font.BOLD, 18));
        lTitle.setForeground(new Color(158, 158, 230));
        bUpdate = new JButton ("Edit");
        bUpdate.setFont(new Font("Regular", Font.PLAIN, 14));
        bUpdate.setBackground(new Color(158, 158, 230));
        bBack = new JButton ("Kembali");
        bBack.setFont(new Font("Regular", Font.PLAIN, 14));
        bBack.setBackground(new Color(158, 158, 230));
        tTable = new JTable(datas, column);
        tTable.setBackground(new Color(158, 158, 230));
        scrollPane = new JScrollPane(tTable);
        scrollPane.setBackground(new Color(158, 158, 230));

        getContentPane().setBackground(new Color(40, 40, 87));

        setTitle("EDIT DATA MAHASISWA");
        setSize (570,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        setLayout(null);
        add(lTitle);
        add(bUpdate);
        add(bBack);
        add(scrollPane);

        lTitle.setBounds(160, 30, 300, 30);
        scrollPane.setBounds(70, 70, 400, 400);
        bUpdate.setBounds(170, 490, 90, 25);
        bBack.setBounds(280, 490, 90, 25);

        DBConnection connec = new DBConnection();
        statement = connec.getConnection().createStatement();
        String sql = "SELECT *FROM tb_mahasiswa";
        resultSet = statement.executeQuery(sql);
        int row = 0;
        while (resultSet.next()){
            datas[row][0] = resultSet.getString("nama");
            datas[row][1] = resultSet.getString("nim");
            datas[row][2] = resultSet.getString("alamat");
            datas[row][3] = resultSet.getString("no_telp");
            row++;
        }
        statement.close();
        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Main();
            }
        });
        bUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                setVisible(false);
                new ProcessUpdate();
            }
        });


    }

}