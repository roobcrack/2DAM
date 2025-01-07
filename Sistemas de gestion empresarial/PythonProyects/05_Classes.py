####################################################
## 5. Clases
####################################################
# Heredamos de object para obtener una clase.
class Humano(object):
    # Un atributo de clase es compartido por todas las instancias de esta clase
    _especie = "H. sapiens" # por convención con "_" NO debe ser accedido desde fuera de la propia clase (privado)

    # Constructor basico
    def __init__(self, nombre, edad):
        # Asigna el argumento al atributo nombre de la instancia
        self._nombre = nombre
        self._edad = edad

    @property
    def nombre(self):
        return self._nombre

    @nombre.setter
    def nombre(self, valor):
        # Agregar lógica adicional si es necesario antes de establecer el nombre
        self._nombre = valor

    @property
    def edad(self):
        return self._edad

    @edad.setter
    def edad(self, valor):
        # Agregar lógica adicional si es necesario antes de establecer la edad
        self._edad = valor

    # Un metodo de instancia. Todos los metodos toman self como primer argumento
    def decir(self, msg):
        return "%s: %s" % (self.nombre, msg) #% es el operador de formato

    # Un metodo de clase es compartido a través de todas las instancias
    # Son llamados con la clase como primer argumento
    @classmethod
    def get_especie(cls): #cls se refiere a la propia clase. se usa por "convencion"
        return cls._especie

    # Un metodo estatico es llamado sin la clase o instancia como referencia
    @staticmethod
    def dormir():
        return "*durmiendo*"

# Instancia una clase
i = Humano(nombre="Edu",edad="20")
print(i.decir("hola")) # imprime "edu: hola"
j = Humano("Paula",33)
print(j.decir("bienvenido")) #imprime "Paula: bienvenido"

# Llama nuestro método de clase
print(f"Los humanos son: {i.get_especie()}") # => Los humanos son: H. sapiens

# Cambia los atributos compartidos
Humano.especie = "H. neanderthalensis"
i.get_especie() # => "H. neanderthalensis"
j.get_especie() # => "H. neanderthalensis"

# Llama al método estático
Humano.dormir() # => "*durmiendo*"