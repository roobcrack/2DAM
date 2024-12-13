namespace Entities
{
    public class Employee
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Mail { get; set; }
        public DateTime SignDate { get; set; }
        public bool Active { get; set; }

        public Employee(int id, string name, string mail, DateTime signDate, bool Active)
        {
            this.Id = id;
            this.Name = name;
            this.Mail = mail;
            this.SignDate = signDate;
            this.Active = Active;
        }

        public Employee(string name, string mail, DateTime signDate, bool Active) 
            :this(0, name, mail, signDate, Active) { }
    }
}
