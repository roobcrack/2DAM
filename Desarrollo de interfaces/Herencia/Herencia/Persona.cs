namespace Herencia
{
    public class Persona
    {
        public int dni { get; set; }
        public string name { get; set; }
        public string mail { get; set; }

        public Persona(int dni, string name, string mail)
        {
            this.dni = dni;
            this.name = name;
            this.mail = mail;
        }
    }
}
