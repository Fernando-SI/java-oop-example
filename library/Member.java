import java.util.ArrayList;

abstract class People{
  public abstract void receiveBook(Book book);
  public abstract void giveBook(Book book);
  public abstract Book getBookById(String bookId);
}

class Member extends People {
  private String id;
  private String name;
  public ArrayList<Book> borrowedBooks = new ArrayList<Book>();

  @Override
  public void receiveBook(Book book) {
    this.borrowedBooks.add(book);
  }

  @Override
  public void giveBook(Book book) {
    this.borrowedBooks.remove(book);
  }

  @Override
  public Book getBookById(String bookId) {
    for (Book book : borrowedBooks) {
      if (book.getId().equals(bookId)) {
        return book;
      }
    }
    return null;
  } 

  public void setId(String id){
    this.id = id;
  }

  public String getId(){
    return id;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getName(){
    return name;
  }

  public boolean isBookBorrowed(String bookId) {
    return false;
  }
}