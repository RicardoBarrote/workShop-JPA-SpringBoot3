# Projeto WorkShop

## Descrição

O **WorkShop** é um projeto de software para gerenciar e operar uma loja online, abrangendo funcionalidades para categorias de produtos, gerenciamento de pedidos e controle de pagamentos. A aplicação é desenvolvida utilizando o framework Spring Boot e é configurada para utilizar uma base de dados PostgreSQL, com suporte para operações CRUD completas e manipulação de relacionamentos entre entidades.

## Funcionalidades

- **Gerenciamento de Categorias**: Criação, leitura, atualização e exclusão de categorias de produtos.
- **Gerenciamento de Produtos**: Adição de produtos, atribuição de categorias e visualização de detalhes de produtos.
- **Gerenciamento de Pedidos**: Criação e atualização de pedidos, com detalhes sobre produtos e quantidades.
- **Gerenciamento de Pagamentos**: Registro de pagamentos associados a pedidos.

## Tecnologias

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
- **JPA/Hibernate**: Framework de mapeamento objeto-relacional para persistência de dados.
- **RESTful API**: Arquitetura para construção de APIs com endpoints HTTP.

## Estrutura do Projeto

- **Entities**: Classes que representam as tabelas do banco de dados.
- **Repositories**: Interfaces para acesso aos dados das entidades.
- **Services**: Lógica de negócios e manipulação de dados.
- **Controllers**: Endpoints da API para interação com o usuário.
- **Exceptions**: Classes para tratamento de erros e retorno de mensagens padronizadas.
- **Enums**: Definição de valores constantes para estados e tipos.

## Configuração do Ambiente

1. **Banco de Dados**: Configure o PostgreSQL com as credenciais apropriadas e ajuste o arquivo `application.properties` para conectar ao banco de dados.
2. **Execução do Projeto**: Utilize o Maven para construir e executar a aplicação.

## Como Contribuir

