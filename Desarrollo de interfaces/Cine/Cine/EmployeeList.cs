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
using Presentation;

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
            LoadEmployees();
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
                var item = new ListViewItem(new[] { employee.Id.ToString(), employee.Name, employee.Mail, employee.SignDate.ToString(), employee.Active.ToString() })
                {
                    Tag = employee
                };
                lvEmployees.Items.Add(item);
            }
        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            NewEmployee newEmployeeForm = new NewEmployee();
            newEmployeeForm.ShowDialog();
            LoadEmployees();
        }

        private void lvEmployees_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            if (lvEmployees.SelectedItems.Count > 0)
            {
                var selectedItem = lvEmployees.SelectedItems[0];
                var employee = (Employee)selectedItem.Tag;

                int result = empManag.DeleteEmployee(employee.Id);
                if (result > 0)
                {
                    lblError.Text = "Employee deleted successfully.";
                    LoadEmployees();
                }
                else
                {
                    lblError.Text = $"Error: {empManag.GetLastError()}";
                }
            }
            else
            {
                lblError.Text = "Please select a employee to delete.";
            }
        }
    }
}
