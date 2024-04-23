/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author DHRUV
 */
public class AllSongs extends javax.swing.JFrame {

    int userid = UserData.getUserId();

    /**
     * Creates new form tryclass
     */
    public AllSongs() {
        initComponents();
        loadSongs();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void addSongToPlaylist() {
        int playlistid = PlaylistData.getPlaylistId();        
        int trackid = PlaylistData.getTrackId();

        boolean entryExists = false;

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            // Check if the entry already exists in playlistracks
            String checkEntryQuery = "SELECT COUNT(*) AS count FROM playlistracks WHERE playlistid = ? AND userid = ? AND trackid = ?";
            try (java.sql.PreparedStatement entryStatement = connection.prepareStatement(checkEntryQuery)) {
                entryStatement.setInt(1, playlistid);
                entryStatement.setInt(2, userid);
                entryStatement.setInt(3, trackid);
                java.sql.ResultSet entryResultSet = entryStatement.executeQuery();
                if (entryResultSet.next()) {
                    int count = entryResultSet.getInt("count");
                    entryExists = count > 0;
                }
            } catch (SQLException ex) {
                System.err.println("Error checking entry existence: " + ex.getMessage());
            }

            if (entryExists) {
                 JOptionPane.showMessageDialog(null, "Entry already exists in playlistracks.", "Info", JOptionPane.INFORMATION_MESSAGE);
                return; // Exit the method or handle the error as needed
            }

            // Proceed to insert into playlistracks
            String insertPlaylistRacksQuery = "INSERT INTO playlistracks (trackid, playlistid, userid) VALUES (?, ?, ?)";
            try (java.sql.PreparedStatement insertStatement = connection.prepareStatement(insertPlaylistRacksQuery)) {
                insertStatement.setInt(1, trackid);
                insertStatement.setInt(2, playlistid);
                insertStatement.setInt(3, userid);
                int rowsAffected = insertStatement.executeUpdate();
                if (rowsAffected > 0) {
                     JOptionPane.showMessageDialog(null, "Track added to playlist successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                     JOptionPane.showMessageDialog(null, "Failed to add track to playlist.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(null, "Error inserting into playlist: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadSongs() {
        String playlistTracksQuery = "SELECT song_cover_image, song_title, song_artist, duration FROM tracks LIMIT 25" + ";";

        //String playlistTracksQuery = "SELECT song_cover_image,song_title, song_artist,duration from tracks where trackid=1";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistTracksQuery)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                int trackCount = 0;
                while (resultSet.next() && trackCount < 25) { // Assuming you want to display up to 4 tracks                    
                    String songName = resultSet.getString("song_title");
                    String songArtist = resultSet.getString("song_artist");
                    int songDuration = resultSet.getInt("duration");
                    byte[] imageBytes1 = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon1 = new ImageIcon(imageBytes1);
                    Image image1 = imageIcon1.getImage();
                    Image scaledImage = image1.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
                    switch (trackCount) {
                        case 0:
                            sc2.setIcon(new ImageIcon(scaledImage));
                            sn2.setText(songName);
                            sa2.setText(songArtist);
                            st2.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 1:
                            sc3.setIcon(new ImageIcon(scaledImage));
                            sn3.setText(songName);
                            sa3.setText(songArtist);
                            st3.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 2:
                            sc4.setIcon(new ImageIcon(scaledImage));
                            sn4.setText(songName);
                            sa4.setText(songArtist);
                            st4.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 3:
                            sc5.setIcon(new ImageIcon(scaledImage));
                            sn5.setText(songName);
                            sa5.setText(songArtist);
                            st5.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 4:
                            sc6.setIcon(new ImageIcon(scaledImage));
                            sn6.setText(songName);
                            sa6.setText(songArtist);
                            st6.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 5:
                            sc7.setIcon(new ImageIcon(scaledImage));
                            sn7.setText(songName);
                            sa7.setText(songArtist);
                            st7.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 6:
                            sc8.setIcon(new ImageIcon(scaledImage));
                            sn8.setText(songName);
                            sa8.setText(songArtist);
                            st8.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 7:
                            sc9.setIcon(new ImageIcon(scaledImage));
                            sn9.setText(songName);
                            sa9.setText(songArtist);
                            st9.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 8:
                            sc10.setIcon(new ImageIcon(scaledImage));
                            sn10.setText(songName);
                            sa10.setText(songArtist);
                            st10.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 9:
                            sc11.setIcon(new ImageIcon(scaledImage));
                            sn11.setText(songName);
                            sa11.setText(songArtist);
                            st11.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 10:
                            sc12.setIcon(new ImageIcon(scaledImage));
                            sn12.setText(songName);
                            sa12.setText(songArtist);
                            st12.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 11:
                            sc13.setIcon(new ImageIcon(scaledImage));
                            sn13.setText(songName);
                            sa13.setText(songArtist);
                            st13.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 12:
                            sc14.setIcon(new ImageIcon(scaledImage));
                            sn14.setText(songName);
                            sa14.setText(songArtist);
                            st14.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 13:
                            sc15.setIcon(new ImageIcon(scaledImage));
                            sn15.setText(songName);
                            sa15.setText(songArtist);
                            st15.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 14:
                            sc16.setIcon(new ImageIcon(scaledImage));
                            sn16.setText(songName);
                            sa16.setText(songArtist);
                            st16.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 15:
                            sc17.setIcon(new ImageIcon(scaledImage));
                            sn17.setText(songName);
                            sa17.setText(songArtist);
                            st17.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 16:
                            sc18.setIcon(new ImageIcon(scaledImage));
                            sn18.setText(songName);
                            sa18.setText(songArtist);
                            st18.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 17:
                            sc19.setIcon(new ImageIcon(scaledImage));
                            sn19.setText(songName);
                            sa19.setText(songArtist);
                            st19.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 18:
                            sc20.setIcon(new ImageIcon(scaledImage));
                            sn20.setText(songName);
                            sa20.setText(songArtist);
                            st20.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 19:
                            sc21.setIcon(new ImageIcon(scaledImage));
                            sn21.setText(songName);
                            sa21.setText(songArtist);
                            st21.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 20:
                            sc22.setIcon(new ImageIcon(scaledImage));
                            sn22.setText(songName);
                            sa22.setText(songArtist);
                            st22.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 21:
                            sc23.setIcon(new ImageIcon(scaledImage));
                            sn23.setText(songName);
                            sa23.setText(songArtist);
                            st23.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 22:
                            sc24.setIcon(new ImageIcon(scaledImage));
                            sn24.setText(songName);
                            sa24.setText(songArtist);
                            st24.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 23:
                            sc25.setIcon(new ImageIcon(scaledImage));
                            sn25.setText(songName);
                            sa25.setText(songArtist);
                            st25.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 24:
                            sc26.setIcon(new ImageIcon(scaledImage));
                            sn26.setText(songName);
                            sa26.setText(songArtist);
                            st26.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                    }
                    trackCount++;
                }
            } catch (SQLException ex) {
                System.err.println("Error executing playlist tracks query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
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
        jPanel3 = new javax.swing.JPanel();
        currcover = new javax.swing.JLabel();
        currsong = new javax.swing.JLabel();
        currartist = new javax.swing.JLabel();
        songSlider = new javax.swing.JSlider();
        currTime = new javax.swing.JLabel();
        maxTime = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        homeicon = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        playlistcover = new javax.swing.JLabel();
        playlistname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        sc1 = new javax.swing.JLabel();
        sn1 = new javax.swing.JLabel();
        sa1 = new javax.swing.JLabel();
        st1 = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        sc2 = new javax.swing.JLabel();
        sn2 = new javax.swing.JLabel();
        sa2 = new javax.swing.JLabel();
        st2 = new javax.swing.JLabel();
        jPanel35 = new javax.swing.JPanel();
        sc3 = new javax.swing.JLabel();
        sn3 = new javax.swing.JLabel();
        sa3 = new javax.swing.JLabel();
        st3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        sc4 = new javax.swing.JLabel();
        sn4 = new javax.swing.JLabel();
        sa4 = new javax.swing.JLabel();
        st4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        sc5 = new javax.swing.JLabel();
        sn5 = new javax.swing.JLabel();
        sa5 = new javax.swing.JLabel();
        st5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        sc6 = new javax.swing.JLabel();
        sn6 = new javax.swing.JLabel();
        sa6 = new javax.swing.JLabel();
        st6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        sc7 = new javax.swing.JLabel();
        sn7 = new javax.swing.JLabel();
        sa7 = new javax.swing.JLabel();
        st7 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        sc8 = new javax.swing.JLabel();
        sn8 = new javax.swing.JLabel();
        sa8 = new javax.swing.JLabel();
        st8 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        sc9 = new javax.swing.JLabel();
        sn9 = new javax.swing.JLabel();
        sa9 = new javax.swing.JLabel();
        st9 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        sc10 = new javax.swing.JLabel();
        sn10 = new javax.swing.JLabel();
        sa10 = new javax.swing.JLabel();
        st10 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        sc11 = new javax.swing.JLabel();
        sn11 = new javax.swing.JLabel();
        sa11 = new javax.swing.JLabel();
        st11 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        sc12 = new javax.swing.JLabel();
        sn12 = new javax.swing.JLabel();
        sa12 = new javax.swing.JLabel();
        st12 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        sc13 = new javax.swing.JLabel();
        sn13 = new javax.swing.JLabel();
        sa13 = new javax.swing.JLabel();
        st13 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        sc14 = new javax.swing.JLabel();
        sn14 = new javax.swing.JLabel();
        sa14 = new javax.swing.JLabel();
        st14 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        sc15 = new javax.swing.JLabel();
        sn15 = new javax.swing.JLabel();
        sa15 = new javax.swing.JLabel();
        st15 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        sc16 = new javax.swing.JLabel();
        sn16 = new javax.swing.JLabel();
        sa16 = new javax.swing.JLabel();
        st16 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        sc17 = new javax.swing.JLabel();
        sn17 = new javax.swing.JLabel();
        sa17 = new javax.swing.JLabel();
        st17 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        sc18 = new javax.swing.JLabel();
        sn18 = new javax.swing.JLabel();
        sa18 = new javax.swing.JLabel();
        st18 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        sc19 = new javax.swing.JLabel();
        sn19 = new javax.swing.JLabel();
        sa19 = new javax.swing.JLabel();
        st19 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        sc20 = new javax.swing.JLabel();
        sn20 = new javax.swing.JLabel();
        sa20 = new javax.swing.JLabel();
        st20 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        sc21 = new javax.swing.JLabel();
        sn21 = new javax.swing.JLabel();
        sa21 = new javax.swing.JLabel();
        st21 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        sc22 = new javax.swing.JLabel();
        sn22 = new javax.swing.JLabel();
        sa22 = new javax.swing.JLabel();
        st22 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        sc23 = new javax.swing.JLabel();
        sn23 = new javax.swing.JLabel();
        sa23 = new javax.swing.JLabel();
        st23 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        sc24 = new javax.swing.JLabel();
        sn24 = new javax.swing.JLabel();
        sa24 = new javax.swing.JLabel();
        st24 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        sc25 = new javax.swing.JLabel();
        sn25 = new javax.swing.JLabel();
        sa25 = new javax.swing.JLabel();
        st25 = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        sc26 = new javax.swing.JLabel();
        sn26 = new javax.swing.JLabel();
        sa26 = new javax.swing.JLabel();
        st26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));

        jPanel3.setBackground(new java.awt.Color(20, 20, 20));

        currsong.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        currsong.setForeground(new java.awt.Color(255, 255, 255));
        currsong.setText("unknown song");

        currartist.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        currartist.setForeground(new java.awt.Color(255, 255, 255));
        currartist.setText("unknown artist");

        currTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        currTime.setForeground(new java.awt.Color(255, 255, 255));
        currTime.setText("00:00");

        maxTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        maxTime.setForeground(new java.awt.Color(255, 255, 255));
        maxTime.setText("00:00");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currcover, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currsong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currartist, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addGap(56, 56, 56)
                .addComponent(currTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(songSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(maxTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(currsong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currartist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(currTime)
                                    .addComponent(songSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maxTime))
                                .addGap(0, 20, Short.MAX_VALUE))))
                    .addComponent(currcover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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
                .addComponent(homeicon)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(25, 25, 25));

        playlistname.setFont(new java.awt.Font("Poor Richard", 0, 30)); // NOI18N
        playlistname.setForeground(new java.awt.Color(255, 255, 255));
        playlistname.setText("All Songs");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(playlistcover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playlistname, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlistcover, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playlistname, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
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

        jScrollPane1.setBackground(new java.awt.Color(20, 20, 20));
        jScrollPane1.setBorder(null);

        jPanel4.setBackground(new java.awt.Color(20, 20, 20));

        jPanel6.setBackground(new java.awt.Color(25, 25, 25));

        sc1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sc1.setForeground(new java.awt.Color(255, 255, 255));
        sc1.setText("#");

        sn1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sn1.setForeground(new java.awt.Color(255, 255, 255));
        sn1.setText("Title");
        sn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn1MouseReleased(evt);
            }
        });

        sa1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sa1.setForeground(new java.awt.Color(255, 255, 255));
        sa1.setText("Artist");

        st1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        st1.setForeground(new java.awt.Color(255, 255, 255));
        st1.setText("Time");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel34.setBackground(new java.awt.Color(25, 25, 25));

        sc2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc2.setForeground(new java.awt.Color(255, 255, 255));

        sn2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn2.setForeground(new java.awt.Color(255, 255, 255));
        sn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn2MouseReleased(evt);
            }
        });