1. Faça um fork do repositório.
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`).
3. Faça as alterações e commit (`git commit -am 'Add new feature'`).
4. Envie a branch (`git push origin feature/nova-feature`).
5. Crie uma Pull Request.

## Contato

Para mais informações, entre em contato com [e-mail](ricardoambscfc@gmail.com).



# Entidade User

A classe `User` representa uma entidade de usuário na aplicação, mapeada para a tabela `tb_users` no banco de dados. Essa entidade contém informações básicas do usuário, como nome, email, senha e telefone, além de manter uma lista de pedidos associados ao usuário.

## Anotações

- `@Entity`: Especifica que essa classe é uma entidade e está mapeada para uma tabela no banco de dados.
- `@Table(name = "tb_users")`: Especifica o nome da tabela no banco de dados para a qual essa entidade está mapeada.
- `@Id`: Marca a chave primária da entidade.
- `@GeneratedValue(strategy = GenerationType.IDENTITY)`: Especifica a estratégia de geração da chave primária. Neste caso, é auto-incrementada.
- `@Column(nullable = false)`: Especifica que a coluna não pode ser nula no banco de dados.
- `@JsonIgnore`: Indica que a lista `orders` deve ser ignorada durante a serialização para JSON.

## Campos

- `Integer id`: O identificador único do usuário. Esta é a chave primária da tabela `tb_users`.
- `String name`: O nome do usuário. Este campo é obrigatório.
- `String email`: O email do usuário. Este campo é obrigatório.
- `String password`: A senha do usuário. Este campo é obrigatório.
- `String phone`: O número de telefone do usuário. Este campo é obrigatório.
- `List<Order> orders`: Uma lista de pedidos associados ao usuário. Este campo é mapeado pelo campo `client` na entidade `Order` e é ignorado durante a serialização para JSON.

## Construtores

- `User()`: Construtor padrão.
- `User(Integer id, String name, String email, String password, String phone)`: Construtor parametrizado para inicializar um objeto `User` com valores específicos.
- `User(UserRequestPayLoad payLoad)`: Construtor que inicializa um objeto `User` com base em um objeto `UserRequestPayLoad`.

## Métodos

- `Integer getId()`: Retorna o ID do usuário.
- `void setId(Integer id)`: Define o ID do usuário.
- `String getName()`: Retorna o nome do usuário.
- `void setName(String name)`: Define o nome do usuário.
- `String getEmail()`: Retorna o email do usuário.
- `void setEmail(String email)`: Define o email do usuário.
- `String getPassword()`: Retorna a senha do usuário.
- `void setPassword(String password)`: Define a senha do usuário.
- `String getPhone()`: Retorna o número de telefone do usuário.
- `void setPhone(String phone)`: Define o número de telefone do usuário.
- `List<Order> getOrders()`: Retorna a lista de pedidos associados ao usuário.
- `boolean equals(Object o)`: Compara este usuário com outro objeto para verificar a igualdade com base no ID.
- `int hashCode()`: Retorna um código hash para o usuário com base no ID.

## Relacionamentos

- `@OneToMany(mappedBy = "client")`: Especifica um relacionamento de um-para-muitos entre `User` e `Order`, onde `User` é a entidade pai e `Order` é a entidade filha. O atributo `mappedBy` indica que o campo `client` na entidade `Order` possui o relacionamento.

## Exemplo de Uso

```java
User user = new User(null, "João Silva", "joao@example.com", "senha123", "555-1234");
System.out.println(user.getName()); // Saída: João Silva
```

# Interface UserRepository

A interface `UserRepository` é um repositório Spring Data JPA que fornece operações CRUD (Create, Read, Update, Delete) para a entidade `User`. Esta interface não requer a implementação de métodos, pois herda métodos padrão do `JpaRepository`.

## Extensão

- `JpaRepository<User, Integer>`: Extende a interface `JpaRepository`, que oferece métodos para realizar operações com a entidade `User` no banco de dados. Os dois parâmetros genéricos são:
  - `User`: A entidade para a qual o repositório é criado.
  - `Integer`: O tipo de dado da chave primária (`id`) da entidade `User`.

## Métodos Herdados

A interface `UserRepository` herda vários métodos do `JpaRepository`, incluindo, mas não se limitando a:

- `List<User> findAll()`: Retorna uma lista de todos os usuários.
- `Optional<User> findById(Integer id)`: Retorna um usuário pelo seu ID, se existir.
- `User save(User user)`: Salva ou atualiza um usuário.
- `void deleteById(Integer id)`: Exclui um usuário pelo seu ID.
- `boolean existsById(Integer id)`: Verifica se um usuário com o ID especificado existe.

# Classe UserService

A classe `UserService` é uma classe de serviço no Spring que encapsula a lógica de negócio relacionada à entidade `User`. Esta classe interage com o `UserRepository` para realizar operações como buscar todos os usuários, buscar um usuário por ID, criar um novo usuário, atualizar um usuário existente e deletar um usuário.

## Anotações

- `@Service`: Indica que essa classe é um componente de serviço do Spring, responsável por executar a lógica de negócio.

## Injeção de Dependência

- `@Autowired`: O Spring injeta automaticamente uma instância do `UserRepository` na classe `UserService`, permitindo a interação com o banco de dados.

## Métodos

### `List<User> findAll()`
Este método retorna uma lista com todos os usuários no sistema.

**Exemplo de Uso:**
```java
List<User> users = userService.findAll();
```

### `User findById(Integer id)`
Este método busca um usuário pelo seu ID. Se o usuário não for encontrado, lança uma exceção ResourcerNotFound.

**Exemplo de uso:**
```java
User user = userService.findById(1);
```

### `User createdUser(UserRequestPayLoad payload)`
Este método cria um novo usuário com base nos dados recebidos em UserRequestPayLoad e o salva no banco de dados.

**Exemplo de uso:**
```java
UserRequestPayLoad payLoad = new UserRequestPayLoad("Nome", "email@example.com", "senha", "telefone");
User user = userService.createdUser(payLoad);
```

### `void delete (Integer id)` 
Este método exclui um usuário pelo seu ID. Se o usuário não for encontrado, lança uma exceção NoSuchElementException com uma mensagem personalizada.

**Exemplo de uso:**
```java
userService.delete(1);
```

### `User updateUser(Integer id, UserRequestPayLoad payLoad)`
Este método atualiza as informações de um usuário existente com base nos dados recebidos em UserRequestPayLoad. Se o usuário não for encontrado, lança uma exceção ResourcerNotFound.

**Exemplo de uso:**
```java
UserRequestPayLoad payLoad = new UserRequestPayLoad("Nome Atualizado", "email_atualizado@example.com", "nova_senha", "novo_telefone");
User updatedUser = userService.updateUser(1, payLoad);
```
# Classe UserController

A classe `UserController` gerencia as requisições HTTP relacionadas aos usuários e utiliza o `UserService` para executar a lógica de negócio.

## Métodos

### `ResponseEntity<List<User>> getAllUser()`
- **Método HTTP:** `GET`
- **URL:** `/users`
- **Descrição:** Retorna uma lista de todos os usuários.

### `ResponseEntity<User> getUserById(@PathVariable Integer id)`
- **Método HTTP:** `GET`
- **URL:** `/users/{id}`
- **Descrição:** Retorna um usuário específico pelo ID fornecido.

### `ResponseEntity<User> createdUser(@RequestBody UserRequestPayLoad payLoad)`
- **Método HTTP:** `POST`
- **URL:** `/users`
- **Descrição:** Cria um novo usuário com base nos dados fornecidos e retorna o usuário criado.

### `ResponseEntity<User> deleteUser(@PathVariable Integer id)`
- **Método HTTP:** `DELETE`
- **URL:** `/users/{id}`
- **Descrição:** Exclui o usuário com o ID fornecido.

### `ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserRequestPayLoad payLoad)`
- **Método HTTP:** `PUT`
- **URL:** `/users/{id}`
- **Descrição:** Atualiza o usuário com o ID fornecido com base nos dados fornecidos e retorna o usuário atualizado.


# Classe Order

A classe `Order` representa um pedido em um sistema de gerenciamento de pedidos. Ela está mapeada para a tabela `tb_orders` no banco de dados.

## Atributos

- `Integer id`: Identificador único do pedido. É gerado automaticamente.
- `LocalDateTime moment`: Data e hora em que o pedido foi criado.
- `User client`: Usuário associado ao pedido.
- `Integer orderStatus`: Status do pedido, representado por um código.
- `Set<OrderItem> items`: Conjunto de itens associados ao pedido.
- `Payment payment`: Detalhes do pagamento relacionado ao pedido.

## Construtores

- `Order()`: Construtor padrão.
- `Order(Integer id, LocalDateTime moment, OrderStatus orderStatus, User client)`: Construtor utilizado para o seeding de teste.
- `Order(OrderRequestPayLoad payLoad, User user)`: Construtor utilizado para criar um pedido com base nos dados do payload e no usuário.

## Métodos

- `getId()`: Retorna o ID do pedido.
- `setId(Integer id)`: Define o ID do pedido.
- `getMoment()`: Retorna a data e hora do pedido.
- `setMoment(LocalDateTime moment)`: Define a data e hora do pedido.
- `getClient()`: Retorna o usuário associado ao pedido.
- `setClient(User client)`: Define o usuário associado ao pedido.
- `getPayment()`: Retorna os detalhes do pagamento (ignorando o campo durante a serialização JSON).
- `setPayment(Payment payment)`: Define os detalhes do pagamento.
- `getItems()`: Retorna o conjunto de itens associados ao pedido.
- `getOrderStatus()`: Retorna o status do pedido como um `OrderStatus`.
- `setOrderStatus(OrderStatus status)`: Define o status do pedido com base em um `OrderStatus`.
- `getTotal()`: Calcula e retorna o valor total do pedido com base nos subtotais dos itens.
- `equals(Object o)`: Compara o pedido com outro objeto para verificar igualdade.
- `hashCode()`: Gera um código hash baseado no ID do pedido.

## Observações

- O método `getTotal()` calcula a soma total do pedido com base nos subtotais dos itens.

# Interface OrderRepository

A interface `OrderRepository` é um repositório Spring Data JPA que fornece operações de acesso a dados para a entidade `Order`. Ela estende a interface `JpaRepository`, o que permite realizar operações CRUD e consultas adicionais sem a necessidade de implementar métodos manualmente.

## Extensão

- `JpaRepository<Order, Integer>`: Esta interface estende `JpaRepository`, que fornece métodos para realizar operações de persistência em uma entidade `Order`. O tipo `Order` é a entidade associada e `Integer` é o tipo do identificador da entidade.

## Funcionalidades

Como `OrderRepository` estende `JpaRepository`, ele herda uma série de métodos úteis, como:

- **Salvar um pedido:** `save(Order order)`
- **Buscar todos os pedidos:** `findAll()`
- **Buscar um pedido por ID:** `findById(Integer id)`
- **Excluir um pedido:** `deleteById(Integer id)`

Esses métodos permitem operações básicas de CRUD (criar, ler, atualizar e deletar) sem a necessidade de implementações adicionais.

# Classe OrderService

A classe `OrderService` gerencia a lógica de negócio relacionada aos pedidos (`Order`). Ela utiliza os repositórios `OrderRepository`, `UserRepository`, `ProductRepository`, e `OrderItemRepository` para realizar operações sobre os pedidos e seus itens.

## Dependências

- `OrderRepository`: Repositório para operações com pedidos.
- `UserRepository`: Repositório para operações com usuários.
- `ProductRepository`: Repositório para operações com produtos.
- `OrderItemRepository`: Repositório para operações com itens de pedidos.

## Métodos

### `List<Order> findAll()`
- **Descrição:** Retorna uma lista de todos os pedidos.

### `Order findById(Integer id)`
- **Descrição:** Retorna um pedido específico pelo ID fornecido. Lança uma exceção se o pedido não for encontrado.

### `Order createdOrder(OrderRequestPayLoad payLoad)`
- **Descrição:** Cria um novo pedido com base nos dados fornecidos e retorna o pedido criado. Utiliza o ID do usuário para associar o pedido ao usuário correspondente.

### `void deleteOrder(Integer id)`
- **Descrição:** Exclui o pedido com o ID fornecido. Lança uma exceção se o pedido não for encontrado.

### `Order addProductToOrder(Integer orderId, Integer productId, Integer quantity)`
- **Descrição:** Adiciona um produto ao pedido especificado pelo ID. Se o pedido ou o produto não forem encontrados, lança uma exceção. Retorna o pedido atualizado.

### `void deleteProductToOrder(Integer orderId, Integer productId)`
- **Descrição:** Remove um produto do pedido especificado pelo ID. Se o pedido ou o produto não forem encontrados, ou se o item do pedido não existir, lança uma exceção.

### `Order paymentOrder(Integer id, PaymentRequestPayLoad payLoad)`
- **Descrição:** Processa o pagamento para o pedido especificado pelo ID e atualiza o status do pedido para `PAID`. Retorna o pedido atualizado com o pagamento associado.

# Classe OrderController

A classe `OrderController` expõe endpoints REST para gerenciar pedidos (`Order`). Ela utiliza o serviço `OrderService` para realizar operações relacionadas aos pedidos.

## Endpoints

### `GET /orders`
- **Descrição:** Retorna uma lista de todos os pedidos.
- **Resposta:** `200 OK` com a lista de pedidos.

### `GET /orders/{id}`
- **Descrição:** Retorna um pedido específico pelo ID fornecido.
- **Parâmetros:** `id` - ID do pedido.
- **Resposta:** `200 OK` com o pedido correspondente.

### `POST /orders`
- **Descrição:** Cria um novo pedido com base nos dados fornecidos.
- **Corpo da Requisição:** `OrderRequestPayLoad` - Dados do pedido.
- **Resposta:** `201 Created` com o pedido criado e o URI do novo recurso.

### `DELETE /orders/{id}`
- **Descrição:** Exclui o pedido com o ID fornecido.
- **Parâmetros:** `id` - ID do pedido.
- **Resposta:** `204 No Content` se o pedido for excluído com sucesso.

### `POST /orders/{id}/products`
- **Descrição:** Adiciona um produto ao pedido especificado pelo ID.
- **Parâmetros:** `id` - ID do pedido.
- **Corpo da Requisição:** `OrderItemRequestPayLoad` - Dados do produto a ser adicionado (ID do produto e quantidade).
- **Resposta:** `200 OK` com o pedido atualizado.

### `DELETE /orders/{idOrder}/products/{idProduct}`
- **Descrição:** Remove um produto do pedido especificado pelos IDs do pedido e do produto.
- **Parâmetros:** `idOrder` - ID do pedido, `idProduct` - ID do produto.
- **Resposta:** `204 No Content` se o produto for removido com sucesso.

### `POST /orders/{id}/payment`
- **Descrição:** Processa o pagamento para o pedido especificado pelo ID.
- **Parâmetros:** `id` - ID do pedido.
- **Corpo da Requisição:** `PaymentRequestPayLoad` - Dados do pagamento.
- **Resposta:** `200 OK` com o pedido atualizado e o pagamento associado.

# Classe Payment

A classe `Payment` representa o pagamento associado a um pedido (`Order`). 

## Atributos

- `id` (Integer): Identificador único do pagamento.
- `moment` (LocalDateTime): Data e hora em que o pagamento foi efetuado.
- `order` (Order): Pedido associado ao pagamento. 

## Construtores

- **Payment()**: Construtor padrão.
- **Payment(Integer id, LocalDateTime moment, Order order)**: Construtor com todos os atributos.
- **Payment(PaymentRequestPayLoad payLoad, Order order)**: Construtor que utiliza um payload de requisição e um pedido para criar um pagamento.

## Métodos

- **Integer getId()**: Retorna o ID do pagamento.
- **void setId(Integer id)**: Define o ID do pagamento.
- **LocalDateTime getMoment()**: Retorna o momento do pagamento.
- **void setMoment(LocalDateTime moment)**: Define o momento do pagamento.
- **Order getOrder()**: Retorna o pedido associado ao pagamento.
- **void setOrder(Order order)**: Define o pedido associado ao pagamento.
- **boolean equals(Object o)**: Compara o pagamento atual com outro objeto para verificar igualdade.
- **int hashCode()**: Retorna o código hash do pagamento.

# Classe Category

A classe `Category` representa uma categoria de produtos no sistema.

## Atributos

- `id` (Integer): Identificador único da categoria.
- `name` (String): Nome da categoria.
- `products` (Set<Product>): Conjunto de produtos associados a esta categoria. A relação é mapeada pela propriedade `categories` na entidade `Product`.

## Construtores

- **Category()**: Construtor padrão.
- **Category(Integer id, String name)**: Construtor com todos os atributos.
- **Category(CategoryRequestPayLoad payLoad)**: Construtor que utiliza um payload de requisição para criar uma categoria.

## Métodos

- **Integer getId()**: Retorna o ID da categoria.
- **void setId(Integer id)**: Define o ID da categoria.
- **String getName()**: Retorna o nome da categoria.
- **void setName(String name)**: Define o nome da categoria.
- **Set<Product> getProducts()**: Retorna o conjunto de produtos associados a esta categoria.
- **boolean equals(Object o)**: Compara a categoria atual com outro objeto para verificar igualdade.
- **int hashCode()**: Retorna o código hash da categoria.

# Interface CategoryRepository

A interface `CategoryRepository` é responsável pelas operações de acesso aos dados da entidade `Category`.

## Extensão

- **Extende**: `JpaRepository<Category, Integer>`

## Métodos

Como extensão da interface `JpaRepository`, o `CategoryRepository` herda todos os métodos padrão para operações CRUD e consultas básicas.

# Classe CategoryService

O `CategoryService` fornece a lógica de negócios para a manipulação de categorias.

## Anotações

- **@Service**: Define a classe como um serviço Spring.

## Dependências

- **CategoryRepository**: Usado para acessar os dados das categorias.

## Métodos

### `findAll()`
- **Descrição**: Recupera todas as categorias.
- **Retorno**: `List<Category>`

### `findById(Integer id)`
- **Descrição**: Recupera uma categoria pelo seu ID.
- **Parâmetros**:
  - `id`: O ID da categoria a ser recuperada.
- **Retorno**: `Category`
- **Exceções**:
  - Lança `ResourcerNotFound` se a categoria não for encontrada.

### `createdCategory(CategoryRequestPayLoad payLoad)`
- **Descrição**: Cria uma nova categoria com base no payload fornecido.
- **Parâmetros**:
  - `payLoad`: Dados necessários para criar a categoria.
- **Retorno**: `Category`
- **Exceções**:
  - Lança `PropertyNull` se o nome da categoria for nulo.

### `deleteCategory(Integer id)`
- **Descrição**: Remove uma categoria pelo seu ID.
- **Parâmetros**:
  - `id`: O ID da categoria a ser removida.
- **Retorno**: `void`
- **Exceções**:
  - Lança `NoSuchElementException` se a categoria não for encontrada.

### `updateCategory(Integer id, CategoryRequestPayLoad payLoad)`
- **Descrição**: Atualiza uma categoria existente com base no payload fornecido.
- **Parâmetros**:
  - `id`: O ID da categoria a ser atualizada.
  - `payLoad`: Dados necessários para atualizar a categoria.
- **Retorno**: `Category`
- **Exceções**:
  - Lança `ResourcerNotFound` se a categoria não for encontrada.

# Classe CategoryController

O `CategoryController` fornece endpoints HTTP para manipulação de categorias.

## Anotações

- **@RestController**: Define a classe como um controlador REST.
- **@RequestMapping(value = "/categories")**: Mapeia as solicitações HTTP para os endpoints relacionados a categorias.

## Dependências

- **CategoryService**: Usado para a lógica de negócios das categorias.

## Métodos

### `findAll()`
- **Método HTTP**: `GET`
- **Descrição**: Recupera todas as categorias.
- **Retorno**: `ResponseEntity<List<Category>>`

### `findById(@PathVariable Integer id)`
- **Método HTTP**: `GET`
- **Descrição**: Recupera uma categoria pelo seu ID.
- **Parâmetros**:
  - `id`: O ID da categoria a ser recuperada.
- **Retorno**: `ResponseEntity<Category>`

### `createdCategory(@RequestBody CategoryRequestPayLoad payLoad)`
- **Método HTTP**: `POST`
- **Descrição**: Cria uma nova categoria com base no payload fornecido.
- **Parâmetros**:
  - `payLoad`: Dados necessários para criar a categoria.
- **Retorno**: `ResponseEntity<Category>`

### `deleteCategory(@PathVariable Integer id)`
- **Método HTTP**: `DELETE`
- **Descrição**: Remove uma categoria pelo seu ID.
- **Parâmetros**:
  - `id`: O ID da categoria a ser removida.
- **Retorno**: `ResponseEntity<Void>`

### `updateCategory(@PathVariable Integer id, @RequestBody CategoryRequestPayLoad payLoad)`
- **Método HTTP**: `PUT`
- **Descrição**: Atualiza uma categoria existente com base no payload fornecido.
- **Parâmetros**:
  - `id`: O ID da categoria a ser atualizada.
  - `payLoad`: Dados necessários para atualizar a categoria.
- **Retorno**: `ResponseEntity<Category>`

# Classe Product

A classe `Product` representa um produto no sistema e está mapeada para a tabela `tb_product`.

## Anotações

- **@Entity**: Define a classe como uma entidade JPA.
- **@Table(name = "tb_product")**: Mapeia a entidade para a tabela `tb_product`.

## Atributos

- **id**: Identificador único do produto (Chave Primária).
  - Tipo: `Integer`
- **name**: Nome do produto.
  - Tipo: `String`
- **description**: Descrição do produto.
  - Tipo: `String`
- **price**: Preço do produto.
  - Tipo: `Double`
- **link**: Link para o produto.
  - Tipo: `String`
- **categories**: Conjunto de categorias associadas ao produto.
  - Tipo: `Set<Category>`
- **items**: Conjunto de itens de pedido associados ao produto.
  - Tipo: `Set<OrderItem>`

## Construtores

- **Product()**: Construtor padrão.
- **Product(Integer id, String name, String description, String link, Double price)**: Construtor com parâmetros para inicialização.
- **Product(ProductRequestPayload payload)**: Construtor usando um payload para criar um produto.

## Métodos

- **getId()**: Retorna o ID do produto.
- **setId(Integer id)**: Define o ID do produto.
- **getName()**: Retorna o nome do produto.
- **setName(String name)**: Define o nome do produto.
- **getDescription()**: Retorna a descrição do produto.
- **setDescription(String description)**: Define a descrição do produto.
- **getPrice()**: Retorna o preço do produto.
- **setPrice(Double price)**: Define o preço do produto.
- **getLink()**: Retorna o link do produto.
- **setLink(String link)**: Define o link do produto.
- **getCategories()**: Retorna o conjunto de categorias associadas ao produto.
- **getOrders()**: Retorna um conjunto de pedidos associados ao produto, excluindo o acesso direto.
- **equals(Object o)**: Compara este produto com outro para verificar se são iguais.
- **hashCode()**: Gera um código hash para o produto com base no ID.

## Relacionamentos

- **@ManyToMany**: Relacionamento muitos-para-muitos com a entidade `Category`.
- **@OneToMany(mappedBy = "id.product")**: Relacionamento um-para-muitos com a entidade `OrderItem`.
- **@JsonIgnore**: Ignora a propriedade `orders` na serialização JSON para evitar loops infinitos.

# Classe ProductService

A classe `ProductService` fornece serviços relacionados à entidade `Product` e está responsável por gerenciar as operações de produto, como criação, busca, e manipulação de categorias associadas.

## Anotações

- **@Service**: Indica que esta classe é um serviço Spring, responsável por lógica de negócios.

## Dependências

- **ProductRepository**: Repositório para acessar operações de banco de dados da entidade `Product`.
- **CategoryRepository**: Repositório para acessar operações de banco de dados da entidade `Category`.

## Métodos

- **findAll()**: 
  - **Descrição**: Retorna uma lista de todos os produtos.
  - **Retorno**: `List<Product>`

- **findById(Integer id)**: 
  - **Descrição**: Encontra um produto pelo ID.
  - **Parâmetro**: `id` (ID do produto)
  - **Retorno**: `Product`
  - **Exceção**: Lança `ResourcerNotFound` se o produto não for encontrado.

- **createdProduct(ProductRequestPayload payload)**: 
  - **Descrição**: Cria um novo produto com base no payload fornecido.
  - **Parâmetro**: `payload` (Dados do produto)
  - **Retorno**: `Product`

- **addCategory(Integer idProduct, Integer idCategory)**: 
  - **Descrição**: Adiciona uma categoria a um produto existente.
  - **Parâmetros**:
    - `idProduct` (ID do produto)
    - `idCategory` (ID da categoria)
  - **Retorno**: `Product`
  - **Exceção**: Lança `ResourcerNotFound` se o produto ou a categoria não forem encontrados.

- **deleteCategoryToProduct(Integer idProduct, Integer idCategory)**: 
  - **Descrição**: Remove uma categoria de um produto existente.
  - **Parâmetros**:
    - `idProduct` (ID do produto)
    - `idCategory` (ID da categoria)
  - **Retorno**: `void`
  - **Exceção**: Lança `ResourcerNotFound` se o produto ou a categoria não forem encontrados. Lança `ResourcerNotFound` se a categoria não for encontrada na lista de categorias do produto.


# Classe ProductController

A classe `ProductController` expõe os endpoints da API REST para gerenciar produtos e suas categorias.

## Anotações

- **@RestController**: Indica que esta classe é um controlador REST.
- **@RequestMapping**: Define a URL base para os endpoints desta classe (`/products`).

## Dependências

- **ProductService**: Serviço que fornece lógica de negócios para a entidade `Product`.

## Endpoints

### `GET /products`

- **Descrição**: Obtém a lista de todos os produtos.
- **Resposta**: `200 OK` com uma lista de produtos.

### `GET /products/{id}`

- **Descrição**: Obtém um produto pelo ID.
- **Parâmetro**: 
  - `id` (ID do produto)
- **Resposta**: `200 OK` com o produto correspondente.
- **Exceção**: Lança `404 Not Found` se o produto não for encontrado.

### `POST /products`

- **Descrição**: Cria um novo produto.
- **Corpo da Requisição**: `ProductRequestPayload` com os dados do novo produto.
- **Resposta**: `201 Created` com o produto criado e a URI do novo recurso.
- **Exemplo de URI**: `/products/{id}`

### `POST /products/{id}`

- **Descrição**: Adiciona uma categoria a um produto existente.
- **Parâmetros**: 
  - `id` (ID do produto)
  - **Corpo da Requisição**: `CategoryRequestPayLoad` com o ID da categoria a ser adicionada.
- **Resposta**: `200 OK` com o produto atualizado.
- **Exceção**: Lança `404 Not Found` se o produto ou a categoria não forem encontrados.

### `DELETE /products/{idProduct}/category/{idCategory}`

- **Descrição**: Remove uma categoria de um produto existente.
- **Parâmetros**: 
  - `idProduct` (ID do produto)
  - `idCategory` (ID da categoria a ser removida)
- **Resposta**: `204 No Content` após a exclusão da categoria.
- **Exceção**: Lança `404 Not Found` se o produto ou a categoria não forem encontrados.


# Classe OrderItem

A classe `OrderItem` representa um item de um pedido, contendo informações sobre o pedido, o produto, a quantidade e o preço.

## Anotações

- **@Entity**: Indica que esta classe é uma entidade JPA.
- **@Table**: Define a tabela associada (`tb_order_item`).
- **@EmbeddedId**: Define a chave primária composta usando a classe `OrderItemPK`.

## Atributos

- **OrderItemPK id**: Chave primária composta contendo `Order` e `Product`.
- **Integer quantity**: Quantidade do produto no pedido.
- **Double price**: Preço do produto.

## Construtores

- **OrderItem()**: Construtor padrão.
- **OrderItem(Order order, Product product, Integer quantity, Double price)**: Construtor para criar um item de pedido com valores especificados.

## Métodos

- **getQuantity()**: Retorna a quantidade do produto.
- **setQuantity(Integer quantity)**: Define a quantidade do produto.
- **getPrice()**: Retorna o preço do produto.
- **setPrice(Double price)**: Define o preço do produto.
- **getOrder()**: Retorna o pedido associado.
- **setOrder(Order order)**: Define o pedido associado.
- **getProduct()**: Retorna o produto associado.
- **setProduct(Product product)**: Define o produto associado.
- **getSubTotal()**: Calcula o subtotal do item (quantidade * preço).
- **equals(Object o)**: Compara este item de pedido com outro para verificar igualdade.
- **hashCode()**: Retorna o código hash baseado na chave primária composta.

# Classe OrderItemPK

A classe `OrderItemPK` é uma chave primária composta para a entidade `OrderItem`, contendo referências ao pedido e ao produto.

## Anotações

- **@Embeddable**: Indica que esta classe pode ser embutida em outra entidade como uma chave primária composta.

## Atributos

- **Order order**: Pedido associado à chave primária.
- **Product product**: Produto associado à chave primária.

## Métodos

- **getOrder()**: Retorna o pedido associado.
- **setOrder(Order order)**: Define o pedido associado.
- **getProduct()**: Retorna o produto associado.
- **setProduct(Product product)**: Define o produto associado.
- **equals(Object o)**: Compara esta chave primária composta com outra para verificar igualdade.
- **hashCode()**: Retorna o código hash baseado no pedido e no produto.


# Classe OrderStatus

A classe `OrderStatus` é um enum que representa os diferentes estados possíveis de um pedido.

## Valores do Enum

- **WAITING_PAYMENT (1)**: O pedido está aguardando pagamento.
- **PAID (2)**: O pedido foi pago.
- **SHIPPED (3)**: O pedido foi enviado.
- **DELIVERED (4)**: O pedido foi entregue.
- **CANCELED (5)**: O pedido foi cancelado.

## Atributos

- **int code**: Código associado ao status do pedido.

## Construtor

- **OrderStatus(int code)**: Construtor privado para definir o código associado ao status do pedido.

## Métodos

- **getCode()**: Retorna o código associado ao status do pedido.
- **valueOf(int code)**: Retorna o `OrderStatus` correspondente ao código fornecido. Lança uma `IllegalStateException` se o código for inválido.

# Classe StandardError

A classe `StandardError` representa a estrutura de um erro padrão para as respostas de erro da API.

## Atributos

- **LocalDateTime timestamp**: Data e hora em que o erro ocorreu.
- **Integer status**: Código HTTP do status do erro.
- **String error**: Mensagem de erro geral.
- **String message**: Mensagem detalhada do erro.
- **String path**: Caminho da solicitação que causou o erro.

## Construtor

- **StandardError()**: Construtor padrão.
- **StandardError(LocalDateTime timestamp, Integer status, String error, String message, String path)**: Construtor com parâmetros para inicializar todos os atributos.

## Métodos

- **getTimestamp()**: Retorna a data e hora em que o erro ocorreu.
- **setTimestamp(LocalDateTime timestamp)**: Define a data e hora do erro.
- **getStatus()**: Retorna o código HTTP do status do erro.
- **setStatus(Integer status)**: Define o código HTTP do status do erro.
- **getError()**: Retorna a mensagem de erro geral.
- **setError(String error)**: Define a mensagem de erro geral.
- **getMessage()**: Retorna a mensagem detalhada do erro.
- **setMessage(String message)**: Define a mensagem detalhada do erro.
- **getPath()**: Retorna o caminho da solicitação que causou o erro.
- **setPath(String path)**: Define o caminho da solicitação que causou o erro.

# Classe ControllerExceptionHandler

A classe `ControllerExceptionHandler` é usada para capturar e tratar exceções lançadas pelos controladores da aplicação.

## Métodos

- **NoSuchElementException(ResourcerNotFound e, HttpServletRequest request)**: Captura exceções do tipo `ResourcerNotFound`. Retorna uma resposta com status `404 Not Found` e uma mensagem padrão para erros de recurso não encontrado.

- **PropertyValueException(PropertyNull e, HttpServletRequest request)**: Captura exceções do tipo `PropertyNull`. Retorna uma resposta com status `409 Conflict` e uma mensagem padrão para erros de campo nulo.

Cada método constrói um objeto `StandardError` com informações sobre o erro e o retorna na resposta HTTP.




