using System;
using System.Collections.Generic;

class Program
{
    static void Main()
    {
        Conjunto conjunto = new Conjunto();

        conjunto.Agregar(22);
        conjunto.Agregar(12);

        Console.WriteLine(conjunto.ToString());
    }
}