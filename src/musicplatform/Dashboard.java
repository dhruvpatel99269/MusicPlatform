/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import musicplatform.Albums.Edm;
import musicplatform.Albums.Bollywood00s;
import musicplatform.Albums.MostPlayed;
import musicplatform.Albums.CurrentTrending;
import musicplatform.ArtistsPanels.ArijitSingh;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import musicplatform.Albums.BestOfRK;
import musicplatform.Albums.BestOfSK;
import musicplatform.Albums.DanceParty;
import musicplatform.Albums.SadMixtape;
import musicplatform.ArtistsPanels.MustafaZahid;
import musicplatform.ArtistsPanels.Pritam;
import musicplatform.ArtistsPanels.ShreyaGhoshal;


/**
 *
 * @author DHRUV
 */
public class Dashboard extends javax.swing.JFrame {

    int userid = UserData.getUserId();

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        loadAlbums();
        loadArtist();
        loadPlaylists();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void loadPlaylists() {
        String playlistQuery1 = "SELECT count(playlistid), playlist_name, playlistid FROM playlists WHERE uid = ? GROUP BY playlist_name,playlistid;";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(playlistQuery1)) {
                statement.setInt(1, userid);                
                java.sql.ResultSet resultSet = statement.executeQuery();
                int playlistCount = 0;
                while (resultSet.next() && playlistCount < 4) { // Assuming you want to display up to 3 playlists
                    String playlistName = resultSet.getString(2); // Get the playlist name
                    int id=resultSet.getInt(3);
                    switch (playlistCount) {
                        case 0:
                            playlist1.setText(playlistName);
                            PlaylistData.setPlaylistId1(id);                            
                            break;
                        case 1:
                            playlist2.setText(playlistName);
                            PlaylistData.setPlaylistId2(id);                            
                            break;
                        case 2:
                            playlist3.setText(playlistName);
                            PlaylistData.setPlaylistId3(id);                            
                            break;
                        case 3:
                            playlist4.setText(playlistName);
                            PlaylistData.setPlaylistId4(id);                            
                            break;                        
                    }
                    playlistCount++;
                }

                // Handle case when user has fewer than 3 playlists
                while (playlistCount < 4) {
                    switch (playlistCount) {
                        case 0:
                            playlist1.setText("");
                            break;
                        case 1:
                            playlist2.setText("");
                            break;
                        case 2:
                            playlist3.setText("");
                            break;
                        case 3:
                            playlist4.setText("");
                            break;                        
                    }
                    playlistCount++;
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }
    }

