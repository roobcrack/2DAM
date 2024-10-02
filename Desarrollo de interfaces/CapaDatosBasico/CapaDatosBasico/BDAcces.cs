using System.Data.OleDb;


namespace CapaDatosBasico
{
    class BDAccess
    {
        private OleDbConnection conexionConBD;
        private OleDbCommand orden;
        private OleDbDataReader lector;
        static string strConexion;

        public BDAccess()
        {
            strConexion = string.Format("Provider=Microsoft.ACE.OLEDB.12.0;Data Source=|DataDirectory|{0};Persist Security Info=True;", "tfnos.accdb");
            conexionConBD = null;
            orden = null;
            lector = null;
        }

        public void Abrir()
        {
            //Abrir la base de datos
            conexionConBD = new OleDbConnection(strConexion);
            conexionConBD.Open();
        }
        public void Cerrar()
        {
            // Cerrar la conexión cuando ya no sea necesaria.
            if (lector != null)
            {
                lector.Close();
            }

            if (conexionConBD != null)
            {
                conexionConBD.Close();
            }
        }

        public OleDbDataReader EjecutarDML(string SQL)
        {
            //Ejecutar DML: Select.
            orden = new OleDbCommand(SQL, conexionConBD);
            lector = orden.ExecuteReader();
            return lector;
        }

        public int EjecutarDDL(string SQL)
        {
            //Ejecutar DDL: Insert, Update y Delete.
            int filasAfectadas = 0;
            orden = new OleDbCommand(SQL, conexionConBD);
            filasAfectadas = orden.ExecuteNonQuery();
            return filasAfectadas;
        }


    }
}
