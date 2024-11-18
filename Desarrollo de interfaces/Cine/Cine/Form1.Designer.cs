namespace Cine
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            Data = new GroupBox();
            txtDescription = new TextBox();
            label6 = new Label();
            label5 = new Label();
            numRecommendedAge = new NumericUpDown();
            numDuration = new NumericUpDown();
            txtTtile = new TextBox();
            btnDelete = new Button();
            btnAdd = new Button();
            label3 = new Label();
            label4 = new Label();
            label2 = new Label();
            label1 = new Label();
            groupBox2 = new GroupBox();
            lvMovies = new ListView();
            label7 = new Label();
            lblError = new Label();
            Data.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)numRecommendedAge).BeginInit();
            ((System.ComponentModel.ISupportInitialize)numDuration).BeginInit();
            groupBox2.SuspendLayout();
            SuspendLayout();
            // 
            // Data
            // 
            Data.Controls.Add(txtDescription);
            Data.Controls.Add(label6);
            Data.Controls.Add(label5);
            Data.Controls.Add(numRecommendedAge);
            Data.Controls.Add(numDuration);
            Data.Controls.Add(txtTtile);
            Data.Controls.Add(btnDelete);
            Data.Controls.Add(btnAdd);
            Data.Controls.Add(label3);
            Data.Controls.Add(label4);
            Data.Controls.Add(label2);
            Data.Controls.Add(label1);
            Data.Location = new Point(45, 38);
            Data.Name = "Data";
            Data.Size = new Size(376, 363);
            Data.TabIndex = 0;
            Data.TabStop = false;
            Data.Text = "Data";
            // 
            // txtDescription
            // 
            txtDescription.Location = new Point(141, 185);
            txtDescription.Name = "txtDescription";
            txtDescription.Size = new Size(219, 23);
            txtDescription.TabIndex = 12;
            // 
            // label6
            // 
            label6.AutoSize = true;
            label6.Location = new Point(290, 142);
            label6.Name = "label6";
            label6.Size = new Size(34, 15);
            label6.TabIndex = 11;
            label6.Text = "years";
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Location = new Point(290, 91);
            label5.Name = "label5";
            label5.Size = new Size(50, 15);
            label5.TabIndex = 10;
            label5.Text = "minutes";
            // 
            // numRecommendedAge
            // 
            numRecommendedAge.Location = new Point(141, 134);
            numRecommendedAge.Maximum = new decimal(new int[] { 18, 0, 0, 0 });
            numRecommendedAge.Name = "numRecommendedAge";
            numRecommendedAge.Size = new Size(120, 23);
            numRecommendedAge.TabIndex = 9;
            // 
            // numDuration
            // 
            numDuration.Location = new Point(141, 83);
            numDuration.Maximum = new decimal(new int[] { 1000, 0, 0, 0 });
            numDuration.Name = "numDuration";
            numDuration.Size = new Size(120, 23);
            numDuration.TabIndex = 8;
            // 
            // txtTtile
            // 
            txtTtile.Location = new Point(141, 34);
            txtTtile.Name = "txtTtile";
            txtTtile.Size = new Size(219, 23);
            txtTtile.TabIndex = 7;
            // 
            // btnDelete
            // 
            btnDelete.Location = new Point(265, 317);
            btnDelete.Name = "btnDelete";
            btnDelete.Size = new Size(75, 23);
            btnDelete.TabIndex = 6;
            btnDelete.Text = "Delete";
            btnDelete.UseVisualStyleBackColor = true;
            btnDelete.Click += btnDelete_Click;
            // 
            // btnAdd
            // 
            btnAdd.Location = new Point(159, 317);
            btnAdd.Name = "btnAdd";
            btnAdd.Size = new Size(75, 23);
            btnAdd.TabIndex = 5;
            btnAdd.Text = "Add";
            btnAdd.UseVisualStyleBackColor = true;
            btnAdd.Click += button1_Click;
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Location = new Point(15, 185);
            label3.Name = "label3";
            label3.Size = new Size(67, 15);
            label3.TabIndex = 3;
            label3.Text = "Description";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Location = new Point(15, 134);
            label4.Name = "label4";
            label4.Size = new Size(99, 15);
            label4.TabIndex = 2;
            label4.Text = "Recomended age";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Location = new Point(15, 85);
            label2.Name = "label2";
            label2.Size = new Size(53, 15);
            label2.TabIndex = 1;
            label2.Text = "Duration";
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(15, 34);
            label1.Name = "label1";
            label1.Size = new Size(29, 15);
            label1.TabIndex = 0;
            label1.Text = "Title";
            // 
            // groupBox2
            // 
            groupBox2.Controls.Add(lvMovies);
            groupBox2.Controls.Add(label7);
            groupBox2.Location = new Point(466, 38);
            groupBox2.Name = "groupBox2";
            groupBox2.Size = new Size(376, 363);
            groupBox2.TabIndex = 1;
            groupBox2.TabStop = false;
            groupBox2.Text = "Movie list";
            // 
            // lvMovies
            // 
            lvMovies.Location = new Point(23, 34);
            lvMovies.Name = "lvMovies";
            lvMovies.Size = new Size(328, 306);
            lvMovies.TabIndex = 2;
            lvMovies.UseCompatibleStateImageBehavior = false;
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Location = new Point(47, 360);
            label7.Name = "label7";
            label7.Size = new Size(38, 15);
            label7.TabIndex = 1;
            label7.Text = "label7";
            // 
            // lblError
            // 
            lblError.AutoSize = true;
            lblError.Location = new Point(489, 413);
            lblError.Name = "lblError";
            lblError.Size = new Size(27, 15);
            lblError.TabIndex = 2;
            lblError.Text = "Log";
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(891, 450);
            Controls.Add(lblError);
            Controls.Add(groupBox2);
            Controls.Add(Data);
            Name = "Form1";
            Text = "Cinema";
            Load += Form1_Load;
            Data.ResumeLayout(false);
            Data.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)numRecommendedAge).EndInit();
            ((System.ComponentModel.ISupportInitialize)numDuration).EndInit();
            groupBox2.ResumeLayout(false);
            groupBox2.PerformLayout();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private GroupBox Data;
        private Label label3;
        private Label label4;
        private Label label2;
        private Label label1;
        private GroupBox groupBox2;
        private Button btnDelete;
        private Button btnAdd;
        private Label label6;
        private Label label5;
        private NumericUpDown numRecommendedAge;
        private NumericUpDown numDuration;
        private TextBox txtTtile;
        private Label label7;
        private Label lblError;
        private TextBox txtDescription;
        private ListView lvMovies;
    }
}
