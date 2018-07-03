Exemplo Seam Booking com Arquillian
=================

Esse exemplo demonstra uma estratégia de utilizar testes de integração usando arquillian e jboss seam em um módulo maven separado.

Configurações utilizadas
-------------------

- [arquillian](http://arquillian.org/) - aproveita JUnit para executar casos de teste dentro de um container Java (por exemplo, JBOSS EAP).
- [arquillian-suite-extension](https://github.com/ingwarsw/arquillian-suite-extension) - extensão para aproveitar a mesma configuração de deployment para rodar a suite de testes. (por exemplo, todos os testes rodarem no mesmo ear implantado no container)
- [arquillian-extension-seam2](https://github.com/arquillian/arquillian-extension-seam2) - extensão para utilizar injeções seam nos testes de integração
- [maven failsafe plugin](https://maven.apache.org/surefire/maven-failsafe-plugin/) - plugin do maven para executar os testes na fase de integration-test do maven

1. Publicar servidor de aplicação no repositório maven local para ser utilizando na execução dos testes:

        mvn install:install-file -Dfile=jboss-eap-6.3.zip -DgroupId=br.gov.ce.fortaleza.sefin -DartifactId=jboss-eap-as-dist -Dversion=6.3 -Dpackaging=zip


2. Exemplo de como executar testes no modo gerenciado do arquillian:

        mvn verify -Darquillian=jboss-sefin-managed

3. Exemplo de como executar testes no modo remoto do arquillian:

         mvn verify -Darquillian=jboss-sefin-remote