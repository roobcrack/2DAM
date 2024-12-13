namespace Presentacion
{
    partial class EmployeeList
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            lvEmployees = new ListView();
            lblTitle = new Label();
            btnAdd = new Button();
            btnEdit = new Button();
            btnDelete = new Button();
            groupBox1 = new GroupBox();
            groupBox1.SuspendLayout();
            SuspendLayout();
            // 
            // lvEmployees
            // 
            lvEmployees.Location = new Point(33, 76);
            lvEmployees.Name = "lvEmployees";
            lvEmployees.Size = new Size(725, 250);
            lvEmployees.TabIndex = 0;
            lvEmployees.UseCompatibleStateImageBehavior = false;
            // 
            // lblTitle
            // 
            lblTitle.AutoSize = true;
            lblTitle.Font = new Font("Segoe UI", 24F);
            lblTitle.Location = new Point(33, 18);
            lblTitle.Name = "lblTitle";
            lblTitle.Size = new Size(216, 45);
            lblTitle.TabIndex = 1;
            lblTitle.Text = "Employee List";
            // 
            // btnAdd
            // 
            btnAdd.Location = new Point(18, 22);
            btnAdd.Name = "btnAdd";
            btnAdd.Size = new Size(75, 36);
            btnAdd.TabIndex = 2;
            btnAdd.Text = "Add";
            btnAdd.UseVisualStyleBackColor = true;
            // 
            // btnEdit
            // 
            btnEdit.Location = new Point(110, 22);
            btnEdit.Name = "btnEdit";
            btnEdit.Size = new Size(75, 36);
            btnEdit.TabIndex = 3;
            btnEdit.Text = "Edit";
            btnEdit.UseVisualStyleBackColor = true;
            // 
            // btnDelete
            // 
            btnDelete.Location = new Point(204, 22);
            btnDelete.Name = "btnDelete";
            btnDelete.Size = new Size(75, 36);
            btnDelete.TabIndex = 4;
            btnDelete.Text = "Delete";
            btnDelete.UseVisualStyleBackColor = true;
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(btnAdd);
            groupBox1.Controls.Add(btnDelete);
            groupBox1.Controls.Add(btnEdit);
            groupBox1.Location = new Point(33, 343);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(298, 70);
            groupBox1.TabIndex = 5;
            groupBox1.TabStop = false;
            groupBox1.Text = "Administrate";
            // 
            // EmployeeList
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(groupBox1);
            Controls.Add(lblTitle);
            Controls.Add(lvEmployees);
            Name = "EmployeeList";
            Text = "EmployeeList";
            Load += ListaEmpleados_Load;
            groupBox1.ResumeLayout(false);
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private ListView lvEmployees;
        private Label lblTitle;
        private Button btnAdd;
        private Button btnEdit;
        private Button btnDelete;
        private GroupBox groupBox1;
    }
}