# Nome do Projeto

## Finalidade do Projeto

Realizar busca pelo o nome da cidade para obter as informações do clima.
Projeto com a finlidade de colocar em prática assuntos que venho estudando e validar os conceitos
na prática. É uma forma de avaliar a minha evolução técnica.

## Tecnologias Principais

*   **Linguagem:** Kotlin
*   **Interface do Usuário:** XML com View Binding
*   **Arquitetura:** MVVM padrão recomendado pelo Google
*   **Gerenciamento de Dependências:** Hilt
*   **Rede:** Retrofit
*   **Outras bibliotecas importantes:** Coroutines, Navigation Component, Lottie Animation, Splash Screen

## Arquitetura Utilizada

O projeto segue o padrão de arquitetura Model-View-ViewModel (MVVM), que promove uma separação clara de responsabilidades:

*   **View (UI):** Responsável por exibir os dados na tela e capturar as interações do usuário. Implementada com Jetpack Compose (ou Activities/Fragments com XML).
*   **ViewModel:** Atua como intermediário entre a View e o Model. Contém a lógica de apresentação e expõe os dados para a View através de `LiveData`. É independente do ciclo de vida da UI.
*   **Model (Repository/DataSource):** Responsável por buscar e gerenciar os dados, seja de uma API remota, banco de dados local ou outra fonte. O Repository abstrai a origem dos dados para o ViewModel.

## Boas Práticas Contidas no Projeto

*   **Clean Architecture (se aplicável):** [Descreva como os princípios da Clean Architecture foram aplicados, como a separação de camadas e a regra de dependência.]
*   **Injeção de Dependência:** Utilização do Hilt para gerenciar as dependências e facilitar a testabilidade e manutenção do código.
*   **Programação Reativa:** Uso de Coroutines para lidar com operações assíncronas de forma eficiente e concisa.
*   **Gerenciamento de Estado da UI:** Utilização de `LiveData` para gerenciar o estado da UI de forma reativa e consistente.
*   **Tratamento de Erros:** Implementação de mecanismos robustos para tratamento de erros e feedback ao usuário.
*   **Código Limpo e Legível:** Adoção de convenções de código Kotlin, nomes significativos para variáveis e funções, e comentários quando necessário.
*   **Single Source of Truth (SSOT):** Os dados são gerenciados de forma a ter uma única fonte de verdade, geralmente no Repository ou em uma camada de dados.
*   **Princípios SOLID:** Aplicação dos princípios SOLID para criar um código mais flexível, manutenível e compreensível.
*   **Versionamento de Código:** Utilização de Git para controle de versão e colaboração.
