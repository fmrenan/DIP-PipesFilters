# Utiliza��o dos Filtros
Para utilizar a /ajuda nos demais filtros � necess�rio antes o filtro abrir
Ex: abrir lenna.jpg | borda /ajuda

Obs: A utiliza��o do filtro pixelada deve ser logo ap�s o abrir

Exemplos de utiliza��o:

1- Aplicando efeito de pixeliza��o, espelhamento, sepia e borda
Visualiza��o:
abrir lenna.jpg | pixelada -3 | espelhada | sepia | borda -v 3

Salvar
abrir lenna.jpg | pixelada 3 | espelhada | sepia | borda 3 | salvar -v exemplo1.jpg 

2- Aplica��o dos filtros nitidez, negativa e brilho
Visualiza��o:
abrir lenna.jpg | nitidez | negativa | brilho -v 2

Salvar:
abrir lenna.jpg | nitidez | negativa | brilho 2 | salvar -v exemplo2.jpg

3- Aplicando filtros de rota��o, limiar e nitidez 
Visualiza��o:
abrir lenna.jpg | rotaciona 90 | limiar 120 | nitidez -v

Salvar:
abrir lenna.jpg | rotaciona 90 | limiar 120 | nitidez | salvar -v exemplo3.jpg