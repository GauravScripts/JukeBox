import java.util.List;

public class PlayInPlaylist {
    public void playsong(List<String> list, int count1) {

        try {
            Songs song = new Songs();
            int temp = -20;
            int count;
            int count2 = 0;
            for (String string : list) {
                if (Integer.parseInt(string.split(",")[0]) == count1) {
                    break;
                }
                count2++;
            }
            for (int i = count2; i < list.size(); i++) {

                String[] arr = list.get(i).split(",");
                int id = Integer.parseInt(arr[0]);
                String path = arr[1];
                temp = song.playmusic(path, id, "playlist");
                count = i;
                if (temp == -1) {
                    if (i == 0) {
                        i = -1;
                        continue;
                    }
                    i = i - 2;
                } else if (temp == 0) {
                    break;
                }

            }
            if (temp == list.size()) {
                System.out.println("You are at the end of the playlist");
            }
        } catch (Exception e) {
            System.out.println("You are the first song");
        }
    }
}
