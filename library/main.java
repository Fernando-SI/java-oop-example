//perbaikan menambah addbook serta try-catch 
import java.util.Scanner;

class Main {

  static Scanner scan = new Scanner(System.in);
  static Library library = new Library();

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

  public static int chooseMenu() {
    System.out.print("choose menu : ");
    int pilihan = scan.nextInt();
    return pilihan;
  }
  public static void main(String[] args) {
    initLibraryData();

    String isContinue = "y";

    // menggunakan do-while
    do {
      showMenu();
      int selectedMenu = chooseMenu();

      switch (selectedMenu) {
          case 1:
              showBooks();
              break;
          case 2:
              showMembers();
              break;
          case 3:
              addMember();
              break;
          case 4:
              borrowBook();
              break;
          case 5:
              returnBook();
              break;
          case 6:
              addBook();
              break;
          default:
              System.out.println("Pilihan anda tidak terdapat di menu");
              break;
      }

      System.out.print("Continue? (y/n): ");
      isContinue = scan.next();
  } while (isContinue.equals("y"));
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
    member1.setId("1");
    member1.setName("aka");

    Member member2 = new Member();
    member2.setId("2");
    member2.setName("budi");

    Member member3 = new Member();
    member3.setId("3");
    member3.setName("tono");

    library.books.add(book1);
    library.books.add(book2);
    library.books.add(book3);

    library.members.add(member1);
    library.members.add(member2);
    library.members.add(member3);
  }

  // fungsi untuk menampilkan buku
  public static void showBooks() {
    for (Book book : library.books) {
        // Periksa apakah buku sedang dipinjam oleh anggota
        boolean isBorrowed = library.isBookBorrowed(book.getId());
        if (!isBorrowed) {
            System.out.println(book.getId() + " " + book.title);
        }
    }
}

  // fungsi untuk menampilkan anggota
  public static void showMembers() {
    for (Member member : library.members) {
      System.out.println(member.getId() + " " + member.getName());
    }
  }

  // fungsi untuk menambah anggota
  public static void addMember() {
    Member member = new Member();
    // penggunaan try catch apabila member sudah terdapat dalam sistem
    try {
        System.out.print("id : ");
        String memberId = scan.next();

        if (library.isMemberIdExist(memberId)) {
            throw new Exception("Member ID sudah ada");
        } else {
            member.setId(memberId);
        }

        System.out.print("name : ");
        member.setName(scan.next());

        library.addMember(member);
        System.out.println("Member berhasil ditambahkan!");
    } catch (Exception e) {
        System.out.println(e.getMessage());
    }
}

// fungsi untuk meminjam buku
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

// fungsi untuk mengembalikan buku
public static void returnBook() {
  System.out.print("id member : ");
  String memberId = scan.next();

  System.out.print("id book : ");
  String bookId = scan.next();

  library.receiveBook(bookId, memberId);
}

  // fungsi untuk menambahkan buku
  public static void addBook() {
    Book book = new Book();
    // penggunaan try catch untuk memeriksa apakah buku tersebut sudah  ada pada sistem
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