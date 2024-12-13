using System;
using System.Collections.Generic;
using System.Data;
using Data;

namespace Business
{
    public class BookManage
    {
        public string Error { get; private set; }

        public bool CreateBook(Book book)
        {
            if (!ValidateBook(book))
                return false;

            string sql = $"INSERT INTO book (book_id, book_title, author, subject, page_count, ISBN) " +
                         $"VALUES ({book.BookId}, '{book.Title}', '{book.Author}', '{book.Subject}', {book.PageCount}, '{book.ISBN}')";

            int result = DataBase.ExecuteNonQuery(sql);

            if (result == -1)
            {
                Error = DataBase.Error;
                return false;
            }

            return true;
        }

        public List<Book> GetBooks()
        {
            string sql = "SELECT book_id, book_title, author, subject, page_count, ISBN FROM book";

            DataTable table = DataBase.ExecuteQuery(sql);
            if (table == null)
            {
                Error = DataBase.Error;
                return null;
            }

            List<Book> books = new List<Book>();
            foreach (DataRow row in table.Rows)
            {
                books.Add(new Book(
                    Convert.ToInt32(row["book_id"]),
                    row["book_title"].ToString(),
                    row["author"].ToString(),
                    row["subject"].ToString(),
                    Convert.ToInt32(row["page_count"]),
                    row["ISBN"].ToString()
                ));
            }

            return books;
        }

        public bool UpdateBook(Book book)
        {
            if (!ValidateBook(book))
                return false;

            string sql = $"UPDATE book SET book_title = '{book.Title}', author = '{book.Author}', subject = '{book.Subject}', " +
                         $"page_count = {book.PageCount}, ISBN = '{book.ISBN}' WHERE book_id = {book.BookId}";

            int result = DataBase.ExecuteNonQuery(sql);

            if (result == -1)
            {
                Error = DataBase.Error;
                return false;
            }

            return true;
        }

        public bool RemoveBook(int bookId)
        {
            string sql = $"DELETE FROM book WHERE book_id = {bookId}";

            int result = DataBase.ExecuteNonQuery(sql);

            if (result == -1)
            {
                Error = DataBase.Error;
                return false;
            }

            return true;
        }

        private bool ValidateBook(Book book)
        {
            if (string.IsNullOrWhiteSpace(book.Title))
            {
                Error = "The title of the book cannot be empty.";
                return false;
            }

            if (book.PageCount <= 0)
            {
                Error = "The number of pages must be greater than zero.";
                return false;
            }

            return true;
        }
    }
}