    public void loadAlbums() {
        String albumimgQuery1 = "SELECT album_cover_image FROM albums WHERE ALBUMID=5";
        String albumimgQuery2 = "SELECT album_cover_image FROM albums WHERE ALBUMID=7";
        String albumimgQuery3 = "SELECT album_cover_image FROM albums WHERE ALBUMID=8";
        String albumimgQuery4 = "SELECT album_cover_image FROM albums WHERE ALBUMID=6";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("album_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(169, 169, java.awt.Image.SCALE_SMOOTH);
                    album1.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery2)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("album_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(169, 169, java.awt.Image.SCALE_SMOOTH);
                    album2.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery3)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("album_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(169, 169, java.awt.Image.SCALE_SMOOTH);
                    album3.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery4)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("album_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(169, 169, java.awt.Image.SCALE_SMOOTH);
                    album4.setIcon(new ImageIcon(scaledImage));
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

    private void loadArtist() {
        String albumimgQuery1 = "SELECT artist_cover_image FROM artist WHERE ARTISTID=1";
        String albumimgQuery2 = "SELECT artist_cover_image FROM artist WHERE ARTISTID=10";
        String albumimgQuery3 = "SELECT artist_cover_image FROM artist WHERE ARTISTID=8";
        String albumimgQuery4 = "SELECT artist_cover_image FROM artist WHERE ARTISTID=16";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(173, 173, java.awt.Image.SCALE_SMOOTH);
                    artist1.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery2)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(173, 173, java.awt.Image.SCALE_SMOOTH);
                    artist2.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery3)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(173, 173, java.awt.Image.SCALE_SMOOTH);
                    artist3.setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Album cover image not found.");
                }
            } catch (SQLException ex) {
                System.err.println("Error executing album image query: " + ex.getMessage());
            }
        } catch (SQLException ex) {
            System.err.println("Error connecting to the database: " + ex.getMessage());
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery4)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(173, 173, java.awt.Image.SCALE_SMOOTH);
                    artist4.setIcon(new ImageIcon(scaledImage));
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
        profileicon = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        addPlaylist = new javax.swing.JLabel();
        playlist2 = new javax.swing.JLabel();
        playlist3 = new javax.swing.JLabel();
        playlist1 = new javax.swing.JLabel();
        playlist4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        topalbumspanel = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        topalbums = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        albumspanel = new javax.swing.JPanel();
        album1 = new javax.swing.JLabel();
        album2 = new javax.swing.JLabel();
        album3 = new javax.swing.JLabel();
        album4 = new javax.swing.JLabel();
        artistspanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        artists = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        artist1 = new javax.swing.JLabel();
        artist2 = new javax.swing.JLabel();
        artist3 = new javax.swing.JLabel();
        artist4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(950, 650));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(950, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(950, 650));

        jPanel2.setBackground(new java.awt.Color(20, 20, 20));

        jPanel7.setBackground(new java.awt.Color(25, 25, 25));

        homeicon.setBackground(new java.awt.Color(0, 0, 0));
        homeicon.setForeground(new java.awt.Color(255, 255, 255));
        homeicon.setText("home");

        profileicon.setForeground(new java.awt.Color(255, 255, 255));
        profileicon.setText("profile");
        profileicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                profileiconMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
                .addComponent(profileicon, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(profileicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeicon, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(25, 25, 25));

        jPanel6.setBackground(new java.awt.Color(20, 20, 20));

        addPlaylist.setBackground(new java.awt.Color(20, 20, 20));
        addPlaylist.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        addPlaylist.setForeground(new java.awt.Color(0, 153, 102));
        addPlaylist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addPlaylist.setText("Create New Playlist");
        addPlaylist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addPlaylistMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addPlaylist, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addPlaylist, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                .addContainerGap())
        );

        playlist2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        playlist2.setForeground(new java.awt.Color(255, 255, 255));
        playlist2.setText("Playlist 2");
        playlist2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playlist2MouseReleased(evt);
            }
        });

        playlist3.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        playlist3.setForeground(new java.awt.Color(255, 255, 255));
        playlist3.setText("Playlist 3");
        playlist3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playlist3MouseReleased(evt);
            }
        });

        playlist1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        playlist1.setForeground(new java.awt.Color(255, 255, 255));
        playlist1.setText("Playlist 1");
        playlist1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playlist1MouseReleased(evt);
            }
        });

        playlist4.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        playlist4.setForeground(new java.awt.Color(255, 255, 255));
        playlist4.setText("Playlist 4");
        playlist4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                playlist4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(playlist1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playlist2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playlist3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(playlist4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(playlist1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playlist2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playlist3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(playlist4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        topalbumspanel.setBackground(new java.awt.Color(25, 25, 25));

        jPanel5.setBackground(new java.awt.Color(25, 25, 25));

        topalbums.setBackground(new java.awt.Color(25, 25, 25));
        topalbums.setFont(new java.awt.Font("Poor Richard", 2, 48)); // NOI18N
        topalbums.setForeground(new java.awt.Color(0, 102, 153));
        topalbums.setText("Top Albums");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topalbums, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(topalbums)
        );

        jScrollPane2.setBorder(null);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        albumspanel.setBackground(new java.awt.Color(25, 25, 25));

        album1.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        album1.setText("jLabel2");
        album1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                album1MouseReleased(evt);
            }
        });

        album2.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        album2.setText("jLabel2");
        album2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                album2MouseReleased(evt);
            }
        });

        album3.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        album3.setText("jLabel2");
        album3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                album3MouseReleased(evt);
            }
        });

        album4.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        album4.setText("jLabel2");
        album4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                album4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout albumspanelLayout = new javax.swing.GroupLayout(albumspanel);
        albumspanel.setLayout(albumspanelLayout);
        albumspanelLayout.setHorizontalGroup(
            albumspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(albumspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(album1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(album2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(album3, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(album4, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        albumspanelLayout.setVerticalGroup(
            albumspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(albumspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(albumspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(album4, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
                    .addComponent(album3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(album1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(album2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane2.setViewportView(albumspanel);

        javax.swing.GroupLayout topalbumspanelLayout = new javax.swing.GroupLayout(topalbumspanel);
        topalbumspanel.setLayout(topalbumspanelLayout);
        topalbumspanelLayout.setHorizontalGroup(
            topalbumspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topalbumspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(topalbumspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topalbumspanelLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 608, Short.MAX_VALUE))
                .addContainerGap())
        );
        topalbumspanelLayout.setVerticalGroup(
            topalbumspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topalbumspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2)
                .addContainerGap())
        );

        artistspanel.setBackground(new java.awt.Color(25, 25, 25));

        jPanel4.setBackground(new java.awt.Color(25, 25, 25));

        artists.setBackground(new java.awt.Color(25, 25, 25));
        artists.setFont(new java.awt.Font("Poor Richard", 2, 48)); // NOI18N
        artists.setForeground(new java.awt.Color(0, 102, 153));
        artists.setText("Artists");
        artists.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistsMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(artists, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(358, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(artists)
        );

        jScrollPane3.setBorder(null);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel11.setBackground(new java.awt.Color(25, 25, 25));

        artist1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artist1MouseReleased(evt);
            }
        });

        artist2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artist2MouseReleased(evt);
            }
        });

        artist3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artist3MouseReleased(evt);
            }
        });

        artist4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artist4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artist1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(artist2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(artist3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(artist4, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artist2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artist4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(artist3, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel11);

        javax.swing.GroupLayout artistspanelLayout = new javax.swing.GroupLayout(artistspanel);
        artistspanel.setLayout(artistspanelLayout);
        artistspanelLayout.setHorizontalGroup(
            artistspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, artistspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(artistspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        artistspanelLayout.setVerticalGroup(
            artistspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(artistspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topalbumspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(topalbumspanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

    private void artistsMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistsMouseReleased
        // TODO add your handling code here:
        dispose();
        AllArtist ads = new AllArtist();
        ads.setVisible(true);
    }//GEN-LAST:event_artistsMouseReleased

    private void artist1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artist1MouseReleased
        // TODO add your handling code here:
        dispose();
        ArijitSingh art=new ArijitSingh();
        art.setVisible(true);
    }//GEN-LAST:event_artist1MouseReleased

    private void artist2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artist2MouseReleased
        // TODO add your handling code here:
        dispose();
        ShreyaGhoshal art=new ShreyaGhoshal();
        art.setVisible(true);
    }//GEN-LAST:event_artist2MouseReleased

    private void artist3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artist3MouseReleased
        // TODO add your handling code here:
        dispose();
        Pritam art=new Pritam();
        art.setVisible(true);
    }//GEN-LAST:event_artist3MouseReleased

    private void artist4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artist4MouseReleased
        // TODO add your handling code here:
        dispose();
        MustafaZahid art=new MustafaZahid();
        art.setVisible(true);
    }//GEN-LAST:event_artist4MouseReleased

    private void album1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_album1MouseReleased
        // TODO add your handling code here:
        dispose();
        BestOfSK alb=new BestOfSK();
        alb.setVisible(true);
    }//GEN-LAST:event_album1MouseReleased

    private void album2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_album2MouseReleased
        // TODO add your handling code here:
        dispose();
        DanceParty alb=new DanceParty();
        alb.setVisible(true);
    }//GEN-LAST:event_album2MouseReleased

    private void album3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_album3MouseReleased
        // TODO add your handling code here:
        dispose();
        SadMixtape alb=new SadMixtape();
        alb.setVisible(true);
    }//GEN-LAST:event_album3MouseReleased

    private void album4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_album4MouseReleased
        // TODO add your handling code here:
        dispose();
        BestOfRK alb=new BestOfRK();
        alb.setVisible(true);
    }//GEN-LAST:event_album4MouseReleased

    private void profileiconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profileiconMouseReleased
        // TODO add your handling code here:
        dispose();
        Profile ads = new Profile();
        ads.setVisible(true);
    }//GEN-LAST:event_profileiconMouseReleased

    private void addPlaylistMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addPlaylistMouseReleased
        // TODO add your handling code here:
        String playlistName = JOptionPane.showInputDialog(null, "Enter playlist name:", "Add Playlist", JOptionPane.PLAIN_MESSAGE);

        if (playlistName != null && !playlistName.isEmpty()) { // Check if the user entered a name
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
                String insertQuery = "INSERT INTO playlists(playlist_name, uid) VALUES (?, ?)";

                try (java.sql.PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                    statement.setString(1, playlistName);
                    statement.setInt(2, userid);

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Playlist added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        loadPlaylists();
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to add playlist.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error executing insert query: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error connecting to the database: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Playlist name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_addPlaylistMouseReleased

    private void playlist1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist1MouseReleased
        // TODO add your handling code here:
        dispose();
        Playlist1 ads=new Playlist1();
        ads.setVisible(true);
    }//GEN-LAST:event_playlist1MouseReleased

    private void playlist2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist2MouseReleased
        // TODO add your handling code here:
        dispose();
        Playlist2 ads=new Playlist2();
        ads.setVisible(true);
    }//GEN-LAST:event_playlist2MouseReleased

    private void playlist3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist3MouseReleased
        // TODO add your handling code here:
        dispose();
        Playlist3 ads=new Playlist3();
        ads.setVisible(true);
    }//GEN-LAST:event_playlist3MouseReleased

    private void playlist4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playlist4MouseReleased
        // TODO add your handling code here:
        dispose();
        Playlist4 ads=new Playlist4();
        ads.setVisible(true);
    }//GEN-LAST:event_playlist4MouseReleased

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
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addPlaylist;
    private javax.swing.JLabel album1;
    private javax.swing.JLabel album2;
    private javax.swing.JLabel album3;
    private javax.swing.JLabel album4;
    private javax.swing.JPanel albumspanel;
    private javax.swing.JLabel artist1;
    private javax.swing.JLabel artist2;
    private javax.swing.JLabel artist3;
    private javax.swing.JLabel artist4;
    private javax.swing.JLabel artists;
    private javax.swing.JPanel artistspanel;
    private javax.swing.JLabel homeicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel playlist1;
    private javax.swing.JLabel playlist2;
    private javax.swing.JLabel playlist3;
    private javax.swing.JLabel playlist4;
    private javax.swing.JLabel profileicon;
    private javax.swing.JLabel topalbums;
    private javax.swing.JPanel topalbumspanel;
    // End of variables declaration//GEN-END:variables
}
