import java.io.File;
import java.sql.*;
import java.util.Scanner;

import javax.sound.sampled.*;
import javax.swing.*;

public class Podcast {
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jukebox", "root", "admin");
        return connection;

    }

    public int DisplayPodcastEpisode() throws Exception {

        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from podcasttable;");
        Display obj = new Display();
        obj.cowdisplay1();
        System.out.println("No." + "       " + "Episode Name" + "     " + "Podcast Name");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "       " + rs.getDouble("episodeid") + "      "
                    + rs.getString("episodeName") + "     "
                    + rs.getString("podcastname") + " " + rs.getString("episodeDuration"));
        }
        obj.cowdisplay2();
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Enter the No. of the Episode you want to play");
        int a = sca.nextInt();
        return a;

    }

    public int displayByPodcastName() throws Exception {
        Scanner sca = new Scanner(System.in);
        try {
            Statement statement = getConnection().createStatement();
            ResultSet rs = statement.executeQuery("select distinct(podcastname), podcastid from podcasttable;");
            Display obj = new Display();
            obj.cowdisplay1();
            System.out.println("No. " + "     " + " Podcast name");
            while (rs.next()) {
                System.out.println(rs.getInt("podcastid") + "       " + rs.getString("podcastname"));
            }
            obj.cowdisplay2();
            System.out.println("Enter the number of Podcast to display");
            int podcastid = sca.nextInt();
            System.out.println("No." + "       " + "Podcast Name" + "    " + "Episode Name");
            ResultSet rs2 = statement
                    .executeQuery("select * from podcasttable where podcastid="
                            + podcastid);
            while (rs2.next()) {
                System.out.println(
                        rs2.getInt("id") + "       " + rs2.getString("episodeName") + "       "
                                + rs2.getString("episodeduration"));
            }

        } catch (Exception e) {
            System.out.println("There has been an error");
            System.out.println(e.getMessage());
        }
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Enter the Number you want to play");
        int a = sca.nextInt();
        return a;

    }

    public int displayByGenre() throws Exception {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("select * from podcasttable;");
        Display obj = new Display();
        obj.cowdisplay1();
        System.out.println("No." + "   " + "Genre");
        while (rs.next()) {
            System.out.println(rs.getInt("id") + "       " + rs.getString("genre"));
        }
        obj.cowdisplay2();
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Enter the Number you want to play");
        int a = sca.nextInt();
        return a;
    }

    public int playpodcast(String podcastPath, int a, String status1) {
        try {
            File podcastpath = new File(podcastPath);
            if (podcastpath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(podcastpath);
                Scanner t = new Scanner(System.in);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                long timeposition = 0;
                String status = "Playing";

                while (!status.equalsIgnoreCase("stop")) {
                    Display obj = new Display();
                    obj.cowdisplay1();
                    System.out.println("1. Pause");
                    System.out.println("2. Stop and Back");
                    System.out.println("3. Replay");
                    System.out.println("4. Forward/Rewind Few Seconds");
                    System.out.println("5. Resume");
                    System.out.println("6. Next");
                    System.out.println("7. Previous");
                    System.out.println("0. For out of the Program");
                    obj.cowdisplay2();
                    byte c = t.nextByte();
                    switch (c) {
                        case 1:
                            timeposition = clip.getMicrosecondPosition();
                            long tot = clip.getMicrosecondLength();
                            long micro = clip.getMicrosecondPosition();
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.stop();
                            break;
                        case 2:
                            clip.stop();
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            status = "stop";
                            return 0;
                        case 3:
                            clip.stop();
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.setMicrosecondPosition(0L);

                            clip.start();
                            break;
                        case 4:
                            System.out.println(
                                    "Enter Second to Forward or for Rewind Enter the second with (-ve symbol) ");
                            long s = t.nextLong();
                            timeposition = clip.getMicrosecondPosition();
                            clip.setMicrosecondPosition(timeposition + s * 1000000);
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.start();
                            break;
                        case 5:
                            clip.setMicrosecondPosition(timeposition);
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.start();
                            break;
                        case 6:
                            clip.stop();

                            a++;
                            Statement statement = getConnection().createStatement();
                            String query = "select songid from musictable where songid =(SELECT max(songid) from musictable)";
                            ResultSet rs = statement.executeQuery(query);
                            int ab = 0;
                            if (rs.next()) {
                                ab = rs.getInt("songid");
                            } else {
                                System.out.println("Enter the right path");
                            }
                            if (a <= ab) {
                                String ab1 = podcastpathfind(a);
                                playpodcast(ab1, a, "noraml");

                            } else {
                                System.out.println("You are at the end of the Playlist");
                                playpodcast(podcastPath, a, "noraml");
                            }
                            break;

                        case 7:
                            clip.stop();

                            a--;
                            statement = getConnection().createStatement();
                            query = "select songid from musictable where songid =(SELECT min(songid) from musictable)";
                            rs = statement.executeQuery(query);
                            ab = 0;
                            if (rs.next()) {
                                ab = rs.getInt("songid");
                            } else {
                                System.out.println("Enter the right path");
                            }
                            if (a >= ab) {
                                String ab1 = podcastpathfind(a);
                                playpodcast(ab1, a, "noraml");
                                clip.start();
                            } else {
                                System.out.println("This is the first song");
                                playpodcast(podcastPath, a, "noraml");
                            }
                            break;

                        case 0:
                            System.exit(0);

                        default:
                            System.out.println("Invalid Entry");
                    }

                }
            } else {
                System.out.println("Music file path is not found");
            }
        }

        catch (

        Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public String podcastpathfind(int a) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jukebox", "root", "admin");
        Statement statement = con.createStatement();
        String query = "select path from podcasttable where id =" + a + "";
        ResultSet rs = statement.executeQuery(query);
        String ab = " ";
        if (rs.next()) {
            ab = rs.getString("path");
        } else {
            System.out.println("Enter the correct path");
        }
        return ab;

    }
}
