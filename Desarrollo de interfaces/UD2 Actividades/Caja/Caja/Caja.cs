using System;

class Caja : IComparable<Caja>
{
    private double alto { get; set; }
    private double largo { get; set; }
    private double profundo { get; set; }

    public void setAlto(double alto)
    {
        if (alto <= 0)
            throw new ArgumentException("El alto debe ser mayor que cero.");
        this.alto = alto;
    }

    public void setLargo(double largo)
    {
        if (largo <= 0)
            throw new ArgumentException("El largo debe ser mayor que cero.");
        this.largo = largo;
    }

    public void setProfundo(double profundo)
    {
        if (profundo <= 0)
            throw new ArgumentException("El profundo debe ser mayor que cero.");
        this.profundo = profundo;
    }

    public Caja(double alto, double largo, double profundo)
    {
        setAlto(alto);
        setLargo(largo);
        setProfundo(profundo);
    }
    
    public double CalcularVolumen()
    {
        return alto * largo * profundo;
    }

    public static Caja operator + (Caja caja1, Caja caja2)
    {
        return new Caja(caja1.alto + caja2.alto, caja1.largo + caja2.largo, caja1.profundo + caja2.profundo);
    }

    public static Caja operator -(Caja caja1, Caja caja2)
    {
        return new Caja(caja1.alto - caja2.alto, caja1.largo - caja2.largo, caja1.profundo - caja2.profundo);

    }

    public int CompareTo(Caja caja2)
    {
        double volumenCaja1 = this.CalcularVolumen();
        double volumenCaja2 = caja2.CalcularVolumen();

        return volumenCaja1.CompareTo(volumenCaja2);
    }

    public override string ToString()
    {
    return "alto: " + this.alto + ", largo: " + this.largo + ", profundo: " + this.profundo;
    }
}   