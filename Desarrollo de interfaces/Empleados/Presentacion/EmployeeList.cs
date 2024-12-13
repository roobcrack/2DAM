using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Business;
using Entities;

namespace Presentacion
{
    public partial class EmployeeList : Form
    {
        private EmployeeManager empManag = new EmployeeManager();

        public EmployeeList()
        {
            InitializeComponent();
        }

        private void ListaEmpleados_Load(object sender, EventArgs e)
        {
            InitializeListView();
            string message = LoadEmployees();
            lblError.Text = message;
        }

        private void InitializeListView()
        {
            lvEmployees.View = View.Details;
            lvEmployees.Columns.Add("ID", 50);
            lvEmployees.Columns.Add("Name", 150);
            lvEmployees.Columns.Add("Mail", 200);
            lvEmployees.Columns.Add("Sign Date", 150);
            lvEmployees.Columns.Add("Active", 100);
            lvEmployees.FullRowSelect = true;
        }

        private void LoadEmployees()
        {
            lvEmployees.Items.Clear();
            var employees = empManag.GetAllEmployees();
            foreach (var employee in employees)
            {
                var item = new ListViewItem(new[] { employee.Id.ToString(), employee.Name, employee.Mail, employee.SignDate.ToString(), employee.Active.ToString()})
                {
                    Tag = employee
                };
                lvEmployees.Items.Add(item);
            }
        }
    }
}
