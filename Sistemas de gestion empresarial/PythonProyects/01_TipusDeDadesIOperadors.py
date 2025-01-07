####################################################
## 1. Tipos de datos primitivos y operadores.
####################################################
# Tienes números
3 # => 3

# Matemática
1 + 1 # => 2
8 - 1 # => 7
10 * 2 # => 20

# Refuerza la precedencia con paréntesis
(1 + 3) * 2 # => 8

# Excepto la división la cual por defecto retorna un número 'float'
35 / 5 # => 7.0

# Sin embargo también tienes disponible división entera
34 // 5 # => 6

# Valores 'boolean' (booleanos) son tipos primitivos
True
False

# Niega con 'not'
not True # => False
not False # => True

# Igualdad es ==
1 == 1 # => True
2 == 1 # => False

# Desigualdad es !=
1 != 1 # => False
2 != 1 # => True

# Más comparaciones
1 < 10 # => True
1 > 10 # => False
2 <= 2 # => True
2 >= 2 # => True

# ¡Las comparaciones pueden ser concatenadas!
1 < 2 < 3 # => True
2 < 3 < 2 # => False

# Strings se crean con " o '
"Esto es un string."
'Esto también es un string'

# ¡Strings también pueden ser sumados!
"Hola " + "mundo!" # => "Hola mundo!"

# Un string puede ser tratado como una lista de caracteres
"Esto es un string"[0]

# .format puede ser usaro para darle formato a los strings, así:
"{} pueden ser {}".format("strings", "interpolados")

# Puedes reutilizar los argumentos de formato si estos se repiten.
"{0} sé ligero, {0} sé rápido, {0} brinca sobre la {1}".format("Jack",
"vela") # => "Jack sé ligero, Jack sé rápido, Jack brinca sobre la vela"

# Puedes usar palabras claves si no quieres contar.
"{nombre} quiere comer {comida}".format(nombre="Bob", comida="lasaña")
# => "Bob quiere comer lasaña"

# También puedes interpolar cadenas usando variables en el contexto
# Las f-strings, introducidas en las versión 3.6
nombre = 'Bob'
comida = 'Lasaña'
f'{nombre} quiere comer {comida}' # => "Bob quiere comer lasaña"

# None es un objeto ¡un singleton!
None # => None

# No uses el símbolo de igualdad `==` para comparar objetos con None
# Usa `is` en su lugar "etc" is None # => False
None is None # => True

# None, 0, y strings/listas/diccionarios/conjuntos vacíos(as) todos se evalúan como False.
# Todos los otros valores son True
# None es evaluado como False.
print(bool(None)) # => False

# El entero cero es evaluado como False.
print(bool(0)) # => False

# Una cadena vacía es evaluada como False.
print(bool("")) # => False

# Una lista vacía es evaluada como False.
print(bool([])) # => False

# Un diccionario vacío es evaluado como False.
print(bool({})) # => False

# Un conjunto vacío es evaluado como False.
print(bool(set())) # => False