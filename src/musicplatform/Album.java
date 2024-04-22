/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import com.mysql.cj.jdbc.Blob;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
 *
 * @author DHRUV
 */
public class Album extends javax.swing.JFrame {

    private AdvancedPlayer player;
    private Thread playerThread;
    private boolean isPlaying = false;
    private int currentTrackId = 0;
    private javax.swing.Timer timer;
    private int songDuration = 0;
    private LinkedList<Integer> shuffledLinkedList = new LinkedList<>();

    /**
     * Creates new form Album
     */
    public Album() {
        initComponents();
        connectToDatabase();
        loadAlbumImages();
        loadSongs();
    }

    class jPanelGradient extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            Color color1 = new Color(52, 143, 80);
            Color color2 = new Color(86, 180, 211);
            GradientPaint gp = new GradientPaint(0, 0, color1, 180, height, color2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, width, height);
        }
    }

    private void connectToDatabase() {
        // JDBC URL for MySQL database
        String jdbcUrl = "jdbc:mysql://localhost:3306/mydb";
        String username = "root";
        String password = "Dhruv@99269!";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            // Connection successful
            System.out.println("Connected to the database.");
            // You can perform database operations here
        } catch (SQLException e) {
            // Connection failed
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    private void loadAlbumImages() {
        String albumimgQuery = "SELECT album_cover_image FROM albums WHERE ALBUMID=1";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("album_cover_image");
                    // Assuming album1 is a JLabel or similar component to display images
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(144, 144, java.awt.Image.SCALE_SMOOTH);
                    albumcover1.setIcon(new ImageIcon(scaledImage));
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
                            songname.setText(songTitle);
                            player.close();
                            isPlaying = false;
                            currentTrackId = 0; // Reset current track ID
                            timer.stop();
                            return;
                        } else {
                            // Stop currently playing song
                            if (isPlaying) {
                                songname.setText(songTitle);
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
                                songname.setText(songTitle);
                                songartist.setText(songArtist);
                                ImageIcon imageIcon = new ImageIcon(songImage);
                                Image image = imageIcon.getImage();
                                Image scaledImage = image.getScaledInstance(144, 144, java.awt.Image.SCALE_SMOOTH);
                                songcover.setIcon(new ImageIcon(scaledImage));
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

    private void loadSongs() {
        String tscq1 = "SELECT song_cover_image FROM tracks WHERE trackid=24";
        String tsnq1 = "SELECT song_title FROM tracks WHERE trackid=24";
        String tsaq1 = "SELECT song_artist FROM tracks WHERE trackid=24";

        String tscq2 = "SELECT song_cover_image FROM tracks WHERE trackid=12";
        String tsnq2 = "SELECT song_title FROM tracks WHERE trackid=12";
        String tsaq2 = "SELECT song_artist FROM tracks WHERE trackid=12";

        String tscq3 = "SELECT song_cover_image FROM tracks WHERE trackid=23";
        String tsnq3 = "SELECT song_title FROM tracks WHERE trackid=23";
        String tsaq3 = "SELECT song_artist FROM tracks WHERE trackid=23";

        String tscq4 = "SELECT song_cover_image FROM tracks WHERE trackid=18";
        String tsnq4 = "SELECT song_title FROM tracks WHERE trackid=18";
        String tsaq4 = "SELECT song_artist FROM tracks WHERE trackid=18";

        String tscq5 = "SELECT song_cover_image FROM tracks WHERE trackid=17";
        String tsnq5 = "SELECT song_title FROM tracks WHERE trackid=17";
        String tsaq5 = "SELECT song_artist FROM tracks WHERE trackid=17";

        String tscq6 = "SELECT song_cover_image FROM tracks WHERE trackid=22";
        String tsnq6 = "SELECT song_title FROM tracks WHERE trackid=22";
        String tsaq6 = "SELECT song_artist FROM tracks WHERE trackid=22";

        String tscq7 = "SELECT song_cover_image FROM tracks WHERE trackid=11";
        String tsnq7 = "SELECT song_title FROM tracks WHERE trackid=11";
        String tsaq7 = "SELECT song_artist FROM tracks WHERE trackid=11";

        String tscq8 = "SELECT song_cover_image FROM tracks WHERE trackid=16";
        String tsnq8 = "SELECT song_title FROM tracks WHERE trackid=16";
        String tsaq8 = "SELECT song_artist FROM tracks WHERE trackid=16";

        String tscq9 = "SELECT song_cover_image FROM tracks WHERE trackid=25";
        String tsnq9 = "SELECT song_title FROM tracks WHERE trackid=25";
        String tsaq9 = "SELECT song_artist FROM tracks WHERE trackid=25";

        String tscq10 = "SELECT song_cover_image FROM tracks WHERE trackid=15";
        String tsnq10 = "SELECT song_title FROM tracks WHERE trackid=15";
        String tsaq10 = "SELECT song_artist FROM tracks WHERE trackid=15";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover1.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name1.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist1.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq2)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover2.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq2)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name2.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq2)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist2.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq3)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover3.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq3)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name3.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq3)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist3.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq4)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover4.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq4)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name4.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq4)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist4.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq5)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover5.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq5)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name5.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq5)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist5.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq6)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover6.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq6)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name6.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq6)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist6.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq7)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover7.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq7)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name7.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq7)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist7.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq8)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover8.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq8)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name8.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq8)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist8.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq9)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover9.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq9)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name9.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq9)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist9.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tscq10)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("song_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(39, 39, java.awt.Image.SCALE_SMOOTH);
                    cover10.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsnq10)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songTitle = resultSet.getString("song_title");
                    name10.setText(songTitle);
                } else {
                    System.err.println("Song title not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song title query: " + ex.getMessage());
            }

            try (java.sql.PreparedStatement statement = connection.prepareStatement(tsaq10)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String songArtist = resultSet.getString("song_artist");
                    artist10.setText(songArtist);
                } else {
                    System.err.println("Song artist not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing song artist query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Album.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new jPanelGradient();
        myplaylist = new javax.swing.JLabel();
        playlist1 = new javax.swing.JLabel();
        playlist2 = new javax.swing.JLabel();
        playlist3 = new javax.swing.JLabel();
        playlist4 = new javax.swing.JLabel();
        playlist5 = new javax.swing.JLabel();
        gotohome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        hash = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        artist = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        hash1 = new javax.swing.JLabel();
        cover1 = new javax.swing.JLabel();
        artist1 = new javax.swing.JLabel();
        name1 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        hash2 = new javax.swing.JLabel();
        cover2 = new javax.swing.JLabel();
        artist2 = new javax.swing.JLabel();
        name2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        hash3 = new javax.swing.JLabel();
        cover3 = new javax.swing.JLabel();
        artist3 = new javax.swing.JLabel();
        name3 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        hash4 = new javax.swing.JLabel();
        cover4 = new javax.swing.JLabel();
        artist4 = new javax.swing.JLabel();
        name4 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        hash5 = new javax.swing.JLabel();
        cover5 = new javax.swing.JLabel();
        artist5 = new javax.swing.JLabel();
        name5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        hash6 = new javax.swing.JLabel();
        cover6 = new javax.swing.JLabel();
        artist6 = new javax.swing.JLabel();
        name6 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        hash7 = new javax.swing.JLabel();
        cover7 = new javax.swing.JLabel();
        artist7 = new javax.swing.JLabel();
        name7 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        hash8 = new javax.swing.JLabel();
        cover8 = new javax.swing.JLabel();
        artist8 = new javax.swing.JLabel();
        name8 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        hash9 = new javax.swing.JLabel();
        cover9 = new javax.swing.JLabel();
        artist9 = new javax.swing.JLabel();
        name9 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        hash10 = new javax.swing.JLabel();
        cover10 = new javax.swing.JLabel();
        artist10 = new javax.swing.JLabel();
        name10 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        albumcover1 = new javax.swing.JLabel();
        albumname1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        songcover = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        songname = new javax.swing.JLabel();
        songartist = new javax.swing.JLabel();
        songSlider = new javax.swing.JSlider();
        maxTime = new javax.swing.JLabel();
        currTime = new javax.swing.JLabel();
        playpausebtn = new javax.swing.JButton();
        nextbtn = new javax.swing.JButton();
        prevbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(950, 700));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setAutoscrolls(true);

        myplaylist.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        myplaylist.setText("My Playlist's");

        playlist1.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        playlist1.setText("Playlist 1");

        playlist2.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        playlist2.setText("Playlist 2");

        playlist3.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        playlist3.setText("Playlist 3");

        playlist4.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        playlist4.setText("Playlist 4");

        playlist5.setFont(new java.awt.Font("Segoe UI", 2, 36)); // NOI18N
        playlist5.setText("Playlist 5");

        gotohome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        gotohome.setText("Go to home");
        gotohome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                gotohomeMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(playlist5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(gotohome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playlist4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playlist3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(playlist2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(myplaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
                            .addComponent(playlist1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gotohome, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(myplaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playlist5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        hash.setText("#");

        title.setText("Title");

        artist.setText("Artist");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(artist, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash1.setText("2");

        cover1.setText("Title");

        artist1.setText("Artist");

        name1.setText("jLabel1");
        name1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash2.setText("1");

        cover2.setText("Title");

        artist2.setText("Artist");

        name2.setText("jLabel1");
        name2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name2MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name2, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist2, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(cover2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash3.setText("3");

        cover3.setText("Title");

        artist3.setText("Artist");

        name3.setText("jLabel1");
        name3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name3MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name3, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist3, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash4.setText("4");

        cover4.setText("Title");

        artist4.setText("Artist");

        name4.setText("jLabel1");
        name4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name4, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist4, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash5.setText("5");

        cover5.setText("Title");

        artist5.setText("Artist");

        name5.setText("jLabel1");
        name5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name5MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name5, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist5, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash6.setText("6");

        cover6.setText("Title");

        artist6.setText("Artist");

        name6.setText("jLabel1");
        name6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name6MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name6, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist6, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash7.setText("7");

        cover7.setText("Title");

        artist7.setText("Artist");

        name7.setText("jLabel1");
        name7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name7MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name7, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist7, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash8.setText("8");

        cover8.setText("Title");

        artist8.setText("Artist");

        name8.setText("jLabel1");
        name8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name8MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name8, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist8, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash9.setText("9");

        cover9.setText("Title");

        artist9.setText("Artist");

        name9.setText("jLabel1");
        name9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name9MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name9, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist9, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(cover9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        hash10.setText("10");

        cover10.setText("Title");

        artist10.setText("Artist");

        name10.setText("jLabel1");
        name10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                name10MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hash10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cover10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(name10, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artist10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(name10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist10, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(cover10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(hash10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(0, 102, 153));

        albumcover1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                albumcover1MouseReleased(evt);
            }
        });

        albumname1.setFont(new java.awt.Font("Rockwell", 2, 48)); // NOI18N
        albumname1.setText("Bollywood Top 10");

        jScrollPane2.setBackground(new java.awt.Color(0, 102, 153));
        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(0, 102, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe Print", 0, 12)); // NOI18N
        jTextArea1.setRows(2);
        jTextArea1.setText("Hindi hits, Punjabi bangers, love songs, chart toppers, timeless classics, latest trending \ntracks and much more under a single playlist featuring best of artists .");
        jTextArea1.setBorder(null);
        jScrollPane2.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(albumcover1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(albumname1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(albumcover1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(albumname1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel4);

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(songcover, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(songcover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(songname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(songartist, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(songname, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(songartist, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addContainerGap())
        );

        maxTime.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        maxTime.setForeground(new java.awt.Color(255, 255, 255));
        maxTime.setText("maxTime");

        currTime.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        currTime.setForeground(new java.awt.Color(255, 255, 255));
        currTime.setText("currTime");

        playpausebtn.setText("Play");
        playpausebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playpausebtnActionPerformed(evt);
            }
        });

        nextbtn.setText("Next");
        nextbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextbtnActionPerformed(evt);
            }
        });

        prevbtn.setText("Prev");
        prevbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(currTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(songSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(maxTime, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(prevbtn)
                        .addGap(18, 18, 18)
                        .addComponent(playpausebtn)
                        .addGap(18, 18, 18)
                        .addComponent(nextbtn)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playpausebtn)
                    .addComponent(nextbtn)
                    .addComponent(prevbtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(songSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currTime)
                    .addComponent(maxTime))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(964, 607));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void gotohomeMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gotohomeMouseReleased
        // TODO add your handling code here:
        dispose();
        PlatformPanel pp = new PlatformPanel();
        pp.setVisible(true);
    }//GEN-LAST:event_gotohomeMouseReleased

    private void albumcover1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_albumcover1MouseReleased
        // TODO add your handling code here:
        int[] array = {12, 24, 23, 18, 17, 22, 11, 16, 25, 15};
        currentTrackId=12;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == currentTrackId) {
                // Check if the next index is within the array bounds
                int nextIndex = (i) % array.length; // Use modulo operator to wrap around to the beginning
                int nextTrackId = array[nextIndex];
                playSong(nextTrackId);
                break; // Exit the loop after finding the current track ID
            }
        }
    }//GEN-LAST:event_albumcover1MouseReleased

    private void name2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name2MouseReleased
        // TODO add your handling code here:
        playSong(12);
    }//GEN-LAST:event_name2MouseReleased

    private void name1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name1MouseReleased
        // TODO add your handling code here:
        playSong(24);
    }//GEN-LAST:event_name1MouseReleased

    private void name3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name3MouseReleased
        // TODO add your handling code here:
        playSong(23);
    }//GEN-LAST:event_name3MouseReleased

    private void name4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name4MouseReleased
        // TODO add your handling code here:
        playSong(18);
    }//GEN-LAST:event_name4MouseReleased

    private void name5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name5MouseReleased
        // TODO add your handling code here:
        playSong(17);
    }//GEN-LAST:event_name5MouseReleased

    private void name6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name6MouseReleased
        // TODO add your handling code here:
        playSong(22);
    }//GEN-LAST:event_name6MouseReleased

    private void name7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name7MouseReleased
        // TODO add your handling code here:
        playSong(11);
    }//GEN-LAST:event_name7MouseReleased

    private void name8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name8MouseReleased
        // TODO add your handling code here:
        playSong(16);
    }//GEN-LAST:event_name8MouseReleased

    private void name9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name9MouseReleased
        // TODO add your handling code here:
        playSong(25);
    }//GEN-LAST:event_name9MouseReleased

    private void name10MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_name10MouseReleased
        // TODO add your handling code here:
        playSong(15);
    }//GEN-LAST:event_name10MouseReleased

    private void playpausebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playpausebtnActionPerformed
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
    }//GEN-LAST:event_playpausebtnActionPerformed

    private void nextbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextbtnActionPerformed
        // TODO add your handling code here:
        int[] array = {12, 24, 23, 18, 17, 22, 11, 16, 25, 15};

        for (int i = 0; i < array.length; i++) {
            if (array[i] == currentTrackId) {
                // Check if the next index is within the array bounds
                int nextIndex = (i + 1) % array.length; // Use modulo operator to wrap around to the beginning
                int nextTrackId = array[nextIndex];
                playSong(nextTrackId);
                break; // Exit the loop after finding the current track ID
            }
        }
    }//GEN-LAST:event_nextbtnActionPerformed

    private void prevbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevbtnActionPerformed
        // TODO add your handling code here:
        int[] array = {12, 24, 23, 18, 17, 22, 11, 16, 25, 15};

        for (int i = 0; i < array.length; i++) {
            if (array[i] == currentTrackId) {
                // Calculate the previous index using modulo operator to wrap around
                int prevIndex = (i - 1 + array.length) % array.length;
                int prevTrackId = array[prevIndex];
                playSong(prevTrackId);
                break; // Exit the loop after finding the current track ID
            }
        }
    }//GEN-LAST:event_prevbtnActionPerformed

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
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Album.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Album().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel albumcover1;
    private javax.swing.JLabel albumname1;
    private javax.swing.JLabel artist;
    private javax.swing.JLabel artist1;
    private javax.swing.JLabel artist10;
    private javax.swing.JLabel artist2;
    private javax.swing.JLabel artist3;
    private javax.swing.JLabel artist4;
    private javax.swing.JLabel artist5;
    private javax.swing.JLabel artist6;
    private javax.swing.JLabel artist7;
    private javax.swing.JLabel artist8;
    private javax.swing.JLabel artist9;
    private javax.swing.JLabel cover1;
    private javax.swing.JLabel cover10;
    private javax.swing.JLabel cover2;
    private javax.swing.JLabel cover3;
    private javax.swing.JLabel cover4;
    private javax.swing.JLabel cover5;
    private javax.swing.JLabel cover6;
    private javax.swing.JLabel cover7;
    private javax.swing.JLabel cover8;
    private javax.swing.JLabel cover9;
    private javax.swing.JLabel currTime;
    private javax.swing.JLabel gotohome;
    private javax.swing.JLabel hash;
    private javax.swing.JLabel hash1;
    private javax.swing.JLabel hash10;
    private javax.swing.JLabel hash2;
    private javax.swing.JLabel hash3;
    private javax.swing.JLabel hash4;
    private javax.swing.JLabel hash5;
    private javax.swing.JLabel hash6;
    private javax.swing.JLabel hash7;
    private javax.swing.JLabel hash8;
    private javax.swing.JLabel hash9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel maxTime;
    private javax.swing.JLabel myplaylist;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel name10;
    private javax.swing.JLabel name2;
    private javax.swing.JLabel name3;
    private javax.swing.JLabel name4;
    private javax.swing.JLabel name5;
    private javax.swing.JLabel name6;
    private javax.swing.JLabel name7;
    private javax.swing.JLabel name8;
    private javax.swing.JLabel name9;
    private javax.swing.JButton nextbtn;
    private javax.swing.JLabel playlist1;
    private javax.swing.JLabel playlist2;
    private javax.swing.JLabel playlist3;
    private javax.swing.JLabel playlist4;
    private javax.swing.JLabel playlist5;
    private javax.swing.JButton playpausebtn;
    private javax.swing.JButton prevbtn;
    private javax.swing.JSlider songSlider;
    private javax.swing.JLabel songartist;
    private javax.swing.JLabel songcover;
    private javax.swing.JLabel songname;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
