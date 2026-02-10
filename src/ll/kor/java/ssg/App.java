package ll.kor.java.ssg;

import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    List<Article> articles;

    public App() {
        articles = new ArrayList<>();
    }

    void start() {
        IO.println("== 프로그램 시작 ==");

        makeTestData();

        Scanner sc = new Scanner(System.in);

        int lastArticleId = 3;

        while (true) {
            IO.print("명령어) ");
            String cmd = sc.nextLine();
            cmd = cmd.trim(); // 앞뒤에 쓸데없는 공백을 제거

            if (cmd.isEmpty()) continue;
            if (cmd.equals("exit")) break;

            if (cmd.equals("article write")) {
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
            } else if (cmd.startsWith("article list")) {
                if (articles.isEmpty()) {
                    IO.println("게시물이 없습니다.");
                    continue;
                }

                String searchKeyword = cmd.substring("article list".length()).trim();
                List<Article> forListArticles = articles;

//                if ( searchKeyword.length() > 0 ) {
                if ( !searchKeyword.isEmpty() ) {
                    forListArticles =  new ArrayList<>();

                    for ( Article article : articles ) {
                        if ( article.subject.contains(searchKeyword) ) {
                            forListArticles.add(article);
                        }
                    }

                    if ( forListArticles.isEmpty() ) {
                        IO.println("검색 결과가 존재하지 않습니다.");
                        continue;
                    }
                }

                IO.println("번호 | 조회 | 제목");
                for (int i = forListArticles.size() - 1; i >= 0; i--) {
                    Article article = forListArticles.get(i);

                    IO.println(String.format("%d   | %d   | %s", article.id, article.hit, article.subject));
                }
            } else if (cmd.startsWith("article detail ")) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
                    IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
                    continue;
                }

                foundArticle.increaseHit();

                IO.println(String.format("번호 : %d", foundArticle.id));
                IO.println(String.format("날짜 : %s", foundArticle.regDate));
                IO.println(String.format("제목 : %s", foundArticle.subject));
                IO.println(String.format("내용 : %s", foundArticle.content));
                IO.println(String.format("조회 : %d", foundArticle.hit));

            } else if (cmd.startsWith("article delete ")) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                int foundIndex = getArticleIndexById(id);

                if (foundIndex == -1) {
                    IO.println(String.format("%d번 게시물은 존재하지 않습니다.", id));
                    continue;
                }

                articles.remove(foundIndex);

                IO.println(String.format("%d번 게시물이 삭제되었습니다.", id));
            } else if (cmd.startsWith("article modify ")) {
                String[] cmdBits = cmd.split(" ");
                int id = Integer.parseInt(cmdBits[2]);

                Article foundArticle = getArticleById(id);

                if (foundArticle == null) {
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
            } else IO.println("존재하지 않는 명령어입니다.");
        }

        IO.println("== 프로그램 끝 ==");
        sc.close();
    }

    private int getArticleIndexById(int id) {
        int i = 0;
        for (Article article : articles) {
            if (article.id == id) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private Article getArticleById(int id) {
        int index = getArticleIndexById(id);

        if ( index != -1 ) {
            return articles.get(index);
        }

        return null;
    }

    private void makeTestData() {
        IO.println("테스트를 위한 데이터를 생성합니다.");

        articles.add(new Article(1, Util.getNowDateStr(), "제목 1", "내용 1", 10));
        articles.add(new Article(2, Util.getNowDateStr(), "제목 2", "내용 2", 43));
        articles.add(new Article(3, Util.getNowDateStr(), "제목 3", "내용 3", 33));
    }
}