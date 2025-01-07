# Python tiene una función para imprimir
print("Soy Python. Encantado de conocerte")

# En python se declaran las varibales asignándoles un valor.
una_variable = 5 # La convención es usar guiones_bajos_con_minúsculas
una_variable # => 5
otraVariable = 3 # Aqui en formato Camel Case
otraVariable # => 3

# Acceder a variables no asignadas previamente es una excepción.
# Ve Control de Flujo para aprender más sobre el manejo de excepciones.
try:
    # Intenta imprimir una variable no asignada previamente
    print(otra_variable)
except Exception as e:
    # producirá esta execepción
    print(f"¡Error! {e}")

#############################
#       Listas              #
#############################
# Listas almacena secuencias
lista = []

# Puedes empezar con una lista con datos
otra_lista = [4, 5, 6]

# Añadir cosas al final de una lista con 'append'
lista.append(1) #lista ahora es [1]
lista.append(2) #lista ahora es [1, 2]
lista.append(4) #lista ahora es [1, 2, 4]
lista.append(3) #lista ahora es [1, 2, 4, 3]

# Eliminar del final de la lista con 'pop'
lista.pop() # => 3 y lista ahora es [1, 2, 4]
print(lista)
# Pongámoslo de vuelta
lista.append(3) # Nuevamente lista ahora es [1, 2, 4, 3].

# Accede a una lista como lo harías con cualquier array
lista[0] # => 1
# Mira el último elemento
lista[-1] # => 3
# Mirar fuera de los límites es un error 'IndexError'
try:
    lista[4]  # Levanta la excepción IndexError
except IndexError:
    print("¡fuera de indice!")

#### Generar listas a partir de listas con operador "[desdePosición:hastaPosición]"
####                                                "[ini:fin:DeCuantoEnCuanto]"
####         lista[inicio:fin] > El índice de inicio está incluido, pero el índice de fin no lo está
####         Nuestra lista tiene [1,2,4,3] en posiciones 0,1,2,3
# Puedes mirar por rango con la sintáxis de trozo.
lista[1:3] # => [2, 4]
# Omite el final
lista[2:] # => [4, 3]
# Omite el inicio
lista[:3] # => [1, 2, 4]
# Selecciona cada dos elementos
lista[::2] # =>[1, 4]
# Invierte la lista
lista[::-1] # => [3, 4, 2, 1]

# Remueve elementos de una lista con 'del'
del lista[2] # lista ahora es [1, 2, 3]

# Puedes sumar listas
lista + otra_lista # => [1, 2, 3, 4, 5, 6] - Nota: lista y otra_lista no se tocan
print(f"lista = {lista} y otra_lista = {otra_lista}")
lista += otra_lista # aquí si hemos añadido a lista los valores de "otra_lista"
print(lista)
# Concatenar listas con 'extend'
lista.extend(otra_lista) # lista ahora es [1, 2, 3, 4, 5, 6,4 ,5 ,6]

# Verifica la existencia en elemento una una lista con 'in'
# Esto verificará si el número 1 está presente en la lista
print(1 in lista)  # => True
# Esto verificará si el número 6 está presente en la lista
print(7 in lista)  # => False

# Examina el largo de una lista con 'len'
print(len(lista)) # => 9
print(lista)

####
#### Tuplas son como listas pero son inmutables, la diferencia estriba en el () en lugar del [].
####
tupla = (1, 2, 3)
tupla[0] # => 1
try:
    tupla[0] = 3 # Levanta un error TypeError
except Exception as e:
    print(f"Error producido: {e}")

# También puedes hacer todas esas cosas que haces con listas
len(tupla) # => 3
tupla += (4, 5, 6) # => (1, 2, 3, 4, 5, 6)
print(tupla)
tupla[:2] # => (1, 2)
2 in tupla # => True
# Operación "desempaquetado": Puedes desempaquetar tuplas (o listas) en variables
a, b, c = (1, 2, 3) # 'a' ahora es 1, 'b' ahora es 2 y 'c' ahora es 3
# Tuplas son creadas por defecto si omites los paréntesis
d, e, f = 4, 5, 6
# Ahora mira que fácil es intercambiar dos valores
e, d = d, e # d ahora es 5 y e ahora es 4

