using System;
using System.Windows.Forms;
using Business;
using Entities;

namespace Cine
{
    public partial class Form1 : Form
    {
        private readonly MovieManager movieManager = new MovieManager();

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            InitializeListView();
            LoadMovies();
        }

        private void InitializeListView()
        {
            lvMovies.View = View.Details;
            lvMovies.Columns.Add("Title", 200);
            lvMovies.Columns.Add("Duration (min)", 100);
            lvMovies.FullRowSelect = true;
        }

        private void LoadMovies()
        {
            lvMovies.Items.Clear();
            var movies = movieManager.GetAllMovies();
            foreach (var movie in movies)
            {
                var item = new ListViewItem(new[] { movie.Title, movie.Duration.ToString() })
                {
                    Tag = movie // Store the Movie object for easy access
                };
                lvMovies.Items.Add(item);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (ValidateForm())
            {
                movieManager.CurrentMovie = new Movie(
                    txtTtile.Text,
                    (int)numDuration.Value,
                    (int)numRecommendedAge.Value,
                    txtDescription.Text
                );

                int result = movieManager.AddMovie();
                if (result > 0)
                {
                    lblError.Text = "Movie added successfully.";
                    LoadMovies();
                }
                else
                {
                    lblError.Text = $"Error: {movieManager.GetLastError()}";
                }
            }
            else
            {
                lblError.Text = "All fields must be filled and have valid values.";
            }
        }

        private bool ValidateForm()
        {
            return !string.IsNullOrWhiteSpace(txtTtile.Text) &&
                   numDuration.Value > 0 &&
                   numRecommendedAge.Value > 0 &&
                   !string.IsNullOrWhiteSpace(txtDescription.Text);
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (lvMovies.SelectedItems.Count > 0)
            {
                var selectedItem = lvMovies.SelectedItems[0];
                var movie = (Movie)selectedItem.Tag;

                int result = movieManager.DeleteMovie(movie.Id);
                if (result > 0)
                {
                    lblError.Text = "Movie deleted successfully.";
                    LoadMovies();
                }
                else
                {
                    lblError.Text = $"Error: {movieManager.GetLastError()}";
                }
            }
            else
            {
                lblError.Text = "Please select a movie to delete.";
            }
        }
    }
}
