using System.Data;
using Data;
using Entities;

namespace Business
{
    public class EmployeeManager
    {
        public Employee SelectedEmployee { get; set; }
        public List<Employee> Employees { get; private set; }

        public EmployeeManager()
        {
            LoadEmployees(); // Initialize the Employees list
        }

        public string GetLastError()
        {
            return DataBase.Error;
        }

        /// <summary>
        /// Adds a new employee to the database and updates the local list.
        /// </summary>
        public int AddEmployee()
        {
            string sql = $"INSERT INTO employee (name, mail, signDate, active) VALUES (\"{SelectedEmployee.Name}\", \"{SelectedEmployee.Mail}\", \"{SelectedEmployee.SignDate:yyyy-MM-dd}\", {SelectedEmployee.Active})";
            int result = DataBase.ExecuteNonQuery(sql);

            if (result > 0)
            {
                // Re-fetch the new data from the database
                LoadEmployees();
            }

            return result;
        }

        /// <summary>
        /// Deletes an employee from the database and updates the local list.
        /// </summary>
        public int DeleteEmployee(int employeeId)
        {
            string sql = $"DELETE FROM employee WHERE id = {employeeId}";
            int result = DataBase.ExecuteNonQuery(sql);

            if (result > 0)
            {
                // Remove the deleted employee from the in-memory list
                Employees.RemoveAll(e => e.Id == employeeId);
            }

            return result;
        }

        /// <summary>
        /// Retrieves all employees from the database.
        /// </summary>
        public void LoadEmployees()
        {
            Employees = GetAllEmployees();
        }

        /// <summary>
        /// Fetches employee data from the database and returns it as a list.
        /// </summary>
        public List<Employee> GetAllEmployees()
        {
            List<Employee> employees = new List<Employee>();
            DataTable dt = DataBase.ExecuteQuery("SELECT * FROM employee");

            if (dt != null)
            {
                foreach (DataRow row in dt.Rows)
                {
                    employees.Add(new Employee(
                        Convert.ToInt32(row["id"]),
                        row["name"].ToString(),
                        row["mail"].ToString(),
                        DateTime.Parse(row["signDate"].ToString()),
                        bool.Parse(row["active"].ToString())
                    ));
                }
            }

            return employees;
        }
    }
}
