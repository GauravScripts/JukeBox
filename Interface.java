import java.util.Scanner;

public class Interface {
    public void displayinterface(String username) throws Exception {
        Display obj = new Display();
        Scanner sc = new Scanner(System.in);
        obj.cowdisplay1();
        System.out.println(
                "If you want hear Music Press 1\nIf you want to hear Podcast then Press 2\nEnter into Playlist Press 3\nIf you want to exit press 4");
        obj.cowdisplay2();
        int check1 = sc.nextInt();
        while (check1 != 0) {

            if (check1 == 1) {
                Songs song = new Songs();
                System.out.println(
                        "Press 1 for Go to Main Menu\nPress 2 for Display by Music Name \nPress 3 for Display by Artist Name \nPress 4 for Display Album Name");
                int e = sc.nextInt();
                if (e != 1) {
                    if (e == 2) {
                        int b = song.Displaymusic();
                        song.playmusic(song.musicpathfind(b), b, "noraml");
                    } else if (e == 3) {
                        int b = song.DisplaybyArtist();
                        song.playmusic(song.musicpathfind(b), b, "noraml");
                    } else if (e == 4) {
                        int b = song.DisplayAlbumName();
                        song.playmusic(song.musicpathfind(b), b, "noraml");
                    } else {
                        System.out.println("Enter the correct Input");
                    }
                } else if (e == 1) {
                    displayinterface(username);
                } else {
                    System.out.println("Correct Input");
                }
            } else if (check1 == 2) {
                Podcast p = new Podcast();

                obj.cowdisplay1();
                System.out.println(
                        "Press 1 for Go back to Main Menu\nPress 2 for Display by Podcast Episode Name \nPress 3 for Display by Podcast Name \nPress 4 for Display Podcast by genre");
                obj.cowdisplay2();
                int e = sc.nextInt();
                if (e != 1) {
                    if (e == 2) {
                        int f = p.DisplayPodcastEpisode();
                        p.playpodcast(p.podcastpathfind(f), f, "normal");
                    } else if (e == 3) {
                        int f = p.displayByPodcastName();
                        p.playpodcast(p.podcastpathfind(f), f, "noraml");
                    } else if (e == 4) {
                        int f = p.displayByGenre();
                        p.playpodcast(p.podcastpathfind(f), f, "noraml");
                    } else {
                        System.out.println("Enter the Correct Value");
                    }
                } else if (e == 1) {
                    displayinterface(username);
                }
            } else if (check1 == 3) {
                UserPlaylist playlist = new UserPlaylist();
                playlist.playlistfunction(username);

            } else if (check1 == 4) {
                System.exit(0);
            } else {
                System.out.println("Enter the Correct Value");

            }

        }

    }
}
