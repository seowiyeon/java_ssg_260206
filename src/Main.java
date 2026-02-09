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

            if ( cmd.equals("article write") ) {
                int id = lastArticleId + 1;
                String regDate = Util.getNowDateStr();
                IO.print("제목 : ");
                String subject = sc.nextLine();
                IO.print("내용 : ");
                String content = sc.nextLine();

                Article article = new Article(id, regDate, subject, content);
                articles.add(article);

                lastArticleId = id;

                IO.println(String.format("%d번 글이 생성되었습니다.", id));
            } else if ( cmd.equals("article list") ) {
                if ( articles.isEmpty() ) {
                    IO.println("게시물이 없습니다.");
                    continue;
                }

                IO.println("번호 | 제목");
                for ( int i = articles.size() - 1; i >= 0; i-- ) {
                    Article article = articles.get(i);

                    IO.println(String.format("%d   | %s", article.id, article.subject));
                }
            } else if ( cmd.startsWith("article detail ") ) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.id == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if ( foundArticle == null ) {
                    IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
                    continue;
                }

                IO.println(String.format("번호 : %d", foundArticle.id));
                IO.println(String.format("날짜 : %s", foundArticle.regDate));
                IO.println(String.format("제목 : %s", foundArticle.subject));
                IO.println(String.format("내용 : %s", foundArticle.content));

            } else if ( cmd.startsWith("article delete ") ) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                int foundIndex = -1;

                for (int i = 0; i < articles.size(); i++ ) {
                    Article article = articles.get(i);

                    if (article.id == id) {
                        foundIndex = i;
                        break;
                    }
                }

                if ( foundIndex == -1 ) {
                    IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
                    continue;
                }

                articles.remove(foundIndex);

                IO.println(String.format("%d번 게시물이 삭제되었습니다.", id));
            }
            else if ( cmd.startsWith("article modify ") ) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundArticle = null;

                for (Article article : articles) {
                    if (article.id == id) {
                        foundArticle = article;
                        break;
                    }
                }

                if ( foundArticle == null ) {
                    IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
                    continue;
                }


                IO.print("제목 : ");
                String subject = sc.nextLine();
                IO.print("내용 : ");
                String content = sc.nextLine();

                foundArticle.regDate = Util.getNowDateStr();
                foundArticle.subject = subject;
                foundArticle.content = content;

                IO.println(String.format("%d번 게시물이 수정되었습니다.", id));
            }

            else IO.println("존재하지 않는 명령어입니다.");
        }

        IO.println("== 프로그램 끝 ==");
        sc.close();
    }
}