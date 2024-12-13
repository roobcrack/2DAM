namespace Entities
{
    public class Employee
    {
        public int Id { get; set; }
        public string Name {  get; set; }
        public string Mail { get; set; }
        public DateTime SignDate { get; set; }
        public bool Active { get; set; }

        public Employee(int Id, string name, string mail, DateTime signDate, bool Active)
        {
            this.Id = Id;
            this.Name = name;
            this.Mail = mail;
            this.SignDate = signDate;
            this.Active = Active;
        }  
    }
}
