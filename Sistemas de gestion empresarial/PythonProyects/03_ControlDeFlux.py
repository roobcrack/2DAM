# Creemos una variable para experimentar
una_variable = 5
# Aquí está una declaración de un 'if'. ¡La indentación es significativa en Python!
# imprime "una_variable es menor que 10"
if una_variable > 10:
    print("una_variable es mas grande que 10.")
elif una_variable < 10: # Este condición 'elif' es opcional.
    print("una_variable es mas chica que 10.")
else: # Esto también es opcional.
    print("una_variable es de hecho 10.")

### For itera sobre iterables (listas, cadenas, diccionarios, tuplas, generadores...)
""" imprime: perro es un mamifero gato es un mamifero raton es un mamifero
"""
for animal in ["perro", "gato", "raton"]:
    print("{} es un mamifero".format(animal))
""" `range(número)` retorna un generador de números desde cero hasta el número dado
imprime: 0 1 2 3 """
for i in range(4):
    print(i)
""" While itera hasta que una condición no se cumple. imprime: 0 1 2 3 """
x = 0
while x < 4:
    print(x)
    x += 1 # ¿Que pasa si tabulo esta linea al inicio?

##### Control de Excepciones
# try/except/finally - raise - pass
# Maneja excepciones con un bloque try/except
try:
    # Usa raise para levantar un error
    raise IndexError("Este es un error de indice")
except IndexError as e:
    pass # Pass no hace nada (“pasa”). Usualmente aquí harias alguna recuperacion.

# Bloque completo
try:
    # Código que puede generar una excepción
    resultado = 10 / 1
except ZeroDivisionError:
    # Maneja específicamente la excepción ZeroDivisionError
    print("División por cero.")
except Exception as e:
    # Maneja cualquier otra excepción
    print(f"Se produjo un error: {e}")
else:
    # Este bloque se ejecuta si no hay excepciones
    print("¡Éxito! No hubo errores.")
finally:
    # Este bloque se ejecuta siempre, haya o no una excepción
    print("Este bloque siempre se ejecuta.")

### Interfaz Iterable
""" Recordatorio: Una interfaz, en programación, es un concepto abstracto que define un conjunto de métodos y
    comportamientos que una clase debe implementar, pero no proporciona una implementación concreta de esos
    métodos. La interfaz especifica "qué" debe hacer una clase, pero no "cómo" debe hacerlo. """
""" Python usa el concepto de "protocolos" o "interfaces informales", que son más flexibles. Si un objeto
    implementa ciertos métodos, se dice que cumple con una interfaz o protocolo particular.
    Por ejemplo, un objeto es considerado un Iterable si implementa el método __iter__. No necesita heredar 
    explícitamente de una clase "Iterable" o declarar formalmente que implementa esa interfaz.
# Python ofrece una abstracción fundamental llamada Iterable. """
# Un Iterable en Python es cualquier objeto que pueda ser utilizado en un contexto donde se esperan secuencias de elementos (como un bucle for)
# El objeto es retornado por la función 'range' es un iterable.
for numero in range(5):  # 'range(5)' es un iterable
    print(numero)  # Imprime los números del 0 al 4

dicc_lleno = {"uno": 1, "dos": 2, "tres": 3}
nuestro_iterable = dicc_lleno.keys()
print(nuestro_iterable) # => dict_keys(['uno', 'dos', 'tres']). Este es un objeto que implementa nuestra interfaz Iterable Podemos recorrerla.
for i in nuestro_iterable:
    print(i) # Imprime uno, dos, tres

# Otra forma de imprimir los "indices" o claves
dicc_lleno = {"uno": 1, "dos": 2, "tres": 3}
for clave in dicc_lleno:
    print(clave)  # Imprime 'uno', 'dos', 'tres'

# Ahora vamos a imprimir los valroes
dicc_lleno = {"uno": 1, "dos": 2, "tres": 3}
for valor in dicc_lleno.values():
    print(valor)  # Imprime 1, 2, 3

#Y ahora indices y valores:
for clave, valor in dicc_lleno.items():
    print(f"Clave: {clave}, Valor: {valor}")
    # Imprime "Clave: uno, Valor: 1", "Clave: dos, Valor: 2", "Clave: tres, Valor: 3"

### Iteradores
# Aunque no podemos selecionar un elemento por su índice.
try: nuestro_iterable[1] # Genera un TypeError
except TypeError:
    print("No se puede acceder directamente a una posición del Iterable")

# Un iterable es un objeto que sabe como crear un iterador.
nuestro_iterator = iter(nuestro_iterable)
# Nuestro iterador es un objeto que puede recordar el estado mientras lo recorremos.
# Obtenemos el siguiente objeto llamando la función __next__.
nuestro_iterator.__next__() # => "uno"
# Mantiene el estado mientras llamamos __next__.
nuestro_iterator.__next__() # => "dos"
nuestro_iterator.__next__() # => "tres"
# Después que el iterador ha retornado todos sus datos, da una excepción StopIterator.
try:
    nuestro_iterator.__next__() # Genera StopIteration
except StopIteration:
    print("Ya había finalizado")

# Puedes obtener todos los elementos de un iterador llamando a list() en el.
list(dicc_lleno.keys()) # => Retorna ["uno", "dos", "tres"]