#####
##### Diccionarios relacionan claves y valores
#####
dicc_vacio = {}
# Aquí está un diccionario pre-rellenado
dicc_lleno = {"uno": 1, "dos": 2, "tres": 3}
# Busca valores con []
dicc_lleno["uno"] # => 1
print(dicc_lleno)
# Obtén todas las claves como una lista con 'keys()'. Necesitamos envolver la llamada en 'list()' porque obtenemos un iterable. Hablaremos de eso luego.
list(dicc_lleno.keys()) # => ["tres", "dos", "uno"]
# Nota - El orden de las claves del diccionario no está garantizada.
# Tus resultados podrían no ser los mismos del ejemplo.
# Obtén todos los valores como una lista. Nuevamente necesitamos envolverlas en una lista para sacarlas del iterable.
list(dicc_lleno.values()) # => [3, 2, 1]

# Nota - Lo mismo que con las claves, no se garantiza el orden.
# Verifica la existencia de una llave en el diccionario con 'in'
"uno" in dicc_lleno # => True
1 in dicc_lleno # => False

# Buscar una llave inexistente deriva en KeyError
try:
    dicc_lleno["cuatro"] # KeyError
except KeyError:
    print(f"Error en diccionario al acceder al valor")
# Método "get"
# Usa el método 'get' para evitar la excepción KeyError
dicc_lleno.get("uno") # => 1
dicc_lleno.get("cuatro") # => None
# El método 'get' soporta un argumento por defecto cuando el valor no existe.
dicc_lleno.get("uno", 4) # => 1
dicc_lleno.get("cuatro", 4) # => 4

# El método 'setdefault' inserta en un diccionario solo si la llave no está presente
dicc_lleno.setdefault("cinco", 5) #dicc_lleno["cinco"] es puesto con valor 5
dicc_lleno.setdefault("cinco", 6) #dicc_lleno["cinco"] todavía es 5

# Elimina claves de un diccionario con 'del'
del dicc_lleno['uno'] # Remueve la llave 'uno' de dicc_lleno

#####
##### # Sets (conjuntos) almacenan conjuntos: metodos "add"; operadores: '&' (unión) y '|' o '-' (diferencia)
##### Los conjuntos (sets) en Python se utilizan en situaciones donde es importante tener una colección no ordenada y sin elementos duplicados

conjunto_vacio = set()
# Conjunto a partir de una lista ¡observar que elimina los duplicados!:
mi_lista = [1, 2, 2, 3, 4, 4, 4, 5]
mi_conjunto = set(mi_lista)  # {1, 2, 3, 4, 5}

# Inicializar un conjunto con montón de valores.
un_conjunto = {1,2,2,3,4} # un_conjunto ahora es {1, 2, 3, 4}
conjunto_lleno = un_conjunto
# Añade más valores a un conjunto
conjunto_lleno.add(5) # conjunto_lleno ahora es {1, 2, 3, 4, 5}
conjunto_lleno.add(5) # conjunto_lleno ahora es {1, 2, 3, 4, 5} no "introduce" los valores ya existentes
conjunto_lleno.add(6) # conjunto_lleno ahora es {1, 2, 3, 4, 5, 6}
print(conjunto_lleno)
# operaciones de unión e interseccion
a = {1, 2, 3}
b = {3, 4, 5}
union = a | b  # {1, 2, 3, 4, 5}
interseccion = a & b  # {3}
# Haz diferencia de conjuntos con - o difference()
# devuelve un nuevo conjunto que contiene todos los elementos del primer conjunto que no están en el segundo conjunto
a = {1, 2, 3}
b = {3, 4, 5}
diferencia = a - b  # o a.difference(b)
print(diferencia)  # Resultado: {1, 2}

# Verifica la existencia del valor en un conjunto con 'in'
2 in conjunto_lleno # => True
10 in conjunto_lleno # => False
# Comprobar si un elemento está en un conjunto es muy rápido en comparación con hacerlo en una lista o una tupla, especialmente para colecciones grandes.