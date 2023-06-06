class Book {
  private String Id;
  private String title;

  public void setId(String bookId){
    this.Id = bookId;
  }

  String getId(){
    return Id;
  }

  public void setTitle(String title){
    this.title = title;
  }

  String getTittle(){
    return title;
  }

}