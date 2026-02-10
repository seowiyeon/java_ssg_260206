package ll.kor.java.ssg;

import ll.kor.java.ssg.dto.Article;
import ll.kor.java.ssg.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private List<Article> articles;
    private int lastArticleId;

    public App() {
        articles = new ArrayList<>();
        lastArticleId = 0;
    }

    public void start() {
        IO.println("== 프로그램 시작 ==");

        makeTestData();

        Scanner sc = new Scanner(System.in);

        while (true) {
            IO.print("명령어) ");
            String cmd = sc.nextLine().trim();

            if (cmd.isEmpty()) continue;
            if (cmd.equals("exit")) break;

            if (cmd.equals("article write")) {
                doWrite(sc);
            } else if (cmd.equals("article list")) {
                doList();
            } else if (cmd.startsWith("article detail ")) {
                doDetail(cmd);
            } else if (cmd.startsWith("article delete ")) {
                doDelete(cmd);
            } else if (cmd.startsWith("article modify ")) {
                doModify(cmd, sc);
            } else if (cmd.startsWith("article search ")) {
                doSearch(cmd);
            } else {
                IO.println("존재하지 않는 명령어입니다.");
            }
        }

        IO.println("== 프로그램 끝 ==");
        sc.close();
    }

    private void doWrite(Scanner sc) {
        int id = lastArticleId + 1;
        String regDate = Util.getNowDateStr();

        IO.print("제목 : ");
        String subject = sc.nextLine().trim();
        IO.print("내용 : ");
        String content = sc.nextLine().trim();

        Article article = new Article(id, regDate, subject, content);
        articles.add(article);

        lastArticleId = id;

        IO.println(id + "번 글이 생성되었습니다.");
    }

    private void doList() {
        if (articles.isEmpty()) {
            IO.println("게시물이 없습니다.");
            return;
        }

        IO.println("번호 | 조회 | 제목");
        for (int i = articles.size() - 1; i >= 0; i--) {
            Article a = articles.get(i);
            IO.println(String.format("%d | %d | %s", a.id, a.hit, a.subject));
        }
    }

    private void doDetail(String cmd) {
        Article article = findArticleByCmd(cmd);
        if (article == null) return;

        article.increaseHit();

        IO.println("번호 : " + article.id);
        IO.println("날짜 : " + article.regDate);
        IO.println("제목 : " + article.subject);
        IO.println("내용 : " + article.content);
        IO.println("조회 : " + article.hit);
    }

    private void doDelete(String cmd) {
        Article article = findArticleByCmd(cmd);
        if (article == null) return;

        articles.remove(article);
        IO.println(article.id + "번 게시물이 삭제되었습니다.");
    }

    private void doModify(String cmd, Scanner sc) {
        Article article = findArticleByCmd(cmd);
        if (article == null) return;

        IO.print("제목 : ");
        String subject = sc.nextLine().trim();
        IO.print("내용 : ");
        String content = sc.nextLine().trim();

        article.regDate = Util.getNowDateStr();
        article.subject = subject;
        article.content = content;

        IO.println(article.id + "번 게시물이 수정되었습니다.");
    }

    private void doSearch(String cmd) {
        String keyword = cmd.replaceFirst("article search ", "").trim();

        if (keyword.isEmpty()) {
            IO.println("검색어를 입력해주세요.");
            return;
        }

        IO.println("검색 결과:");
        for (Article a : articles) {
            if (a.subject.contains(keyword) || a.content.contains(keyword)) {
                IO.println(String.format("%d | %d | %s", a.id, a.hit, a.subject));
            }
        }
    }

    private Article findArticleByCmd(String cmd) {
        String[] bits = cmd.split(" ");
        if (bits.length < 3) {
            IO.println("게시물 번호를 입력해주세요.");
            return null;
        }

        int id;
        try {
            id = Integer.parseInt(bits[2]);
        } catch (NumberFormatException e) {
            IO.println("유효한 숫자를 입력해주세요.");
            return null;
        }

        Article article = getArticleById(id);
        if (article == null) {
            IO.println(id + "번 게시물이 존재하지 않습니다.");
            return null;
        }

        return article;
    }

    private Article getArticleById(int id) {
        for (Article a : articles) {
            if (a.id == id) return a;
        }
        return null;
    }

    private void makeTestData() {
        IO.println("테스트를 위한 데이터를 생성합니다.");

        articles.add(new Article(1, Util.getNowDateStr(), "제목 1", "내용 1", 10));
        articles.add(new Article(2, Util.getNowDateStr(), "제목 2", "내용 2", 43));
        articles.add(new Article(3, Util.getNowDateStr(), "제목 3", "내용 3", 33));

        lastArticleId = 3;
    }
}
