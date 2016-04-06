# SistemaLavaCar
**autores**: Rodrigo Alves Guerra, Gabriel Eugenio Brito, Caio Ormond Araujo

## Resumo

A disciplina de Técnicas de Programação exige o desenvolvimento de um software, no formato de um sistema de informação, para fins de aprendizado de técnicas de programação orientada a objetos em Java/C++, incluindo uma introdução para técnicas de engenharia de software. Para tal, neste trabalho, escolheu-se o desenvolvimento de um sistema de um lavacar, onde o administrador pode gerenciar os seus clientes, os funcionários, e os veículos que frequentam a empresa. O programa também faz uma estimativa de tempo de espera para que o cliente saiba quanto tempo falta para seu carro ser lavado. O administrador pode gerenciar pelo sistema os insumos que são gastos nas execuções dos serviços, podendo prever falta de produtos e reabastecer seu estoque.

### Palavras-chave: Proposta de projeto, Orientação a objeto, Lavacar, Automação de relatórios.

## INTRODUÇÃO

Este relatório tem como fim propor um tema para o projeto a ser executado na disciplina de Técnicas de Programação, ministrada pelo Prof. Robson Ribeiro Linhares.
O tema escolhido consiste na implementação de um sistema projetado para automatizar partes do gerenciamento de um lavacar, feito sob o paradigma de orientação a objeto (OO). 
O método a ser utilizado seria o ciclo clássico de Engenharia de Software de forma simplificada (i.e. definição dos requisitos, modelagem-projeto via diagramas em UML, implementação em uma linguagem orientada a objetos e testes pelo uso do software).
Nossa motivação para a escolha do tema vem do fato de um dos membros da equipe, Rodrigo, conhecer o dono de um lavacar e, durante suas conversas, compreender a necessidade de um software que gerencie o lugar. Começaremos explicando algumas funcionalidade do sistema.

## EXPLICAÇÃO DO SOFTWARE

O software proposto é uma simplificação de um sistema de gerenciamento de um lavacar. Seu objetivo é tornar mais fácil a supervisão e utilização das informações pertinentes ao dono da empresa.
O nome, RG e o telefone de cada cliente que passar pelo lavacar será cadastrado no sistema, junto com o modelo, placa e outras informações de seu veículo. Também serão armazenados o nome, salário atual e rendimento de cada funcionário.
Será possível estimar o tempo de espera para que o próximo veículo seja atendido, baseado no número de veículos à sua frente e o tempo médio de um serviço para veículos de cada modelo (caminhonete, moto, carro, etc). 
Também utilizando os dados relativos ao número e tipo dos veículos atendidos será feito uma estimativa dos insumos (esponjas, toalhas, sabão, etc) para auxiliar no controle de estoque. Com as informações relativas à saída de caixa (como salário de funcionários e despesas com materiais utilizados no serviço) e de entrada de caixa (como o valor cobrado pela lavacar para prestar os serviços), o sistema deve ser capaz de gerar relatórios diários, mensais e anuais contendo o total gasto, total de dinheiro bruto e o lucro da empresa. 
Ao final de um serviço prestado será gerado um diagnóstico do veículo em questão. Ele deverá conter sugestões de serviços futuros como, por  exemplo, polimento do veículo, e verificação do que é preciso fazer , por exemplo, trocar pneus ou trocar óleo.

