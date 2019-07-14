# Utilização dos Filtros
Para utilizar a /ajuda nos demais filtros é necessário antes o filtro abrir
Ex: abrir lenna.jpg | borda /ajuda

Obs: A utilização do filtro pixelada deve ser logo após o abrir

Exemplos de utilização:

1- Aplicando efeito de pixelização, espelhamento, sepia e borda
Visualização:
abrir lenna.jpg | pixelada -3 | espelhada | sepia | borda -v 3

Salvar
abrir lenna.jpg | pixelada 3 | espelhada | sepia | borda 3 | salvar -v exemplo1.jpg 

2- Aplicação dos filtros nitidez, negativa e brilho
Visualização:
abrir lenna.jpg | nitidez | negativa | brilho -v 2

Salvar:
abrir lenna.jpg | nitidez | negativa | brilho 2 | salvar -v exemplo2.jpg

3- Aplicando filtros de rotação, limiar e nitidez 
Visualização:
abrir lenna.jpg | rotaciona 90 | limiar 120 | nitidez -v

Salvar:
abrir lenna.jpg | rotaciona 90 | limiar 120 | nitidez | salvar -v exemplo3.jpg