#1st function
def addNumbers(a, b):
    return a + b

print(addNumbers(8,4)) #=>12



#2nd function
numbers = [2,4,5]

def multiplyList(lista):
    for i in range(len(lista)):
        lista[i] *= 2

multiplyList(numbers)
print(numbers)



#3rd function
numbers = [5,6,7]

def multiplyListCopy(numbers):
    tempList = []
    for i in range(len(numbers)):
        tempList.append(numbers[i] * 2)
    return tempList

print(multiplyListCopy(numbers))