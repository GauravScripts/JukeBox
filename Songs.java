
import java.io.File;
import java.sql.*;
import java.util.Scanner;

import javax.sound.sampled.*;

public class Songs {
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jukebox", "root", "admin");
        return connection;

    }

    public static int Displaymusic() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("Select * from musicTable");
        Display obj = new Display();
        obj.cowdisplay1();
        System.out.println("No. " + "     " + "Song Name ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "         " + rs.getString(2));
        }
        obj.cowdisplay2();
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Enter the Number you want to play");
        int a = sca.nextInt();
        return a;

    }

    public int DisplaybyArtist() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("Select * from musicTable");
        Display obj = new Display();
        obj.cowdisplay1();
        System.out.println("No. " + "     " + "Artist Name ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "         " + rs.getString("artistname"));
        }
        obj.cowdisplay2();
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Enter the Number you want to play");
        int a = sca.nextInt();
        return a;
    }

    public int DisplayAlbumName() throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        ResultSet rs = statement.executeQuery("Select * from musicTable");
        Display obj = new Display();
        obj.cowdisplay1();
        System.out.println("No. " + "     " + "Album Name ");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + "         " + rs.getString("albumname"));
        }
        obj.cowdisplay2();
        Scanner sca = new Scanner(System.in);
        System.out.println();
        System.out.println("----------------------------------");
        System.out.println("Enter the Number you want to play");
        int a = sca.nextInt();
        return a;
    }

    public int playmusic(String musiclocation, int a, String status1) {
        try {
            File musicPath = new File(musiclocation);
            if (musicPath.exists()) {
                Scanner t = new Scanner(System.in);
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
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
                    System.out.println("4. Forward/Rewind");
                    System.out.println("5. Resume");
                    System.out.println("6. Next");
                    System.out.println("7. Previous");
                    System.out.println("0. For out of Program");
                    obj.cowdisplay2();
                    byte c = t.nextByte();
                    switch (c) {
                        case 1:
                            timeposition = clip.getMicrosecondPosition();
                            long tot = clip.getMicrosecondLength();
                            long micro = clip.getMicrosecondPosition();// microseconds to
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.stop();
                            break;
                        case 2:
                            clip.stop();
                            status = "stop";
                            clip.setMicrosecondPosition(timeposition);
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();// microseconds to
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);

                            return 0;
                        case 3:
                            clip.stop();
                            clip.setMicrosecondPosition(0L);
                            clip.setMicrosecondPosition(timeposition);
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();// microseconds to
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.start();
                            break;
                        case 4:
                            System.out.println(
                                    "Enter Second to Forward or for Backword Enter the second with '-' symbol ");
                            long s = t.nextLong();
                            timeposition = clip.getMicrosecondPosition();
                            clip.setMicrosecondPosition(timeposition + s * 1000000);
                            clip.setMicrosecondPosition(timeposition);
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();// microseconds to
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.start();
                            break;
                        case 5:
                            clip.setMicrosecondPosition(timeposition);
                            tot = clip.getMicrosecondLength();
                            micro = clip.getMicrosecondPosition();// microseconds to
                            System.out.println("Total Time of the Music => " + clip.getMicrosecondLength() / 1000000);
                            System.out.println("played in seconds => " + micro / 1000000);
                            System.out.println("remaining time for this song => " + (tot - micro) / 1000000);
                            clip.start();
                            break;
                        case 6:
                            clip.stop();
                            if (status1 =="noraml") {
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
                                    String ab1 = musicpathfind(a);
                                    playmusic(ab1, a, "noraml");
                                    break;
                                } else {
                                    System.out.println("You are at the end of the Playlist");
                                    playmusic(musiclocation, a, "noraml");
                                    break;
                                }

                            } else if (status1.equals("playlist")) {
                                
                                return a;
                            }
                        case 7:
                            clip.stop();
                            if (status1 =="noraml") {
                                a--;
                                Statement statement = getConnection().createStatement();
                                String query = "select songid from musictable where songid =(SELECT min(songid) from musictable)";
                                ResultSet rs = statement.executeQuery(query);
                                int ab = 0;
                                if (rs.next()) {
                                    ab = rs.getInt("songid");
                                } else {
                                    System.out.println("Enter the right path");
                                }
                                if (a >= ab) {

                                    playmusic(musiclocation, a, "noraml");
                                    clip.start();

                                } else {
                                    System.out.println("This is the first song");
                                    playmusic(musiclocation, a, "noraml");
                                }
                                break;
                            } else if (status1 == "playlist") {
                                return -1;
                            }

                        case 0:
                            System.exit(0);
                        default:
                            System.out.println("Invalid Entry");
                    }

                }
            } else {
                System.out.println("Music file path is not found");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static String musicpathfind(int a) throws SQLException, ClassNotFoundException {
        Statement statement = getConnection().createStatement();
        String query = "select path from musicTable where songid =" + a;
        ResultSet rs = statement.executeQuery(query);
        String ab = " ";
        if (rs.next()) {
            ab = rs.getString("path");
        } else {
            System.out.println("Enter the right path");
        }
        return ab;

    }

}