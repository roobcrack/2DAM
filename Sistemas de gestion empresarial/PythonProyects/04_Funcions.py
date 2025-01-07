####################################################
# 4. Funciones
####################################################
# Usa 'def' para crear nuevas funciones
def add(x, y):
    print("x es {} y y es {}".format(x, y))
    return x + y  # Retorna valores con la declaración return

# Llamando funciones con parámetros
add(5, 6)  # => imprime "x es 5 y y es 6" y retorna 11
# Otra forma de llamar funciones es con argumentos de palabras claves
add(y=6, x=5)  # Argumentos de palabra clave pueden ir en cualquier orden.

# Puedes definir funciones que tomen un número variable de argumentos
# Cuando defines una función y usas *args en los parámetros, estás indicando que la función puede aceptar una cantidad
# arbitraria de argumentos posicionales. Estos argumentos se pasarán a la función como una tupla.
def variosArgumentos(*args):
    return args

variosArgumentos(1, 2, 3)  # => (1,2,3)

# Ejemplo práctico
# Esta función calcula la media de cualquier cantidad de números pasados como argumentos
def calcular_media(*numeros):
    total = sum(numeros)  # Suma todos los números en la tupla 'numeros'
    cantidad = len(numeros)  # Cuenta la cantidad de números proporcionados
    if cantidad == 0:
        return 0  # Evita la división por cero si 'numeros' está vacío
    return total / cantidad  # Calcula la media

# Ejemplo de uso de la función con diferentes cantidades de argumentos
media1 = calcular_media(23, 45, 67, 89)  # Cuatro números
media2 = calcular_media(10, 20)  # Dos números
media3 = calcular_media(100)  # Un número
media4 = calcular_media()  # Ningún número

print("Media 1:", media1)  # Salida: Media de cuatro números
print("Media 2:", media2)  # Salida: Media de dos números
print("Media 3:", media3)  # Salida: Media de un número
print("Media 4:", media4)  # Salida: Media de cero números, se maneja evitando la división por cero


# De manera similar, **kwargs permite pasar una cantidad arbitraria de argumentos de palabras clave (keyword arguments),
# los cuales la función recibirá como un diccionario.
def variosArgumentosClaveValor(**diccionario):
    return diccionario
# Llamémosla para ver que sucede
variosArgumentosClaveValor(pie="grande", lago="ness")  # => {"pie": "grande", "lago": "ness"}
print(f'Esto contiene => {variosArgumentosClaveValor(pie="grande", lago="ness")}')

# Un uso práctico para una función que acepta un número variable de argumentos de palabras clave (**kwargs) es cuando
# necesitas manejar configuraciones o parámetros que pueden variar.
def crear_perfil_usuario(**atributos_usuario):
    perfil = {}
    for atributo, valor in atributos_usuario.items():
        perfil[atributo] = valor
    return perfil

# Crear perfiles de usuario con diferentes atributos
perfil1 = crear_perfil_usuario(nombre="Juan", edad=30, email="juan@example.com")
perfil2 = crear_perfil_usuario(nombre="Ana", pais="España", suscripcion_activa=True)
perfil3 = crear_perfil_usuario(nombre="Luis", edad=42, email="luis@example.com", premium_member=False)

print(perfil1)  # {'nombre': 'Juan', 'edad': 30, 'email': 'juan@example.com'}
print(perfil2)  # {'nombre': 'Ana', 'pais': 'España', 'suscripcion_activa': True}
print(perfil3)  # {'nombre': 'Luis', 'edad': 42, 'email': 'luis@example.com', 'premium_member': False}

# Puedes hacer ambas a la vez si quieres
def todos_los_argumentos(*args, **kwargs):
    print(args)
    print(kwargs)
"""
todos_los_argumentos(1, 2, a=3, b=4) imprime: (1, 2) {"a": 3, "b": 4}
"""
# ¡Cuando llames funciones, puedes hacer lo opuesto a varargs/kwargs!
# Usa * para expandir tuplas y usa ** para expandir argumentos de palabras / claves.
args = (1, 2, 3, 4)
kwargs = {"a": 3, "b": 4}
todos_los_argumentos(*args)  # salida: (1, 2, 3, 4) {}
todos_los_argumentos(**kwargs)  # salida: () {'a': 3, 'b': 4}
todos_los_argumentos(*args, **kwargs) # salida: (1, 2, 3, 4) {'a': 3, 'b': 4}

'''
 Puede ser extremadamente útil para crear funciones flexibles y dinámicas que pueden manejar diferentes tipos de datos
 de entrada. Aquí tienes un ejemplo práctico de una función que puede tomar cualquier combinación de argumentos 
 posicionales y argumentos de palabras clave, procesarlos y realizar alguna operación con ellos.
'''
def registrar_evento(*eventos, **detalles_evento):
    print("Registrando eventos...")
    for evento in eventos:
        print(f"Evento: {evento}")

    print("\nDetalles del evento:")
    for clave, valor in detalles_evento.items():
        print(f"{clave}: {valor}")

# Ejemplo de uso de la función con argumentos posicionales y de palabras clave
registrar_evento("Concierto", "Festival", fecha="2024-01-01", ubicacion="Parque Central")

############################################
###     Funciones de Order Superior
def crear_suma(x):
    def suma(y):
        return x + y
    return suma
sumar_10 = crear_suma(10)
a = sumar_10(3) # => 13
print(f"la suma es: {a}")

############################################
###     Funciones anonimas
# lambda es una palabra clave en Python que se utiliza para crear funciones anónimas (es decir, funciones sin un nombre).
(lambda x: x > 2)(3) # => True

# Hay funciones integradas de orden superior, por ejemplo:
# La función map toma una función y un iterable (como una lista) y aplica esa función a cada elemento del iterable
objeto = map(sumar_10, [1,2,3])
print(list(objeto)) # => [11, 12, 13]
# De manera similar, filter toma una función y un iterable, y devuelve un nuevo iterable con los elementos para los
# cuales la función retorna True
filter(lambda x: x > 5, [3, 4, 5, 6, 7]) # => [6, 7]
## Para saber: en Python 3, las funciones map y filter devuelven objetos iteradores "perezosos" (lazy loading), lo que significa
# que generan los elementos uno a la vez y en tiempo real, en lugar de calcular todos los elementos de una vez y
# almacenarlos en una lista, por ejemplo. Por lo cuál, para imprimirlos los pasamos a "lista".
# Son más eficientes ya que no esperan a tener todos para "guardarlos"


# Podemos usar listas por comprensión para mapeos y filtros agradables
[sumar_10(i) for i in [1, 2, 3]] # => [11, 12, 13]
[x for x in [3, 4, 5, 6, 7] if x > 5] # => [6, 7]
# también hay diccionarios
{k:k**2 for k in range(3)} # => {0: 0, 1: 1, 2: 4}
# y conjuntos por comprensión
{c for c in "la cadena"} # => {'d', 'l', 'a', 'n', ' ', 'c', 'e'}