/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;

/**
 *
 * @author DHRUV
 */
public class Profile extends javax.swing.JFrame {

    int userId = UserData.getUserId();

    /**
     * Creates new form Profile
     */
    public Profile() {
        initComponents();
        fetchUserDetails(userId); // Call the method to fetch user details
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void fetchUserDetails(int userId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish a connection to your database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!");

            // Prepare the SQL statement to fetch user details
            String sql = "SELECT * FROM user WHERE id = ?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);

            // Execute the query
            resultSet = statement.executeQuery();

            // Process the result set
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                // Other user details can be retrieved similarly

                // Update the UI with the fetched user details
                extusername.setText(username);
                extemail.setText(email);
                // Update other UI components as needed
            } else {
                // Handle case when user with the given ID is not found
                System.out.println("User not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        homeicon = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        welcomLbl = new javax.swing.JLabel();
        planDetailsLbl = new javax.swing.JLabel();
        planTypeLbl = new javax.swing.JLabel();
        plantype = new javax.swing.JLabel();
        accDetailsLbl = new javax.swing.JLabel();
        usernamelbl = new javax.swing.JLabel();
        planAmtLbl = new javax.swing.JLabel();
        planamt = new javax.swing.JLabel();
        extusername = new javax.swing.JLabel();
        emaillbl = new javax.swing.JLabel();
        extemail = new javax.swing.JLabel();
        removeAccountBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));

        jPanel2.setBackground(new java.awt.Color(20, 20, 20));

        jPanel7.setBackground(new java.awt.Color(25, 25, 25));

        homeicon.setBackground(new java.awt.Color(0, 0, 0));
        homeicon.setForeground(new java.awt.Color(255, 255, 255));
        homeicon.setText("home");
        homeicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                homeiconMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(25, 25, 25));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 451, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(20, 20, 20));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel9.setBackground(new java.awt.Color(20, 20, 20));

        welcomLbl.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        welcomLbl.setForeground(new java.awt.Color(255, 255, 255));
        welcomLbl.setText("Welcome User");

        planDetailsLbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        planDetailsLbl.setForeground(new java.awt.Color(255, 255, 255));
        planDetailsLbl.setText("Subscription Details");

        planTypeLbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        planTypeLbl.setForeground(new java.awt.Color(255, 255, 255));
        planTypeLbl.setText("Plan Type");

        plantype.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        plantype.setForeground(new java.awt.Color(255, 255, 255));
        plantype.setText("Platinum");

        accDetailsLbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        accDetailsLbl.setForeground(new java.awt.Color(255, 255, 255));
        accDetailsLbl.setText("Account Details");

        usernamelbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        usernamelbl.setForeground(new java.awt.Color(255, 255, 255));
        usernamelbl.setText("Username");

        planAmtLbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        planAmtLbl.setForeground(new java.awt.Color(255, 255, 255));
        planAmtLbl.setText("Amount Paid");

        planamt.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        planamt.setForeground(new java.awt.Color(255, 255, 255));
        planamt.setText("500.00");

        extusername.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        extusername.setForeground(new java.awt.Color(255, 255, 255));
        extusername.setText("Dhruv");

        emaillbl.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        emaillbl.setForeground(new java.awt.Color(255, 255, 255));
        emaillbl.setText("Email");

        extemail.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        extemail.setForeground(new java.awt.Color(255, 255, 255));
        extemail.setText("dhruv@gmail.com");

        removeAccountBtn.setBackground(new java.awt.Color(30, 30, 30));
        removeAccountBtn.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        removeAccountBtn.setForeground(new java.awt.Color(153, 0, 0));
        removeAccountBtn.setText("Remove Account");
        removeAccountBtn.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        removeAccountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeAccountBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(usernamelbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(extusername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(accDetailsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(planTypeLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(plantype, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(planDetailsLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(welcomLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(planAmtLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(planamt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel9Layout.createSequentialGroup()
                            .addComponent(emaillbl, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(extemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(removeAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(welcomLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(planDetailsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(planTypeLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(plantype, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(planamt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(planAmtLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(accDetailsLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(extusername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernamelbl, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(extemail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(emaillbl, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(removeAccountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(964, 657));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void removeAccountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeAccountBtnActionPerformed
        // TODO add your handling code here:        

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            String checkPlaylistRacksQuery = "SELECT COUNT(*) AS count FROM playlistracks WHERE userid = ?";
            try (PreparedStatement checkPlaylistRacksStatement = connection.prepareStatement(checkPlaylistRacksQuery)) {
                checkPlaylistRacksStatement.setInt(1, userId);
                try (ResultSet playlistRacksResultSet = checkPlaylistRacksStatement.executeQuery()) {
                    if (playlistRacksResultSet.next()) {
                        int playlistRacksCount = playlistRacksResultSet.getInt("count");
                        if (playlistRacksCount > 0) {
                            // User ID exists in the playlistracks table, proceed with deletion
                            String deletePlaylistRacksQuery = "DELETE FROM playlistracks WHERE userid = ?";
                            try (PreparedStatement deletePlaylistRacksStatement = connection.prepareStatement(deletePlaylistRacksQuery)) {
                                deletePlaylistRacksStatement.setInt(1, userId);
                                int playlistRacksRowsAffected = deletePlaylistRacksStatement.executeUpdate();
                                if (playlistRacksRowsAffected > 0) {
                                    System.out.println("User ID " + userId + " deleted from playlistracks table.");
                                    // Optionally, perform any additional actions after deletion
                                } else {
                                    System.out.println("Failed to delete user ID " + userId + " from playlistracks table.");
                                    // Optionally, display an error message or handle accordingly
                                }
                            } catch (SQLException ex) {
                                System.err.println("Error executing delete playlistracks query: " + ex.getMessage());
                            }
                        } else {
                            System.out.println("User ID " + userId + " does not exist in the playlistracks table.");
                            // Optionally, display a message indicating that the user ID doesn't exist
                        }
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error executing check playlistracks query: " + ex.getMessage());
            }

            // Check if the user ID exists in the playlist table
            String checkPlaylistQuery = "SELECT COUNT(*) AS count FROM playlists WHERE uid = ?";
            try (PreparedStatement checkPlaylistStatement = connection.prepareStatement(checkPlaylistQuery)) {
                checkPlaylistStatement.setInt(1, userId);
                try (ResultSet playlistResultSet = checkPlaylistStatement.executeQuery()) {
                    if (playlistResultSet.next()) {
                        int playlistCount = playlistResultSet.getInt("count");
                        if (playlistCount > 0) {
                            // User ID exists in the playlist table, proceed with deletion
                            String deletePlaylistQuery = "DELETE FROM playlists WHERE uid = ?";
                            try (PreparedStatement deletePlaylistStatement = connection.prepareStatement(deletePlaylistQuery)) {
                                deletePlaylistStatement.setInt(1, userId);
                                int playlistRowsAffected = deletePlaylistStatement.executeUpdate();
                                if (playlistRowsAffected > 0) {
                                    System.out.println("User ID " + userId + " deleted from playlist table.");
                                    // Optionally, perform any additional actions after deletion
                                } else {
                                    System.out.println("Failed to delete user ID " + userId + " from playlist table.");
                                    // Optionally, display an error message or handle accordingly
                                }
                            } catch (SQLException ex) {
                                System.err.println("Error executing delete playlist query: " + ex.getMessage());
                            }
                        } else {
                            System.out.println("User ID " + userId + " does not exist in the playlist table.");
                            // Optionally, display a message indicating that the user ID doesn't exist
                        }
                    }
                }
            } catch (SQLException ex) {
                System.err.println("Error executing check playlist query: " + ex.getMessage());
            }

            // Repeat the above process for the playlistracks table and user table
            // Check if the user ID exists in the user table
            String checkUserQuery = "SELECT COUNT(*) AS count FROM user WHERE id = ?";
            try (PreparedStatement checkUserStatement = connection.prepareStatement(checkUserQuery)) {
                checkUserStatement.setInt(1, userId);
                try (ResultSet userResultSet = checkUserStatement.executeQuery()) {
                    if (userResultSet.next()) {
                        int userCount = userResultSet.getInt("count");
                        if (userCount > 0) {
                            // User ID exists in the user table, proceed with deletion
                            String deleteUserQuery = "DELETE FROM user WHERE id = ?";
                            try (PreparedStatement deleteUserStatement = connection.prepareStatement(deleteUserQuery)) {
                                deleteUserStatement.setInt(1, userId);
                                int userRowsAffected = deleteUserStatement.executeUpdate();
                                if (userRowsAffected > 0) {
                                    System.out.println("User ID " + userId + " deleted from user table.");
                                    // Optionally, perform any additional actions after deletion
                                    dispose();
                                    SignUp ads=new SignUp();
                                    ads.setVisible(true);
                                } else {
                                    System.out.println("Failed to delete user ID " + userId + " from user table.");
                                    // Optionally, display an error message or handle accordingly
                                }
                            } catch (SQLException ex) {
                                System.err.println("Error executing delete user query: " + ex.getMessage());
                            }
                        } else {
                            System.out.println("User ID " + userId + " does not exist in the user table.");
                            // Optionally, display a message indicating that the user ID doesn't exist
                        }
                    }
                }
                
                
            } catch (SQLException ex) {
                System.err.println("Error executing check user query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }
    }//GEN-LAST:event_removeAccountBtnActionPerformed

    private void homeiconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconMouseReleased
        // TODO add your handling code here:
        dispose();
        Dashboard ads = new Dashboard();
        ads.setVisible(true);
    }//GEN-LAST:event_homeiconMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Profile.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Profile().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accDetailsLbl;
    private javax.swing.JLabel emaillbl;
    private javax.swing.JLabel extemail;
    private javax.swing.JLabel extusername;
    private javax.swing.JLabel homeicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel planAmtLbl;
    private javax.swing.JLabel planDetailsLbl;
    private javax.swing.JLabel planTypeLbl;
    private javax.swing.JLabel planamt;
    private javax.swing.JLabel plantype;
    private javax.swing.JButton removeAccountBtn;
    private javax.swing.JLabel usernamelbl;
    private javax.swing.JLabel welcomLbl;
    // End of variables declaration//GEN-END:variables
}
