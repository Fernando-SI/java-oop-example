//perbaikan menambah addbook serta try-catch 
import java.util.Scanner;

class Main {

  static Scanner scan = new Scanner(System.in);
  static Library library = new Library();

  public static void main(String[] args) {
    initLibraryData();

    String isContinue = "y";

    while (isContinue.equals("y")) {
      showMenu();
      int selectedMenu = chooseMenu();

      if (selectedMenu == 1) {
        showBooks();
      } else if (selectedMenu == 2) {
        showMembers();
      } else if (selectedMenu == 3) {
        addMember();
      } else if (selectedMenu == 4) {
        borrowBook();
      } else if (selectedMenu == 5) {
        returnBook();
      } else if (selectedMenu == 6) {
        addBook();    
      } else {
        System.out.println("wrong input");
      }

      System.out.print("continue ? ");
      isContinue = scan.next();
    }
  }

  public static void showMenu() {
    System.out.println("================================");
    System.out.println("1. show books list");
    System.out.println("2. show members list");
    System.out.println("3. add member");
    System.out.println("4. borrow book");
    System.out.println("5. return book");
    System.out.println("6. add book");
    System.out.println("================================");
  }

  public static void initLibraryData() {
    Book book1 = new Book();
    book1.setId("1");
    book1.setTitle ("pemrograman java");

    Book book2 = new Book();
    book2.setId("2");
    book2.setTitle("pemrograman oop");

    Book book3 = new Book();
    book3.setId("3");
    book3.setTitle ("pemrograman android");

    Member member1 = new Member();
    member1.id = "1";
    member1.name = "aka";

    Member member2 = new Member();
    member2.id = "2";
    member2.name = "budi";

    Member member3 = new Member();
    member3.id = "3";
    member3.name = "tono";

    library.books.add(book1);
    library.books.add(book2);
    library.books.add(book3);

    library.members.add(member1);
    library.members.add(member2);
    library.members.add(member3);
  }

  public static int chooseMenu() {
    System.out.print("choose menu : ");
    int pilihan = scan.nextInt();
    return pilihan;
  }

  public static void showBooks() {
    for (Book book : library.books) {
        // Periksa apakah buku sedang dipinjam oleh anggota
        boolean isBorrowed = library.isBookBorrowed(book.getId());
        if (!isBorrowed) {
            System.out.println(book.getId() + " " + book.title);
        }
    }
}


  public static void showMembers() {
    for (Member member : library.members) {
      System.out.println(member.id + " " + member.name);
    }
  }

  public static void addMember() {
    Member member = new Member();

    try {
        System.out.print("id : ");
        String memberId = scan.next();

        if (library.isMemberIdExist(memberId)) {
            throw new Exception("Member ID sudah ada");
        } else {
            member.id = memberId;
        }

        System.out.print("name : ");
        member.name = scan.next();

        library.addMember(member);
        System.out.println("Member berhasil ditambahkan!");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

public static void borrowBook() {
  System.out.print("id member : ");
  String memberId = scan.next();

  System.out.print("id book : ");
  String bookId = scan.next();

  // Periksa apakah ID buku dan ID anggota ada dalam sistem
  if (!library.isBookIdExist(bookId)) {
      System.out.println("Buku dengan ID " + bookId + " tidak ada dalam sistem.");
      return;
  }

  if (!library.isMemberIdExist(memberId)) {
      System.out.println("Anggota dengan ID " + memberId + " tidak ada dalam sistem.");
      return;
  }

  library.giveBook(memberId, bookId);
}

public static void returnBook() {
  System.out.print("id member : ");
  String memberId = scan.next();

  System.out.print("id book : ");
  String bookId = scan.next();

  library.receiveBook(bookId, memberId);
}


  public static void addBook() {
    Book book = new Book();
    try {
      System.out.print("id buku : ");
      String bookId = scan.next();

      if (library.isBookIdExist(bookId)) {
        throw new Exception("Book ID sudah ada");
      } else {
        book.setId(bookId);
      }

      System.out.print("judul : ");
      book.title = scan.next();

      library.addBook(book);
      System.out.println("Book berhasil ditambahkan!");

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    }
}