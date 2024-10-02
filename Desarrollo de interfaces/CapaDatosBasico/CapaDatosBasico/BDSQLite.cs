using System.Data.SQLite;


namespace CapaDatosBasico
{
    public class BDSQLite
    {
        private SQLiteConnection conexionConBD;
        private SQLiteCommand orden;
        private SQLiteDataReader lector;
        static string strConexion;

        public BDSQLite()
        {
            strConexion = string.Format("Data Source=./{0};", "Tfnos.s3db");
            conexionConBD = null;
            orden = null;
            lector = null;
        }

        public void Abrir()
        {
            //Abrir la base de datos
            conexionConBD = new SQLiteConnection(strConexion);
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

        public SQLiteDataReader EjecutarDML(string SQL)
        {
            //Ejecutar DML: Select.
            orden = new SQLiteCommand(SQL, conexionConBD);
            lector = orden.ExecuteReader();
            return lector;
        }

        public int EjecutarDDL(string SQL)
        {
            //Ejecutar DDL: Insert, Update y Delete.
            int filasAfectadas = 0;
            orden = new SQLiteCommand(SQL, conexionConBD);
            filasAfectadas = orden.ExecuteNonQuery();
            return filasAfectadas;
        }

    }
}
