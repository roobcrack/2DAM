namespace Presentation
{
    partial class AddBook
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
            txtTitle = new TextBox();
            txtAuthor = new TextBox();
            textBox3 = new TextBox();
            txtSubject = new TextBox();
            numericUpDown1 = new NumericUpDown();
            label1 = new Label();
            label2 = new Label();
            label3 = new Label();
            txtPageCount = new Label();
            txtISBN = new Label();
            label6 = new Label();
            btnAdd = new Button();
            btnCancel = new Button();
            ((System.ComponentModel.ISupportInitialize)numericUpDown1).BeginInit();
            SuspendLayout();
            // 
            // txtTitle
            // 
            txtTitle.Location = new Point(60, 110);
            txtTitle.Name = "txtTitle";
            txtTitle.Size = new Size(528, 23);
            txtTitle.TabIndex = 0;
            // 
            // txtAuthor
            // 
            txtAuthor.Location = new Point(58, 154);
            txtAuthor.Name = "txtAuthor";
            txtAuthor.Size = new Size(530, 23);
            txtAuthor.TabIndex = 1;
            // 
            // textBox3
            // 
            textBox3.Location = new Point(58, 286);
            textBox3.Name = "textBox3";
            textBox3.Size = new Size(269, 23);
            textBox3.TabIndex = 2;
            // 
            // txtSubject
            // 
            txtSubject.Location = new Point(58, 198);
            txtSubject.Name = "txtSubject";
            txtSubject.Size = new Size(530, 23);
            txtSubject.TabIndex = 3;
            // 
            // numericUpDown1
            // 
            numericUpDown1.Location = new Point(58, 242);
            numericUpDown1.Name = "numericUpDown1";
            numericUpDown1.Size = new Size(120, 23);
            numericUpDown1.TabIndex = 4;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(60, 92);
            label1.Name = "label1";
            label1.Size = new Size(29, 15);
            label1.TabIndex = 5;
            label1.Text = "Title";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(58, 136);
            label2.Name = "label2";
            label2.Size = new Size(44, 15);
            label2.TabIndex = 6;
            label2.Text = "Author";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(60, 180);
            label3.Name = "label3";
            label3.Size = new Size(46, 15);
            label3.TabIndex = 7;
            label3.Text = "Subject";
            // 
            // txtPageCount
            // 
            txtPageCount.AutoSize = true;
            txtPageCount.Location = new Point(60, 224);
            txtPageCount.Name = "txtPageCount";
            txtPageCount.Size = new Size(69, 15);
            txtPageCount.TabIndex = 8;
            txtPageCount.Text = "Page Count";
            // 
            // txtISBN
            // 
            txtISBN.AutoSize = true;
            txtISBN.Location = new Point(58, 268);
            txtISBN.Name = "txtISBN";
            txtISBN.Size = new Size(32, 15);
            txtISBN.TabIndex = 9;
            txtISBN.Text = "ISBN";
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Font = new Font("Segoe UI", 25F);
            label6.Location = new Point(58, 25);
            label6.Name = "label6";
            label6.Size = new Size(259, 46);
            label6.TabIndex = 10;
            label6.Text = "BOOK MANAGE";
            // 
            // btnAdd
            // 
            btnAdd.Location = new Point(58, 336);
            btnAdd.Name = "btnAdd";
            btnAdd.Size = new Size(75, 47);
            btnAdd.TabIndex = 11;
            btnAdd.Text = "Add";
            btnAdd.UseVisualStyleBackColor = true;
            btnAdd.Click += btnAdd_Click;
            // 
            // btnCancel
            // 
            btnCancel.Location = new Point(152, 336);
            btnCancel.Name = "btnCancel";
            btnCancel.Size = new Size(75, 47);
            btnCancel.TabIndex = 12;
            btnCancel.Text = "Cancel";
            btnCancel.UseVisualStyleBackColor = true;
            btnCancel.Click += btnCancel_Click;
            // 
            // AddBook
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 411);
            Controls.Add(btnCancel);
            Controls.Add(btnAdd);
            Controls.Add(label6);
            Controls.Add(txtISBN);
            Controls.Add(txtPageCount);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(numericUpDown1);
            Controls.Add(txtSubject);
            Controls.Add(textBox3);
            Controls.Add(txtAuthor);
            Controls.Add(txtTitle);
            Name = "AddBook";
            Text = "AddBook";
            ((System.ComponentModel.ISupportInitialize)numericUpDown1).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox txtTitle;
        private TextBox txtAuthor;
        private TextBox textBox3;
        private TextBox txtSubject;
        private NumericUpDown numericUpDown1;
        private Label label1;
        private Label label2;
        private Label label3;
        private Label txtPageCount;
        private Label txtISBN;
        private Label label6;
        private Button btnAdd;
        private Button btnCancel;
    }
}