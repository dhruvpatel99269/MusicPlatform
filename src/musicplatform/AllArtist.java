/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package musicplatform;

import musicplatform.ArtistsPanels.ShreyaGhoshal;
import musicplatform.ArtistsPanels.Pritam;
import musicplatform.ArtistsPanels.KK;
import musicplatform.ArtistsPanels.ArijitSingh;
import java.awt.Image;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import musicplatform.ArtistsPanels.AmitabhBhattacharya;
import musicplatform.ArtistsPanels.BPraak;
import musicplatform.ArtistsPanels.MustafaZahid;
import musicplatform.ArtistsPanels.PalakMuchhal;
import musicplatform.ArtistsPanels.TanishkBagchi;

/**
 *
 * @author DHRUV
 */
public class AllArtist extends javax.swing.JFrame {    

    /**
     * Creates new form AllArtist
     */
    public AllArtist() {
        initComponents();
        loadArtist();        
        int useriD=UserData.getUserId();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    private void loadArtist() {
        String albumimgQuery1 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=1";
        String albumimgQuery2 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=11";
        String albumimgQuery3 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=13";
        String albumimgQuery4 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=14";        
        String albumimgQuery5 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=16";        
        String albumimgQuery6 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=6";        
        String albumimgQuery7 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=8"; 
        String albumimgQuery8 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=10"; 
        String albumimgQuery9 = "SELECT artist_cover_image, artistname FROM artist WHERE ARTISTID=15";         
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Dhruv@99269!")) {
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery1)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc1.setIcon(new ImageIcon(scaledImage));
                    artistn1.setText(artist);
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
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc2.setIcon(new ImageIcon(scaledImage));
                    artistn2.setText(artist);
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
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc3.setIcon(new ImageIcon(scaledImage));
                    artistn3.setText(artist);
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
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc4.setIcon(new ImageIcon(scaledImage));
                    artistn4.setText(artist);
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
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery5)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc5.setIcon(new ImageIcon(scaledImage));
                    artistn5.setText(artist);
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
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery6)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc6.setIcon(new ImageIcon(scaledImage));
                    artistn6.setText(artist);
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
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery7)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc7.setIcon(new ImageIcon(scaledImage));
                    artistn7.setText(artist);
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
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery8)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc8.setIcon(new ImageIcon(scaledImage));
                    artistn8.setText(artist);
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
            try (java.sql.PreparedStatement statement = connection.prepareStatement(albumimgQuery9)) {
                java.sql.ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    String artist=resultSet.getString("artistname");
                    byte[] imageBytes = resultSet.getBytes("artist_cover_image");                    
                    ImageIcon imageIcon = new ImageIcon(imageBytes);
                    Image image = imageIcon.getImage();
                    Image scaledImage = image.getScaledInstance(208, 200, java.awt.Image.SCALE_SMOOTH);
                    artistc9.setIcon(new ImageIcon(scaledImage));
                    artistn9.setText(albumimgQuery9);
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
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        artistspanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        artists = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        artistc1 = new javax.swing.JLabel();
        artistn1 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        artistc2 = new javax.swing.JLabel();
        artistn2 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        artistc3 = new javax.swing.JLabel();
        artistn3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        artistc4 = new javax.swing.JLabel();
        artistn4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        artistc5 = new javax.swing.JLabel();
        artistn5 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        artistc6 = new javax.swing.JLabel();
        artistn6 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        artistc7 = new javax.swing.JLabel();
        artistn7 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        artistc8 = new javax.swing.JLabel();
        artistn8 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        artistc9 = new javax.swing.JLabel();
        artistn9 = new javax.swing.JLabel();

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

        profileicon.setForeground(new java.awt.Color(255, 255, 255));
        profileicon.setText("profile");

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

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        artistspanel.setBackground(new java.awt.Color(25, 25, 25));

        jPanel4.setBackground(new java.awt.Color(25, 25, 25));

        artists.setBackground(new java.awt.Color(25, 25, 25));
        artists.setFont(new java.awt.Font("Poor Richard", 2, 60)); // NOI18N
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
                .addComponent(artists, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(artists)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jScrollPane1.setBackground(new java.awt.Color(25, 25, 25));
        jScrollPane1.setBorder(null);

        jPanel12.setBackground(new java.awt.Color(25, 25, 25));

        artistc1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc1MouseReleased(evt);
            }
        });

        artistn1.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn1.setText("Arijit Singh");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(artistn1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        artistc2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc2MouseReleased(evt);
            }
        });

        artistn2.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn2.setText("Atif Aslam");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc3MouseReleased(evt);
            }
        });

        artistn3.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn3.setText("The Chainsmokers");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc4MouseReleased(evt);
            }
        });

        artistn4.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn4.setText("Ed Sheeran");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn4, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc5MouseReleased(evt);
            }
        });

        artistn5.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn5.setText("Imagine Dragons");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn5, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc6MouseReleased(evt);
            }
        });

        artistn6.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn6.setText("KK");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn6, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc7MouseReleased(evt);
            }
        });

        artistn7.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn7.setText("Martin Garrix");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc7, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn7, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc7, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc8MouseReleased(evt);
            }
        });

        artistn8.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn8.setText("Pritam");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc8, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn8, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc8, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        artistc9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                artistc9MouseReleased(evt);
            }
        });

        artistn9.setFont(new java.awt.Font("Lucida Calligraphy", 0, 18)); // NOI18N
        artistn9.setText("Shaan");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(artistc9, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(artistn9, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistc9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(artistn9, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel12);

        javax.swing.GroupLayout artistspanelLayout = new javax.swing.GroupLayout(artistspanel);
        artistspanel.setLayout(artistspanelLayout);
        artistspanelLayout.setHorizontalGroup(
            artistspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(artistspanelLayout.createSequentialGroup()
                .addGroup(artistspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, artistspanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE))
                .addContainerGap())
        );
        artistspanelLayout.setVerticalGroup(
            artistspanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(artistspanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(artistspanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
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

    }//GEN-LAST:event_artistsMouseReleased

    private void homeiconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconMouseReleased
        // TODO add your handling code here:
        dispose();
        Dashboard ads = new Dashboard();
        ads.setVisible(true);
    }//GEN-LAST:event_homeiconMouseReleased

    private void artistc1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc1MouseReleased
        // TODO add your handling code here:
        dispose();
        ArijitSingh art=new ArijitSingh();
        art.setVisible(true);       
    }//GEN-LAST:event_artistc1MouseReleased

    private void artistc2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc2MouseReleased
        // TODO add your handling code here:
        dispose();
        AmitabhBhattacharya art=new AmitabhBhattacharya();
        art.setVisible(true);        
    }//GEN-LAST:event_artistc2MouseReleased

    private void artistc3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc3MouseReleased
        // TODO add your handling code here:
        dispose();
        BPraak art=new BPraak();
        art.setVisible(true);
    }//GEN-LAST:event_artistc3MouseReleased

    private void artistc4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc4MouseReleased
        // TODO add your handling code here:
        dispose();
        PalakMuchhal art=new PalakMuchhal();
        art.setVisible(true);             
    }//GEN-LAST:event_artistc4MouseReleased

    private void artistc5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc5MouseReleased
        // TODO add your handling code here:
        dispose();
        MustafaZahid art=new MustafaZahid();
        art.setVisible(true);
    }//GEN-LAST:event_artistc5MouseReleased

    private void artistc6MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc6MouseReleased
        // TODO add your handling code here:
        dispose();
        KK art=new KK();
        art.setVisible(true);
    }//GEN-LAST:event_artistc6MouseReleased

    private void artistc7MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc7MouseReleased
        // TODO add your handling code here:
        dispose();
        Pritam art=new Pritam();
        art.setVisible(true);               
    }//GEN-LAST:event_artistc7MouseReleased

    private void artistc8MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc8MouseReleased
        // TODO add your handling code here:
        dispose();
        ShreyaGhoshal art=new ShreyaGhoshal();
        art.setVisible(true);
    }//GEN-LAST:event_artistc8MouseReleased

    private void artistc9MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_artistc9MouseReleased
        // TODO add your handling code here:
        dispose();
        TanishkBagchi art=new TanishkBagchi();
        art.setVisible(true);
    }//GEN-LAST:event_artistc9MouseReleased

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
            java.util.logging.Logger.getLogger(AllArtist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AllArtist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AllArtist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AllArtist.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AllArtist().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel artistc1;
    private javax.swing.JLabel artistc2;
    private javax.swing.JLabel artistc3;
    private javax.swing.JLabel artistc4;
    private javax.swing.JLabel artistc5;
    private javax.swing.JLabel artistc6;
    private javax.swing.JLabel artistc7;
    private javax.swing.JLabel artistc8;
    private javax.swing.JLabel artistc9;
    private javax.swing.JLabel artistn1;
    private javax.swing.JLabel artistn2;
    private javax.swing.JLabel artistn3;
    private javax.swing.JLabel artistn4;
    private javax.swing.JLabel artistn5;
    private javax.swing.JLabel artistn6;
    private javax.swing.JLabel artistn7;
    private javax.swing.JLabel artistn8;
    private javax.swing.JLabel artistn9;
    private javax.swing.JLabel artists;
    private javax.swing.JPanel artistspanel;
    private javax.swing.JLabel homeicon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel profileicon;
    // End of variables declaration//GEN-END:variables
}
