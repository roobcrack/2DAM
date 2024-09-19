using System;
using System.Numerics;

class Ej
{
    static void Main()
    {
        //Solicitar vector 1
        Console.WriteLine("Introduce vector 1: ");
        int x1 = PedirNumero("Introduce el valor de x: ");
        int y1 = PedirNumero("Introduce el valor de y: ");
        int z1 = PedirNumero("Introduce el valor de z: ");
        Vector vector1 = new Vector(x1, y1, z1);
        //Solicitar vector 2
        Console.WriteLine("\nIntroduce vector 2: ");
        int x2 = PedirNumero("Introduce el valor de x: ");
        int y2 = PedirNumero("Introduce el valor de y: ");
        int z2 = PedirNumero("Introduce el valor de z: ");
        Vector vector2 = new Vector(x2, y2, z2);

        Console.WriteLine("La norma del vector 1: {0}", NormaMax(vector1));
        Console.WriteLine("La norma del vector 2: {0}", NormaMax(vector2));

        Console.Write("Los vectores ");
        if (Igual(vector1, vector2))
            Console.WriteLine("son iguales");
        else
            Console.WriteLine("no son iguales");
    }

    static int PedirNumero(string texto)
    {
        int numero = 0;
        bool hecho = false;
        while(!hecho)
        {
            Console.Write(texto);
            try
            {
                numero = Convert.ToInt32(Console.ReadLine());
                hecho = true;
            }
            catch (Exception e) { Console.WriteLine(e.Message); }
        }
        return numero;
    }
    static bool Igual(Vector vector1, Vector vector2)
    {
        return vector1.x == vector2.x && vector1.y == vector2.y && vector1.z == vector2.z;
    }

    static double NormaMax(Vector vector)
    {
        return Math.Sqrt((vector.x * vector.x) + (vector.y * vector.y) + (vector.z * vector.z));
    }
}
