# Descrição do projeto
Queremos desenvolver uma pequena api para gerenciar votação de pautas

Esse projeto foi criado com spring boot

# Explicação breve do porquê das escolhas tomadas durante o desenvolvimento da solução
 - A pauta será criada apenas com a descrição (mesmo que tente passar a data da abertura/fechamento não será possível)
 - A data definida da abertura será a data e hora do momento
 - A data do fechamento não poderá ser menor que a da abertura
 - Caso tente abrir um pauta já aberta não será possível
 - Caso tente votar em uma pauta fechada não será possível (pauta fechada pode ser sem data inicial e final, ou data 
   final já tenha passado do horário da solicitação)
 - Atividade bônus de validação do CPF foi separa no serviço de criar associado
 - O resultado da votação poderá ser acessado mesmo que não tenha finalizado o tempo ou a votação não esteja aberta.
 - Será informado a quantidade de votos sim e não, e a respota geral da votação.
 - Caso não tem votos ou empate a decisão será para reprovar (deixei dessa forma porque não tinha especificado na 
   solicitação) 
   

 - Obs: Por questões de tempo deixei de implementar documentação de API (Swagger) e testes unitários

# O que foi utilizado?

- validações de campos com Java Bean Validation
- controle de exceções com a anotação ControllerAdvice
- mapeamento de objetos com ModelMapper

# Configurar Lombok

Para configurar o lombok no seu ambiente utilize esse documento [Using Lombok](https://projectlombok.org/setup/overview)

# Banco de dados

Banco utilizado na api é o Mysql

# Postman

Rotas de exemplo da utilização da api

https://www.getpostman.com/collections/69cea0a84388b9e1f739
