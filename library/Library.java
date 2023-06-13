import java.util.ArrayList;

class Library {
  public ArrayList<Book> books = new ArrayList<Book>();
  public ArrayList<Member> members = new ArrayList<Member>();

  // Pemeriksaan apakah member ada
  public Boolean isMemberIdExist(String id) {
    boolean isExist = false;
    for (Member member : this.members) {
    if (member.getId().equals(id)) {
      System.out.println("ID member telah digunakan");
      isExist= true;
    }
  }
  return isExist;
}

  // Menambah member
  public void addMember(Member member) {
    if(!isMemberIdExist(member.getId()));
    this.members.add(member);
  }

  public void addMember(){
    System.out.println("Ini overload");
    }


// ---------------------------------------------------\\


// Peminjaman Buku (cek id)
public boolean isBookIdExist(String id) {
    Boolean isExist = false;
    for (Book book : this.books) {
      if (book.getId().equals(id)) {
        isExist = true;
      }
    }
    return isExist;
  }

// cek buku dipinjam/tidak, menambah buku)
public boolean isBookBorrowed(String id){
  for (Member member : this.members){
    if (member.getBookById(id) != null){
      return true;
    }
  }
  return false;
}

// Menambah buku (memastikan apakah buku sudah ada/belum lalu sedang dipinjam/tidak)
public void addBook(Book book) {
    if (isBookBorrowed(book.getId())){
      System.out.println("Book id sedang dipinjam oleh user lain");
    }else if(isBookIdExist(book.getId())){
      System.out.println("Book id sudah ada");
    }else{
      this.books.add(book);
    }
    
    // for (Book b : this.books) {
    //     if (b.getId().equals(book.getId())) {
    //         System.out.println("Book ID sudah ada!");
    //         return;
    //     }
    // }
    // Book b = new Book();
    // b.setId(book.getId());
    // b.setTitle(book.getTittle());
    // this.books.add(b);
}
   
// ---------------------------------------------------\\

  // fungsinya untuk mendapatkan index member
  private int getMemberIndex(Member member) {
    return this.members.indexOf(member);
  }

  // fungsinya untuk mendapatkan Id member
  private Member getMemberById(String id) {
    for (Member member : this.members) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    System.out.println("Anda salah menginput ID Member");
    return null;
  }
  
  // fungsinya untuk mendapatkan Id buku
  private Book getBookById(String bookId) {
    for (Book book : this.books) {
      if (book.getId().equals(bookId)) {
        return book;
      }
    }
    System.out.println("Anda salah menginput ID Buku");
    return null;
  }  

  // method yang digunakan pada saat user mengembalikan buku
  public void receiveBook(String memberId, String bookId) {
    try{
    Member member = this.getMemberById(memberId);
    int memberIndex = this.getMemberIndex(member);
    Book book = this.members.get(memberIndex).getBookById(bookId);
    
    if(book != null){
    this.books.add(book);
    System.out.println("Buku berhasil dikembalikan");
    }else{
      System.out.println("Input ID Member atau ID Buku anda salah");
    }
    this.members.get(memberIndex).borrowedBooks.remove(book);
  } catch (NullPointerException e) {
    System.out.println("Terjadi error pada inputan");
  }
  }  

  //method givebook akan digunakan apabila user ingin meminjam buku
  public void giveBook(String memberId, String bookId) {
    Book book = this.getBookById(bookId);
    this.books.remove(book);

    Member member = this.getMemberById(memberId);
    int memberIndex = this.getMemberIndex(member);
    this.members.get(memberIndex).borrowedBooks.add(book);
  }
}