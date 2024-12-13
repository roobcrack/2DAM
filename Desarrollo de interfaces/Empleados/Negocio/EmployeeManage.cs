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
            Employees = GetAllEmployees();
        }

        public string GetLastError()
        {
            return DataBase.Error;
        }

        public int AddEmployee()
        {
            string sql = $"INSERT INTO employees (id, name, mail, signDate, active) VALUES (\"{SelectedEmployee.Id}\", \"{SelectedEmployee.Name}\", \"{SelectedEmployee.Mail}\", \"{SelectedEmployee.SignDate}\", \"{SelectedEmployee.Active}\")";
            return DataBase.ExecuteNonQuery(sql);
        }

        public int DeleteEmployee(int employeeId)
        {
            string sql = $"DELETE FROM employees WHERE id = {employeeId}";
            return DataBase.ExecuteNonQuery(sql);
        }

        public List<Employee> GetAllEmployees()
        {
            List<Employee> employees = new List<Employee>();
            DataTable dt = DataBase.ExecuteQuery("SELECT * FROM employees");
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