        sa2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa2.setForeground(new java.awt.Color(255, 255, 255));

        st2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel35.setBackground(new java.awt.Color(25, 25, 25));

        sc3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc3.setForeground(new java.awt.Color(255, 255, 255));

        sn3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn3.setForeground(new java.awt.Color(255, 255, 255));
        sn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn3MouseReleased(evt);
            }
        });

        sa3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa3.setForeground(new java.awt.Color(255, 255, 255));

        st3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(25, 25, 25));

        sc4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc4.setForeground(new java.awt.Color(255, 255, 255));

        sn4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn4.setForeground(new java.awt.Color(255, 255, 255));
        sn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn4MouseReleased(evt);
            }
        });

        sa4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa4.setForeground(new java.awt.Color(255, 255, 255));

        st4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn4, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa4, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel10.setBackground(new java.awt.Color(25, 25, 25));

        sc5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc5.setForeground(new java.awt.Color(255, 255, 255));

        sn5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn5.setForeground(new java.awt.Color(255, 255, 255));
        sn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn5MouseReleased(evt);
            }
        });

        sa5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa5.setForeground(new java.awt.Color(255, 255, 255));

        st5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st5.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn5, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa5, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(25, 25, 25));

        sc6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc6.setForeground(new java.awt.Color(255, 255, 255));

        sn6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn6.setForeground(new java.awt.Color(255, 255, 255));
        sn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn6MouseReleased(evt);
            }
        });

        sa6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa6.setForeground(new java.awt.Color(255, 255, 255));

        st6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st6.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn6, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(25, 25, 25));

        sc7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc7.setForeground(new java.awt.Color(255, 255, 255));

        sn7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn7.setForeground(new java.awt.Color(255, 255, 255));
        sn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn7MouseReleased(evt);
            }
        });

        sa7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa7.setForeground(new java.awt.Color(255, 255, 255));

        st7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st7.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn7, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa7, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(25, 25, 25));

        sc8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc8.setForeground(new java.awt.Color(255, 255, 255));

        sn8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn8.setForeground(new java.awt.Color(255, 255, 255));
        sn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn8MouseReleased(evt);
            }
        });

        sa8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa8.setForeground(new java.awt.Color(255, 255, 255));

        st8.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st8.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn8, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa8, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(st8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel13.setBackground(new java.awt.Color(25, 25, 25));

        sc9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc9.setForeground(new java.awt.Color(255, 255, 255));

        sn9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn9.setForeground(new java.awt.Color(255, 255, 255));
        sn9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn9MouseReleased(evt);
            }
        });

        sa9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa9.setForeground(new java.awt.Color(255, 255, 255));

        st9.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st9.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn9, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa9, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(25, 25, 25));

        sc10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc10.setForeground(new java.awt.Color(255, 255, 255));

        sn10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn10.setForeground(new java.awt.Color(255, 255, 255));
        sn10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn10MouseReleased(evt);
            }
        });

        sa10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa10.setForeground(new java.awt.Color(255, 255, 255));

        st10.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st10.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn10, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa10, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(25, 25, 25));

        sc11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc11.setForeground(new java.awt.Color(255, 255, 255));

        sn11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn11.setForeground(new java.awt.Color(255, 255, 255));
        sn11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn11MouseReleased(evt);
            }
        });

        sa11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa11.setForeground(new java.awt.Color(255, 255, 255));

        st11.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn11, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa11, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(25, 25, 25));

        sc12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc12.setForeground(new java.awt.Color(255, 255, 255));

        sn12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn12.setForeground(new java.awt.Color(255, 255, 255));
        sn12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn12MouseReleased(evt);
            }
        });

        sa12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa12.setForeground(new java.awt.Color(255, 255, 255));

        st12.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn12, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa12, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa12, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(25, 25, 25));

        sc13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc13.setForeground(new java.awt.Color(255, 255, 255));

        sn13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn13.setForeground(new java.awt.Color(255, 255, 255));
        sn13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn13MouseReleased(evt);
            }
        });

        sa13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa13.setForeground(new java.awt.Color(255, 255, 255));

        st13.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st13.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(25, 25, 25));

        sc14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc14.setForeground(new java.awt.Color(255, 255, 255));

        sn14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn14.setForeground(new java.awt.Color(255, 255, 255));
        sn14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn14MouseReleased(evt);
            }
        });

        sa14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa14.setForeground(new java.awt.Color(255, 255, 255));

        st14.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st14.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(25, 25, 25));

        sc15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc15.setForeground(new java.awt.Color(255, 255, 255));

        sn15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn15.setForeground(new java.awt.Color(255, 255, 255));
        sn15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn15MouseReleased(evt);
            }
        });

        sa15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa15.setForeground(new java.awt.Color(255, 255, 255));

        st15.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st15.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn15, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa15, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa15, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(25, 25, 25));

        sc16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc16.setForeground(new java.awt.Color(255, 255, 255));

        sn16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn16.setForeground(new java.awt.Color(255, 255, 255));
        sn16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn16MouseReleased(evt);
            }
        });

        sa16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa16.setForeground(new java.awt.Color(255, 255, 255));

        st16.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st16.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(25, 25, 25));

        sc17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc17.setForeground(new java.awt.Color(255, 255, 255));

        sn17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn17.setForeground(new java.awt.Color(255, 255, 255));
        sn17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn17MouseReleased(evt);
            }
        });

        sa17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa17.setForeground(new java.awt.Color(255, 255, 255));

        st17.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st17.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn17, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa17, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa17, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel23.setBackground(new java.awt.Color(25, 25, 25));

        sc18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc18.setForeground(new java.awt.Color(255, 255, 255));

        sn18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn18.setForeground(new java.awt.Color(255, 255, 255));
        sn18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn18MouseReleased(evt);
            }
        });

        sa18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa18.setForeground(new java.awt.Color(255, 255, 255));

        st18.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st18.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn18, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa18, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa18, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel24.setBackground(new java.awt.Color(25, 25, 25));

        sc19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc19.setForeground(new java.awt.Color(255, 255, 255));

        sn19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn19.setForeground(new java.awt.Color(255, 255, 255));
        sn19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn19MouseReleased(evt);
            }
        });

        sa19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa19.setForeground(new java.awt.Color(255, 255, 255));

        st19.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st19.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn19, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa19, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa19, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel25.setBackground(new java.awt.Color(25, 25, 25));

        sc20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc20.setForeground(new java.awt.Color(255, 255, 255));

        sn20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn20.setForeground(new java.awt.Color(255, 255, 255));
        sn20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn20MouseReleased(evt);
            }
        });

        sa20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa20.setForeground(new java.awt.Color(255, 255, 255));

        st20.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st20.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn20, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa20, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa20, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(25, 25, 25));

        sc21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc21.setForeground(new java.awt.Color(255, 255, 255));

        sn21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn21.setForeground(new java.awt.Color(255, 255, 255));
        sn21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn21MouseReleased(evt);
            }
        });

        sa21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa21.setForeground(new java.awt.Color(255, 255, 255));

        st21.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st21.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn21, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa21, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel27.setBackground(new java.awt.Color(25, 25, 25));

        sc22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc22.setForeground(new java.awt.Color(255, 255, 255));

        sn22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn22.setForeground(new java.awt.Color(255, 255, 255));
        sn22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn22MouseReleased(evt);
            }
        });

        sa22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa22.setForeground(new java.awt.Color(255, 255, 255));

        st22.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st22.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn22, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa22, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa22, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(25, 25, 25));

        sc23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc23.setForeground(new java.awt.Color(255, 255, 255));

        sn23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn23.setForeground(new java.awt.Color(255, 255, 255));
        sn23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn23MouseReleased(evt);
            }
        });

        sa23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa23.setForeground(new java.awt.Color(255, 255, 255));

        st23.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st23.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn23, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa23, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa23, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel29.setBackground(new java.awt.Color(25, 25, 25));

        sc24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc24.setForeground(new java.awt.Color(255, 255, 255));

        sn24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn24.setForeground(new java.awt.Color(255, 255, 255));
        sn24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn24MouseReleased(evt);
            }
        });

        sa24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa24.setForeground(new java.awt.Color(255, 255, 255));

        st24.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st24.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn24, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa24, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa24, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel30.setBackground(new java.awt.Color(25, 25, 25));

        sc25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc25.setForeground(new java.awt.Color(255, 255, 255));

        sn25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn25.setForeground(new java.awt.Color(255, 255, 255));
        sn25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn25MouseReleased(evt);
            }
        });

        sa25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa25.setForeground(new java.awt.Color(255, 255, 255));

        st25.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st25.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn25, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa25, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                .addComponent(st25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa25, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel31.setBackground(new java.awt.Color(25, 25, 25));

        sc26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc26.setForeground(new java.awt.Color(255, 255, 255));

        sn26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn26.setForeground(new java.awt.Color(255, 255, 255));
        sn26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn26MouseReleased(evt);
            }
        });

        sa26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa26.setForeground(new java.awt.Color(255, 255, 255));

        st26.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st26.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn26, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa26, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa26, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel34, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel35, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

    private void homeiconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconMouseReleased
        // TODO add your handling code here:
        dispose();
        Dashboard ads = new Dashboard();
        ads.setVisible(true);
    }//GEN-LAST:event_homeiconMouseReleased

    private void sn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn1MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn1MouseReleased

    private void sn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn2MouseReleased
        PlaylistData.setTrackId(1);   
        addSongToPlaylist();
    }//GEN-LAST:event_sn2MouseReleased

    private void sn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn3MouseReleased
        PlaylistData.setTrackId(2);
        addSongToPlaylist();
    }//GEN-LAST:event_sn3MouseReleased

    private void sn4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn4MouseReleased
        PlaylistData.setTrackId(3);
        addSongToPlaylist();
    }//GEN-LAST:event_sn4MouseReleased

    private void sn5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn5MouseReleased
        PlaylistData.setTrackId(4);
        addSongToPlaylist();
    }//GEN-LAST:event_sn5MouseReleased

    private void sn6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn6MouseReleased
        PlaylistData.setTrackId(5);
        addSongToPlaylist();
    }//GEN-LAST:event_sn6MouseReleased

    private void sn7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn7MouseReleased
        PlaylistData.setTrackId(6);
        addSongToPlaylist();
    }//GEN-LAST:event_sn7MouseReleased

    private void sn8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn8MouseReleased
        PlaylistData.setTrackId(7);
        addSongToPlaylist();
    }//GEN-LAST:event_sn8MouseReleased

    private void sn9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn9MouseReleased
        PlaylistData.setTrackId(8);
        addSongToPlaylist();
    }//GEN-LAST:event_sn9MouseReleased

    private void sn10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn10MouseReleased
        PlaylistData.setTrackId(9);
        addSongToPlaylist();
    }//GEN-LAST:event_sn10MouseReleased

    private void sn11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn11MouseReleased
        PlaylistData.setTrackId(10);
        addSongToPlaylist();
    }//GEN-LAST:event_sn11MouseReleased

    private void sn12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn12MouseReleased
        PlaylistData.setTrackId(11);
        addSongToPlaylist();
    }//GEN-LAST:event_sn12MouseReleased

    private void sn13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn13MouseReleased
        PlaylistData.setTrackId(12);
        addSongToPlaylist();
    }//GEN-LAST:event_sn13MouseReleased

    private void sn14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn14MouseReleased
        PlaylistData.setTrackId(13);
        addSongToPlaylist();
    }//GEN-LAST:event_sn14MouseReleased

    private void sn15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn15MouseReleased
        PlaylistData.setTrackId(14);
        addSongToPlaylist();
    }//GEN-LAST:event_sn15MouseReleased

    private void sn16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn16MouseReleased
        PlaylistData.setTrackId(15);
        addSongToPlaylist();
    }//GEN-LAST:event_sn16MouseReleased

    private void sn17MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn17MouseReleased
        PlaylistData.setTrackId(16);
        addSongToPlaylist();
    }//GEN-LAST:event_sn17MouseReleased

    private void sn18MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn18MouseReleased
        PlaylistData.setTrackId(17);
        addSongToPlaylist();
    }//GEN-LAST:event_sn18MouseReleased

    private void sn19MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn19MouseReleased
        PlaylistData.setTrackId(18);
        addSongToPlaylist();
    }//GEN-LAST:event_sn19MouseReleased

    private void sn20MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn20MouseReleased
        PlaylistData.setTrackId(19);
        addSongToPlaylist();
    }//GEN-LAST:event_sn20MouseReleased

    private void sn21MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn21MouseReleased
        PlaylistData.setTrackId(20);
        addSongToPlaylist();
    }//GEN-LAST:event_sn21MouseReleased

    private void sn22MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn22MouseReleased
        PlaylistData.setTrackId(21);
        addSongToPlaylist();
    }//GEN-LAST:event_sn22MouseReleased

    private void sn23MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn23MouseReleased
        PlaylistData.setTrackId(22);
        addSongToPlaylist();
    }//GEN-LAST:event_sn23MouseReleased

    private void sn24MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn24MouseReleased
        PlaylistData.setTrackId(23);
        addSongToPlaylist();
    }//GEN-LAST:event_sn24MouseReleased

    private void sn25MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn25MouseReleased
        PlaylistData.setTrackId(24);
        addSongToPlaylist();
    }//GEN-LAST:event_sn25MouseReleased

    private void sn26MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn26MouseReleased
        PlaylistData.setTrackId(25);
        addSongToPlaylist();
    }//GEN-LAST:event_sn26MouseReleased

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
            java.util.logging.Logger.getLogger(AllSongs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllSongs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllSongs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllSongs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllSongs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel currTime;
    private javax.swing.JLabel currartist;
    private javax.swing.JLabel currcover;
    private javax.swing.JLabel currsong;
    private javax.swing.JLabel homeicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maxTime;
    private javax.swing.JLabel playlistcover;
    private javax.swing.JLabel playlistname;
    private javax.swing.JLabel sa1;
    private javax.swing.JLabel sa10;
    private javax.swing.JLabel sa11;
    private javax.swing.JLabel sa12;
    private javax.swing.JLabel sa13;
    private javax.swing.JLabel sa14;
    private javax.swing.JLabel sa15;
    private javax.swing.JLabel sa16;
    private javax.swing.JLabel sa17;
    private javax.swing.JLabel sa18;
    private javax.swing.JLabel sa19;
    private javax.swing.JLabel sa2;
    private javax.swing.JLabel sa20;
    private javax.swing.JLabel sa21;
    private javax.swing.JLabel sa22;
    private javax.swing.JLabel sa23;
    private javax.swing.JLabel sa24;
    private javax.swing.JLabel sa25;
    private javax.swing.JLabel sa26;
    private javax.swing.JLabel sa3;
    private javax.swing.JLabel sa4;
    private javax.swing.JLabel sa5;
    private javax.swing.JLabel sa6;
    private javax.swing.JLabel sa7;
    private javax.swing.JLabel sa8;
    private javax.swing.JLabel sa9;
    private javax.swing.JLabel sc1;
    private javax.swing.JLabel sc10;
    private javax.swing.JLabel sc11;
    private javax.swing.JLabel sc12;
    private javax.swing.JLabel sc13;
    private javax.swing.JLabel sc14;
    private javax.swing.JLabel sc15;
    private javax.swing.JLabel sc16;
    private javax.swing.JLabel sc17;
    private javax.swing.JLabel sc18;
    private javax.swing.JLabel sc19;
    private javax.swing.JLabel sc2;
    private javax.swing.JLabel sc20;
    private javax.swing.JLabel sc21;
    private javax.swing.JLabel sc22;
    private javax.swing.JLabel sc23;
    private javax.swing.JLabel sc24;
    private javax.swing.JLabel sc25;
    private javax.swing.JLabel sc26;
    private javax.swing.JLabel sc3;
    private javax.swing.JLabel sc4;
    private javax.swing.JLabel sc5;
    private javax.swing.JLabel sc6;
    private javax.swing.JLabel sc7;
    private javax.swing.JLabel sc8;
    private javax.swing.JLabel sc9;
    private javax.swing.JLabel sn1;
    private javax.swing.JLabel sn10;
    private javax.swing.JLabel sn11;
    private javax.swing.JLabel sn12;
    private javax.swing.JLabel sn13;
    private javax.swing.JLabel sn14;
    private javax.swing.JLabel sn15;
    private javax.swing.JLabel sn16;
    private javax.swing.JLabel sn17;
    private javax.swing.JLabel sn18;
    private javax.swing.JLabel sn19;
    private javax.swing.JLabel sn2;
    private javax.swing.JLabel sn20;
    private javax.swing.JLabel sn21;
    private javax.swing.JLabel sn22;
    private javax.swing.JLabel sn23;
    private javax.swing.JLabel sn24;
    private javax.swing.JLabel sn25;
    private javax.swing.JLabel sn26;
    private javax.swing.JLabel sn3;
    private javax.swing.JLabel sn4;
    private javax.swing.JLabel sn5;
    private javax.swing.JLabel sn6;
    private javax.swing.JLabel sn7;
    private javax.swing.JLabel sn8;
    private javax.swing.JLabel sn9;
    private javax.swing.JSlider songSlider;
    private javax.swing.JLabel st1;
    private javax.swing.JLabel st10;
    private javax.swing.JLabel st11;
    private javax.swing.JLabel st12;
    private javax.swing.JLabel st13;
    private javax.swing.JLabel st14;
    private javax.swing.JLabel st15;
    private javax.swing.JLabel st16;
    private javax.swing.JLabel st17;
    private javax.swing.JLabel st18;
    private javax.swing.JLabel st19;
    private javax.swing.JLabel st2;
    private javax.swing.JLabel st20;
    private javax.swing.JLabel st21;
    private javax.swing.JLabel st22;
    private javax.swing.JLabel st23;
    private javax.swing.JLabel st24;
    private javax.swing.JLabel st25;
    private javax.swing.JLabel st26;
    private javax.swing.JLabel st3;
    private javax.swing.JLabel st4;
    private javax.swing.JLabel st5;
    private javax.swing.JLabel st6;
    private javax.swing.JLabel st7;
    private javax.swing.JLabel st8;
    private javax.swing.JLabel st9;
    // End of variables declaration//GEN-END:variables
}
