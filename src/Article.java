public class Article {
    int id;
    String regDate;
    String subject;
    String content;
    int hit;

    public Article(int id, String regDate, String subject, String content) {
        this.id = id;
        this.regDate = regDate;
        this.subject = subject;
        this.content = content;
        this.hit = 0;
    }

    public void increaseHit() {
        hit++;
    }
}