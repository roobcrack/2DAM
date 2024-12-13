public class Book
{
    public int BookId { get; set; }
    public string Title { get; set; }
    public string Author { get; set; }
    public string Subject { get; set; }
    public int PageCount { get; set; }
    public string ISBN { get; set; }

    public Book(int bookId, string title, string author, string subject, int pageCount, string isbn)
    {
        BookId = bookId;
        Title = title;
        Author = author;
        Subject = subject;
        PageCount = pageCount;
        ISBN = isbn;
    }
}
