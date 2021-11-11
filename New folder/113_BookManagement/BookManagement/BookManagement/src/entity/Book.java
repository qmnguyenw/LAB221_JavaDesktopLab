package entity;
public class Book {
    private String code;
    private String name;
    private String author;
    private String publisher;
    private int publishedYear;
    private boolean rent;

    public Book(String code, String name, String author, String publisher, int publishedYear, boolean rent) {
        this.code = code;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.publishedYear = publishedYear;
        this.rent = rent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public boolean isRent() {
        return rent;
    }

    public void setRent(boolean rent) {
        this.rent = rent;
    }
}
