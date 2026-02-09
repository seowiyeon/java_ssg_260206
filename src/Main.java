import java.util.Scanner;

public class Main {
    static void main() {
        System.out.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);

        IO.print("명령어) ");
        String cmd = sc.nextLine();
        IO.println(String.format("입력된 명령어 : %s\n", cmd));


        System.out.println("== 프로그램 끝 ==");
        sc.close();
    }
}