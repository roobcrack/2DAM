using System;
using System.Diagnostics;
using System.Data.SQLite;
using System.Data.OleDb;
using System.Data;
using System.Collections.Generic;

namespace CapaDatosBasico
{
    class ContactoADO
    {
        private BDSQLite bbdd; //Para SQLite

        public ContactoADO()
        {
            bbdd = new BDSQLite(); //Para SQLite
        }

        public int Insertar(string SQL)
        {
            // Para SQLite
            SQLiteDataReader lector;
            int Id = 0;
            bbdd.Abrir();
            try
            {
                lector = bbdd.EjecutarDML(SQL);
                while (lector.Read())
                {
                    Id = lector.GetInt32(0);
                }
            }
            catch (Exception ex)
            {
                Id = 0;
                Debug.WriteLine("Error al insertar:" + ex.Message);
            }
            finally
            {
                bbdd.Cerrar();
            }
            return Id;
        }

        public IList<Contacto> Listado(string SQL)
        {
            IList<Contacto> tabla = new List<Contacto>();
            // Para SQLite
            SQLiteDataReader lector;
            bbdd.Abrir();
            try
            {
                lector = bbdd.EjecutarDML(SQL);
                while (lector.Read())
                {
                    //Recoger los datos de la base de datos
                    Contacto c = new Contacto();
                    c.Telefono = lector.GetString(0);
                    c.Nombre = lector.GetString(1);
                    tabla.Add(c);
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine("Error al insertar:" + ex.Message);
            }
            finally
            {
                bbdd.Cerrar();
            }
            return tabla;
        }
    }
