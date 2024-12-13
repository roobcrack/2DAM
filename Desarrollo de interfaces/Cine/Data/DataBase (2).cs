using System.Data;
using MySql.Data.MySqlClient;

namespace Data
{
    public static class DataBase
    {
        public static string Error { get; private set; }
        public static int NumRecordsModified { get; private set; }

        private static MySqlConnection Connect()
        {
            string connectionString = "datasource=127.0.0.1; port=3306; username=root; password=; database=employees;";
            return new MySqlConnection(connectionString);
        }

        public static DataTable ExecuteQuery(string sql)
        {
            using (MySqlConnection connection = Connect())
            {
                MySqlDataAdapter adapter = new MySqlDataAdapter(sql, connection);
                DataSet dataSet = new DataSet();

                try
                {
                    adapter.Fill(dataSet);
                    NumRecordsModified = 0;
                    Error = string.Empty;
                    return dataSet.Tables[0];  // Returns the first table
                }
                catch (Exception ex)
                {
                    Error = ex.Message;
                    NumRecordsModified = -1;
                    // Log error here or display it to user
                    Console.WriteLine($"Error: {ex.Message}");
                    return null;
                }
            }
        }


        public static int ExecuteNonQuery(string sql)
        {
            using (MySqlConnection connection = Connect())
            {
                try
                {
                    MySqlCommand command = new MySqlCommand(sql, connection);
                    connection.Open();
                    NumRecordsModified = command.ExecuteNonQuery();
                    Error = string.Empty;
                    return NumRecordsModified;
                }
                catch (MySqlException ex)
                {
                    Error = ex.Message;
                    NumRecordsModified = -1;
                    return NumRecordsModified;
                }
            }
        }
    }
}   