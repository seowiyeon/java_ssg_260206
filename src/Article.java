public class Article {
    int id;
    String regDate;
    String subject;
    String content;
    int hit;

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
        hit++;
    }
}