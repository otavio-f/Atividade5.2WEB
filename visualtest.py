#modificado: geracao de ids nao reseta para 0 entre testes
import requests #<-- required, install with pip
import json

#url = "https://first-heroku-app-is-a-spring.herokuapp.com/api/"
url = "http://localhost:8080/api/"

def search_first_id(endpoint: str) -> int:
	for valid_id in range(100):
		response = requests.get(f"{endpoint}/{valid_id}")
		if response.status_code == 200:
			break
	return valid_id

def is_equivalent(value: dict, another: dict) -> bool:
	#compara descartando os ids, usando => e =< entre tuplas do tipo ((chave, valor), (chave, valor), ...)
	#se a maior tupla conter todos os elementos da menor, retorna true
	#se houver elementos com chaves iguais, mas valores diferentes, retorna false
	#se a menor tupla conter alguma chave nao existente na tupla maior, retorna false
	return value.items() >= another.items() or value.items() <= another.items()

def delete_all(endpoint: str):
	contents = requests.get(endpoint).json()
	for data in contents:
		requests.delete(endpoint+str(data['id']))

#Teste visual

# T U R M A S #
print("***INICIANDO TESTES DOS ENDPOINTS***")

print("Limpando base de dados... ", end="")
delete_all(url+"turmas/")
delete_all(url+"alunos/")
print("Feito!")

endpoint = url+"turmas/"

# Insert #
dados = {"id": 0, "nome":"Musica", "turno":"noturno"}
print(f"\nInserindo uma turma... ", end="")
response = requests.post(endpoint, json=dados)
assert response.status_code == 201
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Musica", "turno":"noturno"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos: \n\t{response_data}")

# Insert #
dados = {"id": 0, "nome":"Teatro", "turno":"diurno"}
print(f"\nInserindo uma turma... ", end="")
response = requests.post(endpoint, json=dados)
assert response.status_code == 201
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Teatro", "turno":"diurno"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos: \n\t{response_data}")

# Get all turmas #
print("\nRecuperando dados de todas as turmas... ", end="")
response = requests.get(endpoint)
assert response.status_code == 200
response_data = response.json()
assert len(response_data) == 2
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos:\n\t\t{response_data[0]}\n\t\t{response_data[1]}")

# Get turma/1 #
endpoint = url+"turmas/1"
print("\nRecuperando dados de uma turma... ", end="")
response = requests.get(endpoint)
assert response.status_code == 200
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Musica", "turno":"noturno"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos:\n\t{response_data}")

# Modify turma/1 #
dados = response.json()
dados["nome"] = "Atletismo"
print("\nModificando turma anterior... ", end="")
response = requests.put(endpoint, json=dados)
assert response.status_code == 200
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Atletismo", "turno":"noturno"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos:\n\t{response_data}")

# Delete turma/1 #
endpoint = url+"turmas/1"
print("\nDeletando uma turma... ", end="")
response = requests.delete(endpoint)
assert response.status_code == 200
# Check if turma count is 1 #
response = requests.get(url+"turmas/")
response_data = response.json()
assert len(response_data) == 1
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados restantes:\n\t{response_data}")

# A L U N O S #

endpoint = url+"alunos/"

# Insert #
dados = {"id": 0, "nome":"Jose", "curso":"outro"}
print(f"\nInserindo um aluno... ", end="")
response = requests.post(endpoint, json=dados)
assert response.status_code == 201
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Jose", "curso":"outro"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos: \n\t{response_data}")

# Insert #
dados = {"id": 0, "nome":"Maria", "curso":"algum"}
print(f"\nInserindo um aluno... ", end="")
response = requests.post(endpoint, json=dados)
assert response.status_code == 201
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Maria", "curso":"algum"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos: \n\t{response_data}")

# Get all alunos #
print("\nRecuperando dados de todos os alunos... ", end="")
response = requests.get(endpoint)
assert response.status_code == 200
response_data = response.json()
assert len(response_data) == 2
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos:\n\t\t{response_data[0]}\n\t\t{response_data[1]}")

# Get aluno/1 #
a_id = search_first_id(url+"alunos")
endpoint = url+f"alunos/{a_id}"
print("\nRecuperando dados de um aluno... ", end="")
response = requests.get(endpoint)
assert response.status_code == 200
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Jose", "curso":"outro"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos:\n\t{response_data}")

# Modify aluno/1 #
dados = response.json()
dados["nome"] = "Joao"
print("\nModificando aluno anterior... ", end="")
response = requests.put(endpoint, json=dados)
assert response.status_code == 200
response_data = response.json()
assert is_equivalent(response_data, {"nome":"Joao", "curso":"outro"})
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados recebidos:\n\t{response_data}")

# Delete aluno/id #
a_id = search_first_id(url+"alunos")
endpoint = url+f"alunos/{a_id}"
print("\nDeletando um aluno... ", end="")
response = requests.delete(endpoint)
assert response.status_code == 200
# Check if aluno count is 1 #
response = requests.get(url+"alunos/")
response_data = response.json()
assert len(response_data) == 1
print(f"OK!\n\tCodigo {response.status_code}.\n\tDados restantes:\n\t{response_data}")

print("\n\nLimpando base de dados... ", end="")
delete_all(url+"turmas/")
delete_all(url+"alunos/")
print("Tudo OK!\nPressione uma tecla para sair.")

input()