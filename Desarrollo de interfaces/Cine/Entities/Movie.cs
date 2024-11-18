namespace Entities
{
    public class Movie
    {
        public int Id { get; set; }
        public string Title { get; set; }
        public int Duration { get; set; }
        public int RecommendedAge { get; set; }
        public string Description { get; set; }

        public Movie(int id, string title, int duration, int recommendedAge, string description)
        {
            Id = id;
            Title = title;
            Duration = duration;
            RecommendedAge = recommendedAge;
            Description = description;
        }

        public Movie(string title, int duration, int recommendedAge, string description)
            : this(0, title, duration, recommendedAge, description) { }
    }
}