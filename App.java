import java.util.Scanner;

public class App {

    // TODO: Next song and previous song try with the loop and change the value
    //TODO: 
    public static void main(String[] args) throws Exception {
        Display obj = new Display();
        JDBC jdbc = new JDBC();
        Scanner sc = new Scanner(System.in);

        obj.display();
        int check = sc.nextInt();
        while (check != 0) {
            if (check == 1) {
                jdbc.SignUP();
                System.out.println("For sign In Press 1 \nFor SignUp Press 2\nand for Exit Press 0");
                check = sc.nextInt();
                if (check == 1) {
                    jdbc.authenticate();
                } else if (check == 2) {
                    jdbc.SignUP();
                } else {
                    System.exit(0);
                }
            } else if (check == 2) {
                String[] s = jdbc.authenticate();
                if (s[0].equalsIgnoreCase("login successfully")) {
                    System.out.println(check);
                    jdbc.authenticate();
                    obj.display();
                    check = sc.nextInt();
                    if (check == 1) {
                        jdbc.SignUP();
                        System.out.println("For sign In Press 1 and for Exit Press 0");
                        check = sc.nextInt();
                        if (check != 0) {
                            jdbc.authenticate();
                        }
                    } else {
                        System.out.println(
                                "Try to login Again Maybe your username and password is incorrect¯\\_(ツ)_/¯");
                        jdbc.authenticate();
                    }

                }
            }
        }
    }
}
