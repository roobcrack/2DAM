namespace Presentation
{
    partial class Listing
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
            label1 = new Label();
            lvBooks = new ListView();
            btnAdd = new Button();
            btnModify = new Button();
            btnDelete = new Button();
            lbErrorsOrPasses = new Label();
            groupBox1 = new GroupBox();
            groupBox1.SuspendLayout();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Segoe UI", 23F);
            label1.Location = new Point(30, 24);
            label1.Name = "label1";
            label1.Size = new Size(133, 42);
            label1.TabIndex = 0;
            label1.Text = "LIBRARY";
            // 
            // lvBooks
            // 
            lvBooks.Location = new Point(30, 69);
            lvBooks.Name = "lvBooks";
            lvBooks.Size = new Size(644, 246);
            lvBooks.TabIndex = 1;
            lvBooks.UseCompatibleStateImageBehavior = false;
            lvBooks.SelectedIndexChanged += lvBooks_SelectedIndexChanged;
            // 
            // btnAdd
            // 
            btnAdd.Location = new Point(16, 22);
            btnAdd.Name = "btnAdd";
            btnAdd.Size = new Size(75, 48);
            btnAdd.TabIndex = 2;
            btnAdd.Text = "Add";
            btnAdd.UseVisualStyleBackColor = true;
            btnAdd.Click += btnAdd_Click;
            // 
            // btnModify
            // 
            btnModify.Location = new Point(118, 22);
            btnModify.Name = "btnModify";
            btnModify.Size = new Size(75, 48);
            btnModify.TabIndex = 3;
            btnModify.Text = "Modify";
            btnModify.UseVisualStyleBackColor = true;
            btnModify.Click += btnModify_Click;
            // 
            // btnDelete
            // 
            btnDelete.Location = new Point(212, 22);
            btnDelete.Name = "btnDelete";
            btnDelete.Size = new Size(75, 48);
            btnDelete.TabIndex = 4;
            btnDelete.Text = "Delete";
            btnDelete.UseVisualStyleBackColor = true;
            btnDelete.Click += btnDelete_Click;
            // 
            // lbErrorsOrPasses
            // 
            lbErrorsOrPasses.AutoSize = true;
            lbErrorsOrPasses.Location = new Point(764, 405);
            lbErrorsOrPasses.Name = "lbErrorsOrPasses";
            lbErrorsOrPasses.Size = new Size(10, 15);
            lbErrorsOrPasses.TabIndex = 5;
            lbErrorsOrPasses.Text = ".";
            // 
            // groupBox1
            // 
            groupBox1.Controls.Add(btnAdd);
            groupBox1.Controls.Add(btnModify);
            groupBox1.Controls.Add(btnDelete);
            groupBox1.Location = new Point(30, 338);
            groupBox1.Name = "groupBox1";
            groupBox1.Size = new Size(310, 82);
            groupBox1.TabIndex = 6;
            groupBox1.TabStop = false;
            groupBox1.Text = "Administration";
            // 
            // Listing
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(groupBox1);
            Controls.Add(lbErrorsOrPasses);
            Controls.Add(lvBooks);
            Controls.Add(label1);
            Name = "Listing";
            Text = "Listing";
            Load += Listing_Load;
            groupBox1.ResumeLayout(false);
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private ListView lvBooks;
        private Button btnAdd;
        private Button btnModify;
        private Button btnDelete;
        private Label lbErrorsOrPasses;
        private GroupBox groupBox1;
    }
}