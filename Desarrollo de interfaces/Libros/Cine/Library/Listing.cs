using System;
using System.Collections.Generic;
using System.Windows.Forms;
using Business;  
 
namespace Presentation
{
    public partial class Listing : Form
    {
        private BookManage _bookManage;

        public Listing()
        {
            InitializeComponent();
            _bookManage = new BookManage();
        }

         private void Listing_Load(object sender, EventArgs e)
        {
            lvBooks.View = View.Details;
            lvBooks.Columns.Add("Title", 200);
            lvBooks.Columns.Add("Author", 150);
            lvBooks.Columns.Add("Subject", 150);
            lvBooks.Columns.Add("Page Count", 100);
            lvBooks.Columns.Add("ISBN", 150);
            lvBooks.FullRowSelect = true;

             LoadBooks();
        }

         private void LoadBooks()
        {
            lvBooks.Items.Clear(); 
            List<Book> books = _bookManage.GetBooks(); 

            if (books != null)
            {
                 foreach (Book book in books)
                {
                    ListViewItem item = new ListViewItem(book.BookId.ToString()); 
                    item.SubItems.Add(book.Title);         
                    item.SubItems.Add(book.Author);        
                    item.SubItems.Add(book.Subject);        
                    item.SubItems.Add(book.PageCount.ToString());  
                    item.SubItems.Add(book.ISBN);           

                     lvBooks.Items.Add(item);
                }
            }
            else
            {
                 MessageBox.Show("Error loading books: " + _bookManage.Error);
            }
        }

         private void btnAdd_Click(object sender, EventArgs e)
        {
             AddBook addForm = new AddBook();
            if (addForm.ShowDialog() == DialogResult.OK)
            {
                 LoadBooks();
            }
        }

         private void btnModify_Click(object sender, EventArgs e)
        {
             if (lvBooks.SelectedItems.Count == 0)
            {
                MessageBox.Show("Please select a book to modify.");
                return;
            }

            int bookId = Convert.ToInt32(lvBooks.SelectedItems[0].Text);  
            string title = lvBooks.SelectedItems[0].SubItems[1].Text;
            string author = lvBooks.SelectedItems[0].SubItems[2].Text;
            string subject = lvBooks.SelectedItems[0].SubItems[3].Text;
            int pageCount = Convert.ToInt32(lvBooks.SelectedItems[0].SubItems[4].Text);
            string isbn = lvBooks.SelectedItems[0].SubItems[5].Text;

             Book selectedBook = new Book(bookId, title, author, subject, pageCount, isbn);

             AddBook addForm = new AddBook(selectedBook);  
            if (addForm.ShowDialog() == DialogResult.OK)
            {
                 LoadBooks();
            }
        }


         private void btnDelete_Click(object sender, EventArgs e)
        {
            if (lvBooks.SelectedItems.Count == 0)
            {
                MessageBox.Show("Please select a book to delete.");
                return;
            }

            int bookId = Convert.ToInt32(lvBooks.SelectedItems[0].Text);

             DialogResult result = MessageBox.Show("Are you sure you want to delete this book?", "Confirm Deletion", MessageBoxButtons.YesNo);
            if (result == DialogResult.Yes)
            {
                if (_bookManage.RemoveBook(bookId))
                {
                    MessageBox.Show("Book deleted successfully.");
                    LoadBooks();
                }
                else
                {
                    MessageBox.Show("Error deleting the book: " + _bookManage.Error);
                }
            }
        }

         private void lvBooks_SelectedIndexChanged(object sender, EventArgs e)
        {
             if (lvBooks.SelectedItems.Count > 0)
            {
                btnModify.Enabled = true;
                btnDelete.Enabled = true;
            }
            else
            {
                btnModify.Enabled = false;
                btnDelete.Enabled = false;
            }
        }
    }
}
