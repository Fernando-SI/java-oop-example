import java.util.ArrayList;

class Library {
  public ArrayList<Book> books = new ArrayList<Book>();
  public ArrayList<Member> members = new ArrayList<Member>();
  
  public void addMember(Member member) {
    this.members.add(member);
  }

  public void addBook(Book book) {
    for (Book b : this.books) {
        if (b.getId().equals(book.getId())) {
            System.out.println("Book ID sudah ada!");
            return;
        }
    }
    Book b = new Book();
    b.setId(book.getId());
    b.title = book.title;
    this.books.add(b);
}



public Boolean isMemberIdExist(String id) {
  for (Member member : this.members) {
    if (member.getId().equals(id)) {
      return true;
    }
  }
  return false;
}


  public void giveBook(String bookId, String memberId) {
    Book book = this.getBookById(bookId);
    this.books.remove(book);

    Member member = this.getMemberById(memberId);
    int memberIndex = this.getMemberIndex(member);
    this.members.get(memberIndex).borrowedBooks.add(book);
  }

  public void receiveBook(String bookId, String memberId) {

    Member member = this.getMemberById(memberId);
    int memberIndex = this.getMemberIndex(member);

    Book book =this.members.get(memberIndex).getBookById(bookId);

    this.books.add(book);
    this.members.get(memberIndex).borrowedBooks.remove(book);
  }

  private int getMemberIndex(Member member) {
    return this.members.indexOf(member);
  }

  private Member getMemberById(String id) {
    for (Member member : this.members) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }

  private Book getBookById(String id) {
    for (Book book : this.books) {
      if (book.getId().equals(id)) {
        return book;
      }
    }
    return null;
  }

  public boolean isBookIdExist(String bookId) {
    for (Book book : this.books) {
      if (book.getId().equals(bookId)) {
        return true;
      }
    }
    return false;
  }

  public boolean isBookBorrowed(String id) {
    return false;
  }  
}