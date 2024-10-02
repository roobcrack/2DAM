
namespace CapaDatosBasico
{
    class Contacto
    {
        private string nombre;
        private string telefono;
        private ContactoADO ADO;

        public string Telefono
        {
            get
            {
                return telefono;
            }
            set
            {
                if (value.Length <= 0 || value.Length > 12)
                {
                    throw new ArgumentException("ERR-0001: El teléfono debe contener valor y no superar los 12 caracteres");
                }
                telefono = value;
            }
        }

        public string Nombre
        {
            get
            {
                return nombre;
            }
            set
            {
                if (value.Length > 30)
                {
                    throw new ArgumentException("ERR-0002: El nombre no debe de exceder de 30 caracteres");
                }
                nombre = value;
            }
        }

        public Contacto()
        {
            this.nombre = "Sin nombre";
            this.telefono = "0";
            this.ADO = new ContactoADO();
        }

         public Contacto(string pNombre, string pTelefono)
        {
            int resultado = 0;
            Nombre = pNombre;
            Telefono = pTelefono;
            ADO = new ContactoADO();
 
            resultado = ADO.Insertar(String.Format("INSERT INTO Telefonos (nombre, telefono) VALUES ('{0}','{1}')" , pNombre,pTelefono));
 
        }

        public IList<Contacto> Listado()
        {
            IList<Contacto> tabla;
            ADO = new ContactoADO();
            tabla = ADO.Listado("Select * from Telefonos");
            return tabla;
        }
    }
}
