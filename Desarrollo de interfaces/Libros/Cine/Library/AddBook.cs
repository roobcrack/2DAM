using System;
using System.Windows.Forms;
using Business;

namespace Presentation
{
    public partial class AddBook : Form
    {
        private BookManage _bookManage;
        private Book _currentBook;
        private bool edit = false;

        public AddBook(Book bookToEdit = null)
        {
            InitializeComponent();
            _bookManage = new BookManage();

            if (bookToEdit != null)
            {
                edit = true;
                _currentBook = bookToEdit;
                txtTitle.Text = _currentBook.Title;
                txtAuthor.Text = _currentBook.Author;
                txtSubject.Text = _currentBook.Subject;
                numericUpDown1.Value = _currentBook.PageCount;
                textBox3.Text = _currentBook.ISBN;

                this.Text = "Edit Book";
                btnAdd.Text = "Save Changes"; 
            }
            else
            {
                this.Text = "Add New Book";
                btnAdd.Text = "Add Book";
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            if (string.IsNullOrWhiteSpace(txtTitle.Text) || string.IsNullOrWhiteSpace(txtAuthor.Text) || string.IsNullOrWhiteSpace(txtSubject.Text))
            {
                MessageBox.Show("Please fill in all fields.");
                return;
            }

            string title = txtTitle.Text;
            string author = txtAuthor.Text;
            string subject = txtSubject.Text;
            int pageCount = (int)numericUpDown1.Value;  
            string isbn = textBox3.Text;

            Book bookToSave;

            if (_currentBook != null)
            {
                _currentBook.Title = title;
                _currentBook.Author = author;
                _currentBook.Subject = subject;
                _currentBook.PageCount = pageCount;
                _currentBook.ISBN = isbn;

                bookToSave = _currentBook; 
            }
            else
            {
                bookToSave = new Book(0, title, author, subject, pageCount, isbn);  
            }

            if (edit)
            {
                if (_bookManage.UpdateBook(bookToSave))
                {
                    MessageBox.Show("Book saved successfully.");
                    DialogResult = DialogResult.OK;
                }
                else
                {
                    MessageBox.Show("Error saving book: " + _bookManage.Error);
                }
            }
            else
            {
                if (_bookManage.CreateBook(bookToSave))
                {
                    MessageBox.Show("Book saved successfully.");
                    DialogResult = DialogResult.OK;
                }
                else
                {
                    MessageBox.Show("Error saving book: " + _bookManage.Error);
                }
            }
             
        }

         private void btnCancel_Click(object sender, EventArgs e)
        {
            this.Close();  
        }
    }
}
