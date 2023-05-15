import java.util.ArrayList;

class Member {
  public String id;
  public String name;
  public ArrayList<Book> borrowedBooks = new ArrayList<Book>();

  public void receiveBook(Book book) {
    this.borrowedBooks.add(book);
  }

  public void giveBook(Book book) {
    this.borrowedBooks.remove(book);
  }

  public Book getBookById(String bookId) {
    for (Book book : borrowedBooks) {
      if (book.getId().equals(bookId)) {
        return book;
      }
    }
    return null;
  } 

  public void setId(String string) {
  }
}