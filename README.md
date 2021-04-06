# API
API Spring para teste de desenvolvedor

# Banco de Dados
É nescessário configurar o banco no application.properties

# Exemplo das chamadas
GET - http://localhost:8080/api/
GET - http://localhost:8080/api/receita/prato={prato}
GET - http://localhost:8080/api/listarTickets/mes={mes}&ano={ano}
POST - http://localhost:8080/api/criarTicket

# Exemplo de Json para criar Ticket
{
	"titulo": "Teste",
	"cliente": {
		"id": 1,
		"nome": "Apple Inc."
	},
	"modulo": {
		"id": 1,
		"nome": "Finaneiro"
	},
	"dataAbertura": "2021-03-01T03:00:00.000+00:00",
	"dataEncerramento": "2021-03-02T03:00:00.000+00:00"
}
