using System;
using System.Collections.Generic;

class Conjunto
{
    private List<int> lista;

    public Conjunto()
    {
        lista = new List<int>();
    }

    public bool Vaciar()
    {
        lista.Clear();
        return true;
    }

    public bool Agregar(int numero)
    {
        if (lista.Contains(numero))
            return false;
        lista.Add(numero);
        return true;
    }

    public bool Eliminar(int numero)
    {
        if (lista.Contains(numero))
        {
            lista.Remove(numero);
            return true;
        }
        return false;
    }

    public bool CopiarAEstaLista(List<int> lista)
    {
        this.lista = new List<int>(lista);
        return true;
    }

    public List<int> CopiarAOtraLista()
    {
        return this.lista;
    }

    public bool EsMiembro(int numero)
    {
        return lista.Contains(numero);
    }

    public bool EsIgual(List<int> lista)
    {
        return this.lista.SequenceEqual(lista);
    }

    public override string ToString()
    {
        return string.Join(" ", lista);
    } 

    public bool EsVacio()
    {
        return this.lista.Count == 0;
    }

    public int Cardinal()
    {
        return lista.Count;
    }

    public bool Union(List<int> lista)
    {
        this.lista = this.lista.Union(lista).ToList();
        return true;
    }

    public bool Interseccion(List<int> lista)
    {
        this.lista = this.lista.Intersect(lista).ToList();
        return true;
    }

    public bool Diferencia(List<int> lista)
    {
        this.lista = this.lista.Except(lista).ToList();
        return true;
    }

    public bool DiferenciaSimetrica(List<int> lista)
    {
        List<int> union = this.lista.Union(lista).ToList();
        List<int> interseccion = this.lista.Intersect(lista).ToList();
        this.lista = union.Except(interseccion).ToList();
        return true;
    }
}   