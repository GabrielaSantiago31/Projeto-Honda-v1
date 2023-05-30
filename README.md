# projeto Honda v1
Sistema desenvolvido para realizar o cadastro de funcionários e produtos. 
O sistema ainda efetua a venda dos produtos, gerando um registro, no qual constam as informações das motos que foram vendidas, qual funcionário efetuou a venda, o preço com desconto ou não e também a data.

<p>
<img src="https://img.shields.io/badge/STATUS-COMPLETO-green"/>
</p>

### :arrow_forward: Abrir e rodar o projeto
---
Após baixar o projeto, você pode abri-lo com o Eclipse IDE 2022-12. Para isso, na tela de launcher:
- Clique com o botão esquerdo do mouse em File -> Import -> Existing Pojects into Workspace -> Clique no botão Browse... 
-> Selecione o projeto -> Selecione a opção finish.
- Para rodar as telas, será necessário o plug-in WindowBuilder para Eclipse, o qual pode ser instalado através da própria IDE: 
Vá até a opção Help -> Selecione Eclipse Marketplace -> Digite Window Builder no Campo de busca e selecione a opção Window Builder.

### :hammer_and_pick: Funcionalidade do Projeto 
---
- <kbd>Cadastro de motos:</kbd> para cadastrar, o usuário precisará digitar a marca, o modelo, o preço, a cor e, caso haja, alguma observação no campo disponível. 
Deverá selecionar a cilindrada e o ano dentre as opções apresentadas e clicar em "cadastrar". 
Ainda na tela de cadastro, é possivel ver a lista de motos cadastradas e as opções "alterar" e "excluir".
Caso o usuário do sistema deseje alterar alguma informação, deverá clicar no produto desejado que estará sendo exibido na lista e, em seguida, em "alterar". Os campos para edição das informações
estarão abertos e, após as modificações, o usuário deverá clicar no botão "salvar".
Para excluir um produto, o usuário do sistema deverá clicar em uma das motos da lista e, em seguida, na opção "excluir".

- <kbd>Cadastro de vendedores:</kbd> para cadastrar, alterar e excluir um vendedor, as instruções a serem seguidas são as mesmas do cadastro de motos, pois somente os dados a serem preenchidos são diferentes.

- <kbd>Registro de vendas:</kbd> Para realizar a venda, o usuário do sistema deverá selecionar uma das opções de motos apresentadas, assim como uma das opções de vendedores cadastrados no sistema. Assim que a moto for selecionada, o valor da mesma
já será apresentado na tela, sendo assim necessário que seja informado a quantidade no campo indicado para que o próprio sistema mostre e compute o valor total da compra. Também deverá ser selecionada uma das opções oferecidas para a forma
de pagamento. Com todos os dados preenchidos adequadamente, será necessário apenas um clique no botão "finalizar" para que seja realizado o registro da venda.

###  :octocat: Técnicas e Tecnologias Utilizadas
---
- Java 17
- MySQL Workbench
- Java Swing
- Eclipse IDE
- POO
