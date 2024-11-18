using System;

namespace Herencia
{
    internal class Alumno : Persona
    {
        private List<Modulo> modulos;

        public Alumno(int dni, string name, string mail, List<Modulo> modulos)
            :base(dni,name, mail)
        {
            this.modulos = new List<Modulo>();
        }
    }
}
