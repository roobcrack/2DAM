using System;

class Program
{
    static void Main()
    {
        Complejos complejo1 = new Complejos(5, 6);
        Complejos complejo2 = new Complejos(8, 2);
        Complejos resultado;

        //suma
        resultado = complejo1 + complejo2;
        Console.WriteLine(complejo1.ToString() + " suma " + complejo2.ToString() + " = " + resultado.ToString());
        //resta
        resultado = complejo1 - complejo2;
        Console.WriteLine(complejo1.ToString() + " resta " + complejo2.ToString() + " = " + resultado.ToString());
        //multiplicacion
        resultado = complejo1 * complejo2;
        Console.WriteLine(complejo1.ToString() + " multiplicacion " + complejo2.ToString() + " = " + resultado.ToString());
        //multiplicacion por real
        resultado = 7.5 * complejo2;
        Console.WriteLine("7.5 multiplicacion " + complejo2.ToString() + " = " + resultado.ToString());
    }
}