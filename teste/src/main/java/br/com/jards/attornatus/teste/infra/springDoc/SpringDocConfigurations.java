package br.com.jards.attornatus.teste.infra.springDoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()

                .info(new Info()
                        .title("Gerenciar Pessoas - API")
                        .description(
                                "Teste Técnico Beck-End: API Rest da aplicação Gerenciar Pessoas, contendo as funcionalidades de CRUD de Pessoas e de Endereço.")
                        .contact(new Contact()
                                .name("o Desenvolvedor")
                                .email("jardsguimaraes@hotmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licenca")));
    }

}
