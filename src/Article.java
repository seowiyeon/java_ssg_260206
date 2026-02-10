package ll.kor.java.ssg.dto;

public class Article {
    public int id;
    public String regDate;
    public String subject;
    public String content;
    public int hit;

    public Article(int id, String regDate, String subject, String content) {
        this(id, regDate, subject, content, 0);
    }

    public Article(int id, String regDate, String subject, String content, int hit) {
        this.id = id;
        this.regDate = regDate;
        this.subject = subject;
        this.content = content;
        this.hit = hit;
    }

    public void increaseHit() {
        this.hit++;
    }
}
