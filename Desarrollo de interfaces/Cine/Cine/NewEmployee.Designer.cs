namespace Presentation
{
    partial class NewEmployee
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
            lblTitle = new Label();
            label1 = new Label();
            tbName = new TextBox();
            tbMail = new TextBox();
            label2 = new Label();
            dtSignDate = new DateTimePicker();
            label3 = new Label();
            cbActive = new CheckBox();
            btnSave = new Button();
            btnBack = new Button();
            egor = new Label();
            SuspendLayout();
            // 
            // lblTitle
            // 
            lblTitle.AutoSize = true;
            lblTitle.Font = new Font("Segoe UI", 24F, FontStyle.Bold);
            lblTitle.Location = new Point(24, 19);
            lblTitle.Name = "lblTitle";
            lblTitle.Size = new Size(243, 45);
            lblTitle.TabIndex = 2;
            lblTitle.Text = "New Employee";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(57, 83);
            label1.Name = "label1";
            label1.Size = new Size(59, 15);
            label1.TabIndex = 3;
            label1.Text = "Full name";
            // 
            // tbName
            // 
            tbName.Location = new Point(57, 101);
            tbName.Name = "tbName";
            tbName.PlaceholderText = "Name";
            tbName.Size = new Size(546, 23);
            tbName.TabIndex = 4;
            // 
            // tbMail
            // 
            tbMail.Location = new Point(57, 159);
            tbMail.Name = "tbMail";
            tbMail.PlaceholderText = "mail@domain.extens";
            tbMail.Size = new Size(546, 23);
            tbMail.TabIndex = 6;
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(57, 141);
            label2.Name = "label2";
            label2.Size = new Size(30, 15);
            label2.TabIndex = 5;
            label2.Text = "Mail";
            // 
            // dtSignDate
            // 
            dtSignDate.Location = new Point(57, 230);
            dtSignDate.Name = "dtSignDate";
            dtSignDate.Size = new Size(546, 23);
            dtSignDate.TabIndex = 7;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(57, 212);
            label3.Name = "label3";
            label3.Size = new Size(57, 15);
            label3.TabIndex = 8;
            label3.Text = "Sign Date";
            // 
            // cbActive
            // 
            cbActive.AutoSize = true;
            cbActive.Location = new Point(57, 279);
            cbActive.Name = "cbActive";
            cbActive.Size = new Size(59, 19);
            cbActive.TabIndex = 9;
            cbActive.Text = "Active";
            cbActive.UseVisualStyleBackColor = true;
            // 
            // btnSave
            // 
            btnSave.Location = new Point(57, 314);
            btnSave.Name = "btnSave";
            btnSave.Size = new Size(75, 39);
            btnSave.TabIndex = 10;
            btnSave.Text = "Save";
            btnSave.UseVisualStyleBackColor = true;
            btnSave.Click += btnSave_Click;
            // 
            // btnBack
            // 
            btnBack.Location = new Point(138, 314);
            btnBack.Name = "btnBack";
            btnBack.Size = new Size(75, 39);
            btnBack.TabIndex = 11;
            btnBack.Text = "Back";
            btnBack.UseVisualStyleBackColor = true;
            btnBack.Click += btnBack_Click;
            // 
            // egor
            // 
            egor.AutoSize = true;
            egor.Location = new Point(394, 314);
            egor.Name = "egor";
            egor.Size = new Size(79, 15);
            egor.TabIndex = 12;
            egor.Text = "ERROOOOOR";
            // 
            // NewEmployee
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(651, 387);
            Controls.Add(egor);
            Controls.Add(btnBack);
            Controls.Add(btnSave);
            Controls.Add(cbActive);
            Controls.Add(label3);
            Controls.Add(dtSignDate);
            Controls.Add(tbMail);
            Controls.Add(label2);
            Controls.Add(tbName);
            Controls.Add(label1);
            Controls.Add(lblTitle);
            Name = "NewEmployee";
            Text = "New Employee";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label lblTitle;
        private Label label1;
        private TextBox tbName;
        private TextBox tbMail;
        private Label label2;
        private DateTimePicker dtSignDate;
        private Label label3;
        private CheckBox cbActive;
        private Button btnSave;
        private Button btnBack;
        private Label egor;
    }
}