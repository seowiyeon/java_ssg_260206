import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() {
        IO.println("== 프로그램 시작 ==");
        Scanner sc = new Scanner(System.in);

        int lastArticleId = 0;
        List<Article> articles = new ArrayList<>();

        while ( true ) {
            IO.print("명령어) ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 앞뒤에 쓸데없는 공백을 제거

            // if ( cmd.length() == 0 ) { // 아래와 똑같이 작동
            if ( cmd.isEmpty() ) continue;
            if ( cmd.equals("exit") ) break;

            if ( cmd.equals("write") ) {
                int id = lastArticleId + 1;
                IO.print("제목 : ");
                String subject = sc.nextLine();
                IO.print("내용 : ");
                String content = sc.nextLine();

                Article article = new Article(id, subject, content);
                articles.add(article);

                lastArticleId = id;

                IO.println(String.format("%d번 글이 생성되었습니다.", id));
            } else if ( cmd.equals("list") ) {
                if ( articles.size() == 0 ) {
                    IO.println("게시물이 없습니다.");
                    continue;
                }

                IO.println("번호 | 제목");
                for ( int i = articles.size() - 1; i >= 0; i-- ) {
                    Article article = articles.get(i);

                    IO.println(String.format("%d   | %s", article.id, article.subject));
                }
            }  else IO.println("존재하지 않는 명령어입니다.");
        }

        IO.println("== 프로그램 끝 ==");
        sc.close();
    }
}