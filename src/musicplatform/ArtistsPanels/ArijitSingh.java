/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform.ArtistsPanels;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;
import musicplatform.Dashboard;
import musicplatform.Songs;

/**
 *
 * @author DHRUV
 */
public class ArijitSingh extends javax.swing.JFrame {

    private AdvancedPlayer player;
    private Thread playerThread;
    private boolean isPlaying = false;
    private int currentTrackId = 0;
    private javax.swing.Timer timer;
    private int songDuration = 0;    
    
    /**
     * Creates new form Artist2
     */
    public ArijitSingh() {
        initComponents();
        loadSongs();
        loadArtist();
    }
    
    private void loadArtist() {
        String albumimgQuery = "SELECT artist_cover_image FROM artist WHERE ArtistID=2";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");
                    // Assuming album1 is a JLabel or similar component to display images
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(276, 276, java.awt.Image.SCALE_SMOOTH);
                    artistcover.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }
    }
    
    private void loadSongs() {
        String songq1 = "SELECT song_cover_image, song_title, song_artist, duration FROM tracks WHERE trackid=24";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {

            try (java.sql.PreparedStatement statement = connection.prepareStatement(songq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
                    sc1.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(songq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    sn1.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(songq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    sa1.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(songq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    int songDuration = resultSet.getInt("duration");                    
                    int min = songDuration / 60;
                    int sec = songDuration % 60;
                    st1.setText(String.format("%02d:%02d", min, sec));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(AtifAslam.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void playSong(int trackId) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            // Execute the SQL query to get song details
            String songQuery = "SELECT SONG_TITLE, SONG_COVER_IMAGE, SONG_ARTIST, SONG_AUDIO, DURATION FROM TRACKS WHERE TRACKID=?";

            try (java.sql.PreparedStatement songStatement = connection.prepareStatement(songQuery)) {
                // Set the track ID parameter
                songStatement.setInt(1, trackId);

                try (java.sql.ResultSet songResult = songStatement.executeQuery()) {
                    // Check if the song exists in the database
                    if (songResult.next()) {
                        // Get the values from the result set
                        String songTitle = songResult.getString("SONG_TITLE");
                        String songArtist = songResult.getString("SONG_ARTIST");
                        byte[] songImage = songResult.getBytes("SONG_COVER_IMAGE");
                        byte[] audioData = songResult.getBytes("SONG_AUDIO");
                        songDuration = songResult.getInt("DURATION");
                        songSlider.setMaximum(songDuration);

                        // Display song title                                          
                        // Toggle play/pause
                        if (isPlaying && currentTrackId == trackId) {
                            // Stop playback if the same song is clicked again
                            currsong.setText(songTitle);
                            player.close();
                            isPlaying = false;
                            currentTrackId = 0; // Reset current track ID
                            timer.stop();
                            return;
                        } else {
                            // Stop currently playing song
                            if (isPlaying) {
                                currsong.setText(songTitle);
                                player.close();
                                timer.stop();
                            }

                            // Play the audio directly from MP3 data
                            if (audioData != null) {
                                ByteArrayInputStream audioStream = new ByteArrayInputStream(audioData);
                                player = new AdvancedPlayer(audioStream);
                                playerThread = new Thread(() -> {
                                    try {
                                        player.play();
                                    } catch (JavaLayerException ex) {
                                        Logger.getLogger(Songs.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                                playerThread.start();
                                isPlaying = true;
                                currentTrackId = trackId; // Update current track ID                                                                                                
                                setupTimer(songDuration);
                                currsong.setText(songTitle);
                                currartist.setText(songArtist);
                                ImageIcon imageIcon = new ImageIcon(songImage);
                                Image image = imageIcon.getImage();
                                Image scaledImage = image.getScaledInstance(88, 88, java.awt.Image.SCALE_SMOOTH);
                                currcover.setIcon(new ImageIcon(scaledImage));
                                maxTime.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60)); // Update max time display
                            }
                        }
                    }
                } catch (JavaLayerException ex) {
                    Logger.getLogger(Songs.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException ex) {
                Logger.getLogger(Songs.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Songs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void setupTimer(int max) {
        timer = new javax.swing.Timer(1000, new ActionListener() {
            int value = 0;
            int smin = 0;
            int ssec = 0;
            int emin = 0;
            int esec = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                value++;
                if (value <= max) {
                    songSlider.setValue(value);
                    updateButtonText(value);
                } else {
                    // Stop the timer when the maximum value is reached
                    timer.stop();
                }
            }

            private void updateButtonText(int value) {
                smin = value / 60;
                ssec = value % 60;
                emin = max / 60;
                esec = max % 60;
                maxTime.setText(String.format("%02d:%02d", emin, esec));
                currTime.setText(String.format("%02d:%02d", smin, ssec));
            }
        });
        timer.start();
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
        artistcover = new javax.swing.JLabel();
        artistname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        sc1 = new javax.swing.JLabel();
        sn1 = new javax.swing.JLabel();
        sa1 = new javax.swing.JLabel();
        st1 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        sc2 = new javax.swing.JLabel();
        sn2 = new javax.swing.JLabel();
        sa2 = new javax.swing.JLabel();
        st2 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
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
        jPanel17 = new javax.swing.JPanel();
        sc8 = new javax.swing.JLabel();
        sn8 = new javax.swing.JLabel();
        sa8 = new javax.swing.JLabel();
        st8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));

        jPanel3.setBackground(new java.awt.Color(20, 20, 20));

        currsong.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        currsong.setForeground(new java.awt.Color(255, 255, 255));

        currartist.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        currartist.setForeground(new java.awt.Color(255, 255, 255));

        currTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        currTime.setForeground(new java.awt.Color(255, 255, 255));
        currTime.setText("jLabel7");

        maxTime.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        maxTime.setForeground(new java.awt.Color(255, 255, 255));
        maxTime.setText("jLabel7");

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

        artistname.setFont(new java.awt.Font("Poor Richard", 0, 54)); // NOI18N
        artistname.setForeground(new java.awt.Color(255, 255, 255));
        artistname.setText("Atif Aslam");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(artistname, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                    .addComponent(artistcover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistcover, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(artistname, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        sc1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc1.setForeground(new java.awt.Color(255, 255, 255));
        sc1.setText("jLabel7");

        sn1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn1.setForeground(new java.awt.Color(255, 255, 255));
        sn1.setText("jLabel8");
        sn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn1MouseReleased(evt);
            }
        });

        sa1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa1.setForeground(new java.awt.Color(255, 255, 255));
        sa1.setText("jLabel9");

        st1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st1.setForeground(new java.awt.Color(255, 255, 255));
        st1.setText("jLabel10");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jPanel15.setBackground(new java.awt.Color(25, 25, 25));

        sc2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc2.setForeground(new java.awt.Color(255, 255, 255));
        sc2.setText("jLabel7");

        sn2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn2.setForeground(new java.awt.Color(255, 255, 255));
        sn2.setText("jLabel8");
        sn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn2MouseReleased(evt);
            }
        });

        sa2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa2.setForeground(new java.awt.Color(255, 255, 255));
        sa2.setText("jLabel9");

        st2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st2.setForeground(new java.awt.Color(255, 255, 255));
        st2.setText("jLabel10");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(25, 25, 25));

        sc3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc3.setForeground(new java.awt.Color(255, 255, 255));
        sc3.setText("jLabel7");

        sn3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn3.setForeground(new java.awt.Color(255, 255, 255));
        sn3.setText("jLabel8");
        sn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn3MouseReleased(evt);
            }
        });

        sa3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa3.setForeground(new java.awt.Color(255, 255, 255));
        sa3.setText("jLabel9");

        st3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st3.setForeground(new java.awt.Color(255, 255, 255));
        st3.setText("jLabel10");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(25, 25, 25));

        sc4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sc4.setForeground(new java.awt.Color(255, 255, 255));
        sc4.setText("jLabel7");

        sn4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn4.setForeground(new java.awt.Color(255, 255, 255));
        sn4.setText("jLabel8");
        sn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn4MouseReleased(evt);
            }
        });

        sa4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa4.setForeground(new java.awt.Color(255, 255, 255));
        sa4.setText("jLabel9");

        st4.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st4.setForeground(new java.awt.Color(255, 255, 255));
        st4.setText("jLabel10");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
        sc5.setText("jLabel7");

        sn5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn5.setForeground(new java.awt.Color(255, 255, 255));
        sn5.setText("jLabel8");
        sn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn5MouseReleased(evt);
            }
        });

        sa5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa5.setForeground(new java.awt.Color(255, 255, 255));
        sa5.setText("jLabel9");

        st5.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st5.setForeground(new java.awt.Color(255, 255, 255));
        st5.setText("jLabel10");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
        sc6.setText("jLabel7");

        sn6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn6.setForeground(new java.awt.Color(255, 255, 255));
        sn6.setText("jLabel8");
        sn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn6MouseReleased(evt);
            }
        });

        sa6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa6.setForeground(new java.awt.Color(255, 255, 255));
        sa6.setText("jLabel9");

        st6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st6.setForeground(new java.awt.Color(255, 255, 255));
        st6.setText("jLabel10");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
        sc7.setText("jLabel7");

        sn7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn7.setForeground(new java.awt.Color(255, 255, 255));
        sn7.setText("jLabel8");
        sn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn7MouseReleased(evt);
            }
        });

        sa7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa7.setForeground(new java.awt.Color(255, 255, 255));
        sa7.setText("jLabel9");

        st7.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st7.setForeground(new java.awt.Color(255, 255, 255));
        st7.setText("jLabel10");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

        jPanel17.setBackground(new java.awt.Color(25, 25, 25));

        sc8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sc8.setForeground(new java.awt.Color(255, 255, 255));
        sc8.setText("#");

        sn8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sn8.setForeground(new java.awt.Color(255, 255, 255));
        sn8.setText("Title");
        sn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn8MouseReleased(evt);
            }
        });

        sa8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        sa8.setForeground(new java.awt.Color(255, 255, 255));
        sa8.setText("Artist");

        st8.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        st8.setForeground(new java.awt.Color(255, 255, 255));
        st8.setText("Time");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
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
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
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
        playSong(1);
    }//GEN-LAST:event_sn1MouseReleased

    private void sn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn2MouseReleased

    private void sn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn3MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn3MouseReleased

    private void sn4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn4MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn4MouseReleased

    private void sn5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn5MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn5MouseReleased

    private void sn6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn6MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn6MouseReleased

    private void sn7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn7MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn7MouseReleased

    private void sn8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn8MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_sn8MouseReleased

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
            java.util.logging.Logger.getLogger(ArijitSingh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArijitSingh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArijitSingh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArijitSingh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArijitSingh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel artistcover;
    private javax.swing.JLabel artistname;
    private javax.swing.JLabel currTime;
    private javax.swing.JLabel currartist;
    private javax.swing.JLabel currcover;
    private javax.swing.JLabel currsong;
    private javax.swing.JLabel homeicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maxTime;
    private javax.swing.JLabel sa1;
    private javax.swing.JLabel sa2;
    private javax.swing.JLabel sa3;
    private javax.swing.JLabel sa4;
    private javax.swing.JLabel sa5;
    private javax.swing.JLabel sa6;
    private javax.swing.JLabel sa7;
    private javax.swing.JLabel sa8;
    private javax.swing.JLabel sc1;
    private javax.swing.JLabel sc2;
    private javax.swing.JLabel sc3;
    private javax.swing.JLabel sc4;
    private javax.swing.JLabel sc5;
    private javax.swing.JLabel sc6;
    private javax.swing.JLabel sc7;
    private javax.swing.JLabel sc8;
    private javax.swing.JLabel sn1;
    private javax.swing.JLabel sn2;
    private javax.swing.JLabel sn3;
    private javax.swing.JLabel sn4;
    private javax.swing.JLabel sn5;
    private javax.swing.JLabel sn6;
    private javax.swing.JLabel sn7;
    private javax.swing.JLabel sn8;
    private javax.swing.JSlider songSlider;
    private javax.swing.JLabel st1;
    private javax.swing.JLabel st2;
    private javax.swing.JLabel st3;
    private javax.swing.JLabel st4;
    private javax.swing.JLabel st5;
    private javax.swing.JLabel st6;
    private javax.swing.JLabel st7;
    private javax.swing.JLabel st8;
    // End of variables declaration//GEN-END:variables
}
