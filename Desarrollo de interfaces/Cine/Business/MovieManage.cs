using System;
using System.Collections.Generic;
using System.Data;
using Data;
using Entities;

namespace Business
{
    public class MovieManager
    {
        public Movie CurrentMovie { get; set; }
        public List<Movie> Movies { get; private set; }

        public MovieManager()
        {
            Movies = GetAllMovies();
        }

        public string GetLastError()
        {
            return DataBase.Error;
        }

        public int AddMovie()
        {
            string sql = $"INSERT INTO movies (title, duration, recommended_age, description) VALUES (\"{CurrentMovie.Title}\", \"{CurrentMovie.Duration}\", \"{CurrentMovie.RecommendedAge}\", \"{CurrentMovie.Description}\")";
            return DataBase.ExecuteNonQuery(sql);
        }

        public int DeleteMovie(int movieId)
        {
            string sql = $"DELETE FROM movies WHERE id = {movieId}";
            return DataBase.ExecuteNonQuery(sql);
        }

        public List<Movie> GetAllMovies()
        {
            List<Movie> movies = new List<Movie>();
            DataTable dt = DataBase.ExecuteQuery("SELECT * FROM movies");
            if (dt != null)
            {
                foreach (DataRow row in dt.Rows)
                {
                    movies.Add(new Movie(
                        Convert.ToInt32(row["id"]),
                        row["title"].ToString(),
                        Convert.ToInt32(row["duration"]),
                        Convert.ToInt32(row["recommended_age"]),
                        row["description"].ToString()
                    ));
                }
            }
            return movies;
        }
    }
}