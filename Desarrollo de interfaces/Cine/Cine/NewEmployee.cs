using Business;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Entities;

namespace Presentation
{
    public partial class NewEmployee : Form
    {
        private EmployeeManager empManag = new EmployeeManager();

        public NewEmployee()
        {
            InitializeComponent();
        }

        private void btnBack_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void btnSave_Click(object sender, EventArgs e)
        {
            if (ValidateForm())
            {
                empManag.SelectedEmployee = new Employee(
                    tbName.Text,
                    tbMail.Text,
                    DateTime.Parse(dtSignDate.Value.ToString()),
                    cbActive.Checked
                );

                int result = empManag.AddEmployee();
                if (result > 0)
                {
                    egor.Text = "Movie added successfully.";
                    this.Close();
                }
                else
                {
                    egor.Text = $"Error: {empManag.GetLastError()}";
                }
            }
            else
            {
                egor.Text = "All fields must be filled and have valid values.";
            }
        }

        private bool ValidateForm()
        {
            return !string.IsNullOrWhiteSpace(tbName.Text) &&
                   !string.IsNullOrWhiteSpace(tbMail.Text) &&
                   dtSignDate.Value != null && dtSignDate.Value != DateTime.MinValue;
        }
    }
}
