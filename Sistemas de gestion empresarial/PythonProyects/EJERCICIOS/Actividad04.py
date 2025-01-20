#El operador "is", hace una comparación de igualdad:

print("no" is "no") #=>True

#----
#El operador "not", hace una negacion:

print("no" is not "si") #=>True, dado que "no" y "si" son distintos

#----
#El operador "in", comprueba elementos dentro de un coleccionable:

lista = ["no", "noo", "si"]

print("no" in lista) #=>True, porque "no" esta en lista

#Tambien puede usarse para recorrer coleccionables:
for e in lista:
    print(e)