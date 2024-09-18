using System;

class Complejos
{
    private double real { get; set; }
    private double imaginario { get; set; }

    public Complejos() { }
    public Complejos(double real, double imaginario)
    {
        this.real = real;
        this.imaginario = imaginario;
    }

    //Suma de 2 imaginarios
    public static Complejos operator + (Complejos comp1, Complejos comp2)
    {
        Complejos op = new Complejos(comp1.real+comp2.real, comp1.imaginario+comp2.imaginario);
        return op;
    }

    //Resta de 2 imaginarios
    public static Complejos operator - (Complejos comp1, Complejos comp2)
    {
        Complejos op = new Complejos(comp1.real - comp2.real, comp1.imaginario - comp2.imaginario);
        return op;
    }

    //Multiplicacion de 2 imaginarios
    public static Complejos operator * (Complejos comp1, Complejos comp2)
    {
        Complejos op = new Complejos((comp1.real * comp2.real) - (comp1.imaginario * comp2.imaginario),
            ((comp1.real * comp2.imaginario) + (comp1.imaginario * comp2.real)));
        return op;
    }

    //Multiplicacion de 2 imaginarios
    public static Complejos operator * (double num, Complejos comp)
    {
        Complejos op = new Complejos(num*comp.real, num*comp.imaginario);
        return op;
    }

    //ToString que muestra el numero completo
    public string ToString()
    {
        return real + "+" + imaginario + "i";
    }
}