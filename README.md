# java-microsservicos-docker

Neste projeto existe 3 micro serviços desenvolvidos em java com utilização de spring boot, os micro serviços se chamam product-api, user-api, shopping-api
todos conectam ao database dev e com ajuda do framework cria as tabelas necessarias para utilização do serviço.<br>
A utilização do docker neste projeto é tanto para a criação da imagem postgres com utilização deste comando:<br>
docker	run	-d	-p	5432:5432	-e	POSTGRES_PASSWORD=postgres	postgres<br>
Como para agrupar os serviços e possibilitar o seu gerenciamento, apos executado com o maven o comandos para cada serviço:<br>
mvn	clean	install <br>
mvn	dockerfile:build <br>
<br>
Alguns arquivos de configuração são necessarios, mas todos eles estão presentes no projeto.

