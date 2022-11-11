
import java.sql.*;
import java.util.Scanner;
import java.util.*;

public class UserPlaylist {
    Display display = new Display();
    Scanner sc = new Scanner(System.in);

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Jukebox", "root", "admin");
        return connection;

    }

    public void userplaylist(String us) throws Exception {
        Statement statement = getConnection().createStatement();
        try {
            ResultSet rs = statement
                    .executeQuery("select distinct(playlistname) from userplaylist where username='" + us + "'");
            System.out.println("----- Your Existing PlayLists -------");
            while (rs.next()) {
                System.out.println(rs.getString("playlistname"));

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createPlaylist(String us) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of new playlist => ");
        String newplaylist = scanner.nextLine();
        Statement statement = getConnection().createStatement();
        statement.executeUpdate(
                "insert into userplaylist(playlistname,username) values('" + newplaylist + "','" + us + "')");
        userplaylist(us);
    }

    // ------------------------------
    public void addToPlaylist(String us) throws Exception {
        Statement statement = getConnection().createStatement();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Name your Exiting Playlist");
        ResultSet rs = statement
                .executeQuery("select distinct(playlistname) from userplaylist where username = '" + us + "'");
        while (rs.next()) {
            System.out.println(rs.getString("playlistname"));
        }
        System.out.print("Enter the name of the playlist => ");
        String playlistname = scanner.nextLine();
        display.cowdisplay1();
        System.out.println(
                "Press 1 for Insert Song\nPress 2 for insert Podcast\nPress 4 for Back\nPress 0 for exit from Jukebox");
        display.cowdisplay2();
        int count = sc.nextInt();
        if (count == 1) {
            int a;
            do {
                System.out.println("No." + "     " + "Song Name");
                rs = statement.executeQuery("select songid, songname from musictable");
                while (rs.next()) {
                    System.out.println(rs.getInt("songid") + "    " + rs.getString("songname"));
                }
                System.out.println("Enter the song number you want to Insert");
                int songid = sc.nextInt();
                statement.executeUpdate("insert into userplaylist(playlistname,username,songid) values('"
                        + playlistname + "','" + us + "'," + songid + ")");
                display.cowdisplay1();
                System.out.println("Press 1 if you want to insert more songs\nIf not then press 0");
                display.cowdisplay2();
                a = sc.nextInt();

            } while (a == 1);

        } else if (count == 2) {
            int a;
            do {
                Statement statement1 = getConnection().createStatement();
                System.out.println("No." + "     " + "Podcast Name");
                ResultSet rs2 = statement1.executeQuery("select id, episodename from podcasttable");
                while (rs2.next()) {
                    System.out.println(rs2.getInt("id") + "    " + rs2.getString("episodename"));
                }
                System.out.println("Enter the Episode number you want to Insert");
                int id = sc.nextInt();
                statement.executeUpdate("insert into userplaylist(playlistname,username,id) values('"
                        + playlistname + "','" + us + "'," + id + ")");
                display.cowdisplay1();
                System.out.println("Press 1 if you want to insert more Podcast\nIf not then press 0");
                display.cowdisplay2();
                a = sc.nextInt();

            } while (a == 1);
        } else if (count == 0) {
            System.exit(count);
        } else {
            System.out.println("Enter the correct Input");
        }

    }

    // ----------------------------------------------------------------

    public List<String> showPlaylistSong(String us) throws Exception {
        Statement statement = getConnection().createStatement();
        List<String> list = new ArrayList<>();
        try {
            System.out.println("Name of your Exiting Playlist");
            ResultSet rs = statement
                    .executeQuery("select distinct(playlistname) from userplaylist where username = '" + us + "'");
            while (rs.next()) {
                System.out.println(rs.getString("playlistname"));
            }
            System.out.print("Enter the Playlist name you want to show => ");
            Scanner sc = new Scanner(System.in);
            String playlistname = sc.nextLine();
            Display obj = new Display();
            obj.cowdisplay1();
            ResultSet rs1 = statement.executeQuery(
                    "select distinct(userplaylist.songid), musictable.songname, musictable.path from userplaylist right join musictable on musictable.songid = userplaylist.songid where playlistname ='"
                            + playlistname + "'and username = '" + us + "'");
            System.out.println("No. " + "     " + "Song Name");
            while (rs1.next()) {
                System.out.println(rs1.getInt(1) + "  " + rs1.getString(2));
                list.add(rs1.getInt(1) + "," + rs1.getString(3));

            }
            obj.cowdisplay2();

        }

        catch (Exception e) {
            System.out.println("There has an error");
            e.getMessage();
        }
        return list;
    }

    // ----------------------------------------------------------------
    public List<String> showPlaylistPodcast(String us) throws Exception {
        Statement statement = getConnection().createStatement();
        List<String> list = new ArrayList<>();
        try {
            System.out.println("Name of your Exiting Playlist");
            ResultSet rs = statement
                    .executeQuery("select distinct(playlistname) from userplaylist where username = '" + us + "'");
            while (rs.next()) {
                System.out.println(rs.getString("playlistname"));
            }
            System.out.print("Enter the Playlist name you want to show => ");
            Scanner sc = new Scanner(System.in);
            String playlistname = sc.nextLine();
            System.out.println("No. " + "     " + "Episode Name");
            ResultSet rs1 = statement.executeQuery(
                    "select distinct(userplaylist.id), podcasttable.episodename from userplaylist left join podcasttable  on podcasttable.id = userplaylist.id where playlistname ='"
                            + playlistname + "'and username = '" + us + "'");
            while (rs1.next()) {
                System.out.println(rs1.getInt(1) + "  " + rs1.getString(2));
                list.add(rs1.getInt(1) + "," + rs1.getString(2));

            }
        }

        catch (Exception e) {
            System.out.println("There has an error");
            e.getMessage();
        }
        return list;
    }
    // ----------------------------------------------------------------

    public void playlistfunction(String us) throws Exception {
        Display obj = new Display();
        obj.cowdisplay1();
        System.out.println("=> Press 0 for exit from program");
        System.out.println("=> Create a New playlist Press 1");
        System.out.println("=> Add in the playlist Press 2");
        System.out.println("=> Play the Song playlist Press 3");
        obj.cowdisplay2();
        int count = sc.nextInt();
        if (count == 1) {
            userplaylist(us);
            createPlaylist(us);
        } else if (count == 2) {
            addToPlaylist(us);
        } else if (count == 3) {
            List<String> list = showPlaylistSong(us);
            System.out.println("Enter the No. of Song");
            Scanner sc = new Scanner(System.in);
            int count1 = sc.nextInt();
            PlayInPlaylist obj1 = new PlayInPlaylist();

            obj1.playsong(list,count1);

        }

        else if (count == 0) {
            System.exit(count);
        } else {
            System.out.println("Enter Correct Input");
        }
    }

}
