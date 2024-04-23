/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author DHRUV
 */
public class Playlist3 extends javax.swing.JFrame {

    private AdvancedPlayer player;
    private Thread playerThread;
    private boolean isPlaying = false;
    private int currentTrackId = 0;
    private javax.swing.Timer timer;
    private int songDuration = 0;
    LinkedList<Integer> trackIdList = new LinkedList<>();
    int userid = UserData.getUserId();
    int playlistid3 = PlaylistData.getPlaylistId3();

    /**
     * Creates new form Playlist3
     */
    public Playlist3() {
        initComponents();
        loadPlaylistImage();
        loadSongs();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void loadPlaylistImage() {
        String albumimgQuery = "SELECT album_cover_image FROM albums WHERE ALBUMID=1";
        String playlistQuery = "SELECT playlist_name FROM playlists WHERE playlistID=?";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("album_cover_image");
                    // Assuming album1 is a JLabel or similar component to display images
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(276, 276, java.awt.Image.SCALE_SMOOTH);
                    playlistcover.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistQuery)) {
                statement.setInt(1, playlistid3);
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String playlist = resultSet.getString("playlist_name");
                    playlistname.setText(playlist);
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
        String playlistTracksQuery = "SELECT COUNT(tracks.trackid) AS cnt, tracks.trackid,tracks.song_cover_image, tracks.song_title, tracks.song_artist, tracks.duration "
                + "FROM tracks "
                + "JOIN playlistracks ON playlistracks.trackid = tracks.trackid "
                + "WHERE playlistracks.userid = ? AND playlistracks.playlistid = ? "
                + "GROUP BY tracks.trackid, tracks.song_cover_image, tracks.song_title, tracks.song_artist, tracks.duration "
                + ";";

        //String playlistTracksQuery = "SELECT song_cover_image,song_title, song_artist,duration from tracks where trackid=1";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistTracksQuery)) {
                statement.setInt(1, userid);
                statement.setInt(2, playlistid3);
                java.sql.ResultSet resultSet = statement.executeQuery();
                int trackCount = 0;
                while (resultSet.next() && trackCount < 10) { // Assuming you want to display up to 4 tracks                    
                    String songName = resultSet.getString("tracks.song_title");
                    String songArtist = resultSet.getString("tracks.song_artist");
                    int songDuration = resultSet.getInt("tracks.duration");
                    int trackid = resultSet.getInt("tracks.trackid");
                    trackIdList.add(trackid);
                    byte[] imageBytes1 = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon1 = new ImageIcon(imageBytes1);
                    Image image1 = imageIcon1.getImage();
                    Image scaledImage = image1.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
                    switch (trackCount) {
                        case 0:
                            sc1.setIcon(new ImageIcon(scaledImage));
                            sn1.setText(songName);
                            sa1.setText(songArtist);
                            st1.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 1:
                            sc2.setIcon(new ImageIcon(scaledImage));
                            sn2.setText(songName);
                            sa2.setText(songArtist);
                            st2.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 2:
                            sc3.setIcon(new ImageIcon(scaledImage));
                            sn3.setText(songName);
                            sa3.setText(songArtist);
                            st3.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 3:
                            sc4.setIcon(new ImageIcon(scaledImage));
                            sn4.setText(songName);
                            sa4.setText(songArtist);
                            st4.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 4:
                            sc5.setIcon(new ImageIcon(scaledImage));
                            sn5.setText(songName);
                            sa5.setText(songArtist);
                            st5.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 5:
                            sc6.setIcon(new ImageIcon(scaledImage));
                            sn6.setText(songName);
                            sa6.setText(songArtist);
                            st6.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
                            break;
                        case 6:
                            sc7.setIcon(new ImageIcon(scaledImage));
                            sn7.setText(songName);
                            sa7.setText(songArtist);
                            st7.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60));
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
                    }
                    trackCount++;
                }

                while (trackCount < 15) {
                    switch (trackCount) {
                        case 0:
                            sc1.setIcon(null);
                            sn1.setText("");
                            sa1.setText("");
                            st1.setText("");
                            break;
                        case 1:
                            sc2.setIcon(null);
                            sn2.setText("");
                            sa2.setText("");
                            st2.setText("");
                            break;
                        case 2:
                            sc3.setIcon(null);
                            sn3.setText("");
                            sa3.setText("");
                            st3.setText("");
                            break;
                        case 3:
                            sc4.setIcon(null);
                            sn4.setText("");
                            sa4.setText("");
                            st4.setText("");
                            break;
                        case 4:
                            sc5.setIcon(null);
                            sn5.setText("");
                            sa5.setText("");
                            st5.setText("");
                            break;
                        case 5:
                            sc6.setIcon(null);
                            sn6.setText("");
                            sa6.setText("");
                            st6.setText("");
                            break;
                        case 6:
                            sc7.setIcon(null);
                            sn7.setText("");
                            sa7.setText("");
                            st7.setText("");
                            break;
                        case 7:
                            sc9.setIcon(null);
                            sn9.setText("");
                            sa9.setText("");
                            st9.setText("");
                            break;
                        case 8:
                            sc10.setIcon(null);
                            sn10.setText("");
                            sa10.setText("");
                            st10.setText("");
                            break;
                        case 9:
                            sc11.setIcon(null);
                            sn11.setText("");
                            sa11.setText("");
                            st11.setText("");
                            break;
                        case 10:
                            sc12.setIcon(null);
                            sn12.setText("");
                            sa12.setText("");
                            st12.setText("");
                            break;
                        case 11:
                            sc13.setIcon(null);
                            sn13.setText("");
                            sa13.setText("");
                            st13.setText("");
                            break;
                        case 12:
                            sc14.setIcon(null);
                            sn14.setText("");
                            sa14.setText("");
                            st14.setText("");
                            break;
                        case 13:
                            sc15.setIcon(null);
                            sn15.setText("");
                            sa15.setText("");
                            st15.setText("");
                            break;
                        case 14:
                            sc16.setIcon(null);
                            sn16.setText("");
                            sa16.setText("");
                            st16.setText("");
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
                        songSlider2.setMaximum(songDuration);

                        // Display song title                                          
                        // Toggle play/pause
                        if (isPlaying && currentTrackId == trackId) {
                            // Stop playback if the same song is clicked again
                            currsong2.setText(songTitle);
                            player.close();
                            isPlaying = false;
                            currentTrackId = 0; // Reset current track ID
                            timer.stop();
                            return;
                        } else {
                            // Stop currently playing song
                            if (isPlaying) {
                                currsong2.setText(songTitle);
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
                                currsong2.setText(songTitle);
                                currartist2.setText(songArtist);
                                ImageIcon imageIcon = new ImageIcon(songImage);
                                Image image = imageIcon.getImage();
                                Image scaledImage = image.getScaledInstance(144, 144, java.awt.Image.SCALE_SMOOTH);
                                currcover2.setIcon(new ImageIcon(scaledImage));
                                maxTime2.setText(String.format("%02d:%02d", songDuration / 60, songDuration % 60)); // Update max time display
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
                    songSlider2.setValue(value);
                    updateButtonText(value);
                } else {
                    timer.stop();
                }
            }

            private void updateButtonText(int value) {
                smin = value / 60;
                ssec = value % 60;
                emin = max / 60;
                esec = max % 60;
                maxTime2.setText(String.format("%02d:%02d", emin, esec));
                currTime2.setText(String.format("%02d:%02d", smin, ssec));
            }
        });
        timer.start();
    }
    
    private void deletePlaylist(int userId, int playlistId3) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            String sql = "DELETE FROM playlists WHERE uid = ? AND playlistid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userid);
                pstmt.setInt(2, playlistid3);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            // Handle SQL exception
            ex.printStackTrace();
        }
    }

    private void deletePlaylistTracks(int userId, int playlistId3) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            String sql = "DELETE FROM playlistracks WHERE userid = ? AND playlistid = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, userid);
                pstmt.setInt(2, playlistid3);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            // Handle SQL exception
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        homeicon = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        playlistcover = new javax.swing.JLabel();
        playlistname = new javax.swing.JLabel();
        addSongsBtn = new javax.swing.JButton();
        deletePlaylistBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        sc1 = new javax.swing.JLabel();
        sn1 = new javax.swing.JLabel();
        sa1 = new javax.swing.JLabel();
        st1 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        sc2 = new javax.swing.JLabel();
        sn2 = new javax.swing.JLabel();
        sa2 = new javax.swing.JLabel();
        st2 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
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
        jPanel21 = new javax.swing.JPanel();
        sc13 = new javax.swing.JLabel();
        sn13 = new javax.swing.JLabel();
        sa13 = new javax.swing.JLabel();
        st13 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        sc14 = new javax.swing.JLabel();
        sn14 = new javax.swing.JLabel();
        sa14 = new javax.swing.JLabel();
        st14 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        sc15 = new javax.swing.JLabel();
        sn15 = new javax.swing.JLabel();
        sa15 = new javax.swing.JLabel();
        st15 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        sc16 = new javax.swing.JLabel();
        sn16 = new javax.swing.JLabel();
        sa16 = new javax.swing.JLabel();
        st16 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        currcover2 = new javax.swing.JLabel();
        currsong2 = new javax.swing.JLabel();
        currartist2 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        currTime2 = new javax.swing.JLabel();
        maxTime2 = new javax.swing.JLabel();
        songSlider2 = new javax.swing.JSlider();
        jPanel28 = new javax.swing.JPanel();
        nextBtn1 = new javax.swing.JButton();
        prevBtn1 = new javax.swing.JButton();
        playPauseBtn1 = new javax.swing.JButton();

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
        playlistname.setText("Playlist 1");

        addSongsBtn.setText("Add Songs");
        addSongsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSongsBtnActionPerformed(evt);
            }
        });

        deletePlaylistBtn.setText("Delete Playlist");
        deletePlaylistBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletePlaylistBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(playlistcover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playlistname, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(addSongsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deletePlaylistBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(playlistcover, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(playlistname, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSongsBtn)
                    .addComponent(deletePlaylistBtn))
                .addContainerGap(48, Short.MAX_VALUE))
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

        sn1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sn1.setForeground(new java.awt.Color(255, 255, 255));
        sn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                sn1MouseReleased(evt);
            }
        });

        sa1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        sa1.setForeground(new java.awt.Color(255, 255, 255));

        st1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        st1.setForeground(new java.awt.Color(255, 255, 255));

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

        jPanel18.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
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
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel19.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
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
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jPanel21.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa13, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel22.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc14, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn14, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa14, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                .addComponent(st14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
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

        jPanel23.setBackground(new java.awt.Color(25, 25, 25));

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

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(sc16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sn16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(st16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sa16, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(sc16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel26.setBackground(new java.awt.Color(20, 20, 20));

        currsong2.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        currsong2.setForeground(new java.awt.Color(255, 255, 255));

        currartist2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        currartist2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel27.setBackground(new java.awt.Color(20, 20, 20));

        currTime2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        currTime2.setForeground(new java.awt.Color(255, 255, 255));
        currTime2.setText("jLabel7");

        maxTime2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        maxTime2.setForeground(new java.awt.Color(255, 255, 255));
        maxTime2.setText("jLabel7");

        jPanel28.setBackground(new java.awt.Color(20, 20, 20));

        nextBtn1.setText("next");
        nextBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBtn1ActionPerformed(evt);
            }
        });

        prevBtn1.setText("prev");
        prevBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevBtn1ActionPerformed(evt);
            }
        });

        playPauseBtn1.setText("play");
        playPauseBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playPauseBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(prevBtn1)
                .addGap(18, 18, 18)
                .addComponent(playPauseBtn1)
                .addGap(18, 18, 18)
                .addComponent(nextBtn1)
                .addGap(25, 25, 25))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playPauseBtn1)
                    .addComponent(prevBtn1)
                    .addComponent(nextBtn1))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(songSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(maxTime2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(maxTime2)
                    .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(currTime2)
                        .addComponent(songSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currcover2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currsong2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(currartist2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel26Layout.createSequentialGroup()
                        .addComponent(currsong2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(currartist2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(currcover2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE))
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
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

    private void addSongsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSongsBtnActionPerformed
        // TODO add your handling code here:
        PlaylistData.setPlaylistId(playlistid3);
        dispose();
        AllSongs ads = new AllSongs();
        ads.setVisible(true);
    }//GEN-LAST:event_addSongsBtnActionPerformed

    private void deletePlaylistBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletePlaylistBtnActionPerformed
        if (userid != -1 && playlistid3 != -1) {
            deletePlaylistTracks(userid, playlistid3);
            deletePlaylist(userid, playlistid3); 
            dispose();
            Dashboard dashboard=new Dashboard();
            dashboard.setVisible(true);
        } else {
            // Handle error: unable to get user ID or playlist ID
            System.out.println("Error: Unable to get user ID or playlist ID.");
        }
    }//GEN-LAST:event_deletePlaylistBtnActionPerformed

    private void sn1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn1MouseReleased
        int trackidToPlay = trackIdList.get(0);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn1MouseReleased

    private void sn2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn2MouseReleased
        int trackidToPlay = trackIdList.get(1);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn2MouseReleased

    private void sn3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn3MouseReleased
        int trackidToPlay = trackIdList.get(2);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn3MouseReleased

    private void sn4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn4MouseReleased
        int trackidToPlay = trackIdList.get(3);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn4MouseReleased

    private void sn5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn5MouseReleased
        int trackidToPlay = trackIdList.get(4);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn5MouseReleased

    private void sn6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn6MouseReleased
        int trackidToPlay = trackIdList.get(5);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn6MouseReleased

    private void sn7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn7MouseReleased
        int trackidToPlay = trackIdList.get(6);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn7MouseReleased

    private void sn8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn8MouseReleased
        int trackidToPlay = trackIdList.get(7);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn8MouseReleased

    private void sn9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn9MouseReleased
        int trackidToPlay = trackIdList.get(8);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn9MouseReleased

    private void sn10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn10MouseReleased
        int trackidToPlay = trackIdList.get(9);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn10MouseReleased

    private void sn11MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn11MouseReleased
        int trackidToPlay = trackIdList.get(10);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn11MouseReleased

    private void sn12MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn12MouseReleased
        int trackidToPlay = trackIdList.get(11);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn12MouseReleased

    private void sn13MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn13MouseReleased
        int trackidToPlay = trackIdList.get(12);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn13MouseReleased

    private void sn14MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn14MouseReleased
        int trackidToPlay = trackIdList.get(13);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn14MouseReleased

    private void sn15MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn15MouseReleased
        int trackidToPlay = trackIdList.get(14);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn15MouseReleased

    private void sn16MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sn16MouseReleased
        int trackidToPlay = trackIdList.get(15);
        playSong(trackidToPlay);
    }//GEN-LAST:event_sn16MouseReleased

    private void nextBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBtn1ActionPerformed
        if (currentTrackId == -1) {
            System.out.println("Current track ID not set or invalid.");
            return; // Exit if current track ID is not valid
        }

        if (trackIdList.isEmpty()) {
            System.out.println("Track ID list is empty.");
            return; // Exit if track ID list is empty
        }

        // Find the index of the current track ID in the list
        int currentIndex = trackIdList.indexOf(currentTrackId);
        if (currentIndex == -1) {
            System.out.println("Current track ID not found in the list.");
            return; // Exit if current track ID is not found in the list
        }

        // Calculate the index of the next track ID
        int nextIndex = (currentIndex + 1) % trackIdList.size(); // Use modulo operator to wrap around to the beginning
        int nextTrackId = trackIdList.get(nextIndex);
        playSong(nextTrackId);
    }//GEN-LAST:event_nextBtn1ActionPerformed

    private void prevBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevBtn1ActionPerformed
        // TODO add your handling code here:
        if (currentTrackId == -1) {
            System.out.println("Current track ID not set or invalid.");
            return; // Exit if current track ID is not valid
        }

        if (trackIdList.isEmpty()) {
            System.out.println("Track ID list is empty.");
            return; // Exit if track ID list is empty
        }

        // Find the index of the current track ID in the list
        int currentIndex = trackIdList.indexOf(currentTrackId);
        if (currentIndex == -1) {
            System.out.println("Current track ID not found in the list.");
            return; // Exit if current track ID is not found in the list
        }

        // Calculate the previous index using modulo operator to wrap around
        int prevIndex = (currentIndex - 1 + trackIdList.size()) % trackIdList.size();
        int prevTrackId = trackIdList.get(prevIndex);
        playSong(prevTrackId);
    }//GEN-LAST:event_prevBtn1ActionPerformed

    private void playPauseBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playPauseBtn1ActionPerformed
        // TODO add your handling code here:
        if (currentTrackId != -1) {
            if (isPlaying) {
                // Pause the song
                player.close();
                timer.stop();
                isPlaying = false;
            } else {
                // Resume playing the song
                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
                    // Execute the SQL query to get song details
                    String songQuery = "SELECT SONG_AUDIO, DURATION FROM TRACKS WHERE TRACKID=?";
                    try (java.sql.PreparedStatement songStatement = connection.prepareStatement(songQuery)) {
                        // Set the track ID parameter
                        songStatement.setInt(1, currentTrackId);
                        try (java.sql.ResultSet songResult = songStatement.executeQuery()) {
                            // Check if the song exists in the database
                            if (songResult.next()) {
                                // Get the audio data and duration
                                byte[] audioData = songResult.getBytes("SONG_AUDIO");
                                int songDuration = songResult.getInt("DURATION");

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
                                    setupTimer(songDuration); // Reset the timer for the song
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
        } else {
            // Handle case when no song is currently playing
            // Maybe display a message or take appropriate action
        }
    }//GEN-LAST:event_playPauseBtn1ActionPerformed

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
            java.util.logging.Logger.getLogger(Playlist3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Playlist3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Playlist3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Playlist3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Playlist3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSongsBtn;
    private javax.swing.JLabel currTime2;
    private javax.swing.JLabel currartist2;
    private javax.swing.JLabel currcover2;
    private javax.swing.JLabel currsong2;
    private javax.swing.JButton deletePlaylistBtn;
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
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel maxTime2;
    private javax.swing.JButton nextBtn1;
    private javax.swing.JButton playPauseBtn1;
    private javax.swing.JLabel playlistcover;
    private javax.swing.JLabel playlistname;
    private javax.swing.JButton prevBtn1;
    private javax.swing.JLabel sa1;
    private javax.swing.JLabel sa10;
    private javax.swing.JLabel sa11;
    private javax.swing.JLabel sa12;
    private javax.swing.JLabel sa13;
    private javax.swing.JLabel sa14;
    private javax.swing.JLabel sa15;
    private javax.swing.JLabel sa16;
    private javax.swing.JLabel sa2;
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
    private javax.swing.JLabel sc2;
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
    private javax.swing.JLabel sn2;
    private javax.swing.JLabel sn3;
    private javax.swing.JLabel sn4;
    private javax.swing.JLabel sn5;
    private javax.swing.JLabel sn6;
    private javax.swing.JLabel sn7;
    private javax.swing.JLabel sn8;
    private javax.swing.JLabel sn9;
    private javax.swing.JSlider songSlider2;
    private javax.swing.JLabel st1;
    private javax.swing.JLabel st10;
    private javax.swing.JLabel st11;
    private javax.swing.JLabel st12;
    private javax.swing.JLabel st13;
    private javax.swing.JLabel st14;
    private javax.swing.JLabel st15;
    private javax.swing.JLabel st16;
    private javax.swing.JLabel st2;
    private javax.swing.JLabel st3;
    private javax.swing.JLabel st4;
    private javax.swing.JLabel st5;
    private javax.swing.JLabel st6;
    private javax.swing.JLabel st7;
    private javax.swing.JLabel st8;
    private javax.swing.JLabel st9;
    // End of variables declaration//GEN-END:variables
}
