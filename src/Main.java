//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
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
