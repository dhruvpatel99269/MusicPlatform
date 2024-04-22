/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.decoder.JavaLayerException;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javazoom.jl.player.Player;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author DHRUV
 */
public class Songs extends javax.swing.JFrame {
    
    /**
     * Creates new form Songs*
     */
    private AdvancedPlayer player;
    private Thread playerThread;
    private boolean isPlaying = false;

    public Songs() {
        initComponents();
    }

    private void playSong(int trackId) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/new", "root", "Dhruv@99269!")) {
        String songQuery = "select trackid, song_cover_image, song_title, song_audio, song_artist, song_duration from songs where trackid = ?";

        try (PreparedStatement songStatement = connection.prepareStatement(songQuery)) {
            songStatement.setInt(1, trackId);

            try (ResultSet songResult = songStatement.executeQuery()) {
                if (songResult.next()) {
                    String songTitle = songResult.getString("SONG_TITLE");
                    String songArtist = songResult.getString("SONG_ARTIST");
                    byte[] audioData = songResult.getBytes("SONG_AUDIO");

                    if (isPlaying) {
                        // Stop playback if the same song is clicked again
                        player.close();
                        isPlaying = false;
                    } else {
                        // Stop currently playing song
                        if (isPlaying) {
                            player.close();
                        }

                        // Play the audio directly from MP3 data
                        if (audioData != null) {
                            ByteArrayInputStream audioStream = new ByteArrayInputStream(audioData);
                            player = new AdvancedPlayer(audioStream);

                            playerThread = new Thread(() -> {
                                try {
                                    player.play();
                                } catch (JavaLayerException ex) {
                                    ex.printStackTrace();
                                }
                            });

                            playerThread.start();
                            isPlaying = true;
                            // Display song details
                            songName.setText(songTitle);
                            artistName.setText(songArtist);
                        }
                    }
                }
            }
        }
    } catch (SQLException | JavaLayerException  ex) {
        ex.printStackTrace();
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

        songName = new javax.swing.JLabel();
        artistName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        coverImage = new javax.swing.JLabel();
        addSongbtn = new javax.swing.JButton();
        createplaylistbtn = new javax.swing.JButton();
        playlist1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollBar1 = new javax.swing.JScrollBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        songName.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        songName.setText("SONG NAME");

        artistName.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        artistName.setText("ARTIST");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        jButton1.setText("PLAY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        coverImage.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        coverImage.setText("COVER IMAGE");

        addSongbtn.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        addSongbtn.setText("Add Song");
        addSongbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongbtnActionPerformed(evt);
            }
        });

        createplaylistbtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        createplaylistbtn.setText("Create Playlist");
        createplaylistbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createplaylistbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 141, Short.MAX_VALUE)
                .addComponent(jScrollBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(songName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(artistName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addSongbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(playlist1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(coverImage, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(createplaylistbtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(coverImage, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(createplaylistbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(playlist1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(songName, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(artistName, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addSongbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here: 
        playSong(65);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addSongbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongbtnActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_addSongbtnActionPerformed

    private void createplaylistbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createplaylistbtnActionPerformed
        int userID = 1; // Assuming user ID 1 for demonstration purposes

        // Get the playlist name from user input (you can replace this with your actual input method)
        String playlistName = JOptionPane.showInputDialog(this, "Enter playlist name:");

        if (playlistName != null && !playlistName.isEmpty()) {
            // Add the playlist to the database
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
                String insertQuery = "INSERT INTO playlist (userid, playlist_name) VALUES (?, ?)";

                try (java.sql.PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                    preparedStatement.setInt(1, userID);
                    preparedStatement.setString(2, playlistName);
                    preparedStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(Songs.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Songs.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Playlist name cannot be empty.");
        }
    }//GEN-LAST:event_createplaylistbtnActionPerformed

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
            java.util.logging.Logger.getLogger(Songs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Songs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Songs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Songs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>        

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Songs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSongbtn;
    private javax.swing.JLabel artistName;
    private javax.swing.JLabel coverImage;
    private javax.swing.JButton createplaylistbtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JLabel playlist1;
    private javax.swing.JLabel songName;
    // End of variables declaration//GEN-END:variables

}