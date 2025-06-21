package cl.duoc.mcsv_pedidos.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("MCSV Pedidos API by BLDR Developers")
                        .version("1.0.0")
                        .description("API for managing MCSV Pedidos")
                        .contact(new Contact()
                                .name("Benjamín Castro")
                                .email("ben.castroo@duocuc.cl")
                                .url("https://github.com/BDLR-Developers/mcsv-pedidos.git"))
                        .summary("API de ejercicio para la asignatura FullStack I de DuocUC Viña del Mar"));
    }
}
