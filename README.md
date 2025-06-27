

### Diferença entre os métodos executeQuery e executeUpdate no JDBC
- `executeQuery` é usado para executar instruções SQL do tipo SELECT, ou seja, consultas que retornam dados. Ele retorna um objeto do tipo ResultSet, que contém os dados resultantes da consulta. É indicado para operações que recuperam informações do banco de dados.
- `executeUpdate` é utilizado para executar instruções SQL que modificam o banco de dados, como INSERT, UPDATE e DELETE. Esse método retorna um valor inteiro que indica o número de linhas afetadas pela operação. Não retorna dados, apenas o impacto da alteração.

## Como lidar com ResultSet

### O que é ResultSet?
- É um objeto que mantém um cursor para navegar pelos registros retornados pela consulta. 
- Inicialmente, o cursor está antes do primeiro registro, ou seja, não aponta para nenhum dado. 
- Permite acessar os dados linha a linha, coluna a coluna.

### Como navegar e acessar dados no ResultSet?
- Use o método `next()` para avançar o cursor para o próximo registro. Ele retorna `true` se houver um registro válido, ou `false` se não houver mais registros.
- Além de next(), o ResultSet pode oferecer métodos para navegar para:
  - first() — primeira linha 
  - last() — última linha 
  - previous() — linha anterior absolute(int pos) — linha na posição específica
- Para acessar os dados da linha atual, utilize métodos como:
  - getString(int coluna) ou getString(String nomeColuna) para obter valores do tipo texto. 
  - getInt(int coluna), getDouble(int coluna), entre outros, para tipos numéricos.


```java
ResultSet rs = statement.executeQuery("SELECT * FROM tabela");
while (rs.next()) {
    String nome = rs.getString("nome");
    int idade = rs.getInt("idade");
    System.out.println(nome + " - " + idade);
}
rs.close();
```

## Como funciona o try-with-resources 
O try-with-resources foi introduzido no Java 7 como uma evolução importante para simplificar e tornar mais seguro o gerenciamento de recursos que precisam ser fechados.

Antes do Java 7, o padrão para garantir o fechamento correto de recursos era usar um bloco try com um bloco finally, onde o método close() do recurso era chamado manualmente.

O try-with-resources foi criado para resolver esses problemas, permitindo que recursos que implementam a interface AutoCloseable sejam declarados diretamente no cabeçalho do try. O compilador automaticamente gera o código para fechar esses recursos ao final do bloco, mesmo que ocorra uma exceção.

Características importantes:
- Variáveis "effectively final": No Java 7 e 8, as variáveis declaradas no try-with-resources devem ser novas declarações dentro do parênteses do try e não podem ser reutilizadas fora dele. A partir do Java 9, essa restrição foi relaxada, permitindo usar variáveis já declaradas desde que sejam efetivamente finais.
- Tratamento de exceções suprimidas: se uma exceção ocorrer no bloco try e outra ao fechar o recurso, a exceção principal é lançada e as demais ficam registradas como exceções suprimidas, facilitando o diagnóstico.
- Suporte a múltiplos recursos: é possível declarar vários recursos separados por ponto e vírgula dentro do parênteses do try.

Antes do Java 7
```java
BufferedReader br = null;
try {
    br = new BufferedReader(new FileReader("arquivo.txt"));
    // uso do br
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (br != null) {
        try {
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
```
Depois do Java 7
```java
try (BufferedReader br = new BufferedReader(new FileReader("arquivo.txt"))) {
    // uso do br
} catch (IOException e) {
    e.printStackTrace();
}
// br.close() é chamado automaticamente
```

Uso de conexões criadas manualmente (DriverManager.getConnection):
- A cada operação, o DAO abre uma nova conexão diretamente com o banco usando DriverManager.getConnection().

## HikariCP
O HikariCP é um dos pools de conexão JDBC mais rápidos e eficientes disponíveis atualmente, projetado para oferecer alta performance, baixa latência e confiabilidade no gerenciamento de conexões com bancos de dados. Ele é amplamente adotado em aplicações Java, especialmente em ambientes de produção e frameworks como Spring Boot, onde é o pool padrão desde a versão 2.x.

Vantagens do HikariCP em relação à criação manual de conexões:
- Performance e Baixa Latência: Ao invés de abrir e fechar uma conexão a cada requisição (processo custoso que envolve autenticação e handshake), o HikariCP mantém um conjunto de conexões pré-abertas e prontas para uso.
- Gerenciamento Eficiente de Recursos: O pool controla o número máximo e mínimo de conexões abertas (maximum-pool-size, minimum-idle), fecha conexões ociosas após um tempo configurado (idle-timeout) e renova conexões periodicamente (max-lifetime), evitando problemas de conexões inválidas ou "zumbis".
- Escalabilidade: Permite que múltiplas conexões sejam usadas simultaneamente, suportando alta concorrência sem sobrecarregar o banco.
- Robustez e Monitoramento: O HikariCP possui mecanismos para detectar vazamentos de conexão (com leakDetectionThreshold) e reconectar automaticamente conexões inválidas, além de fornecer métricas detalhadas para monitoramento.

Erro de logs HikariCP
```text
SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
```

O erro indica que o SLF4J não encontrou uma implementação concreta de logger no classpath. O SLF4J é apenas uma API de logging, e para funcionar corretamente precisa de um binding (implementação) como Logback ou Log4j.

Documentação logback ([link](https://logback.qos.ch/manual/configuration.html))
