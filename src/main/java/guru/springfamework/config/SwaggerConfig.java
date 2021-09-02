package guru.springfamework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

   @Bean
    public Docket api() {
       return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.any())
               .paths(PathSelectors.any())
               .build()
               .pathMapping("/")
               .apiInfo(metaData());
   }


    private ApiInfo metaData(){
       Contact contact = new Contact("Stanislav Holik", "https://github.com/sgolikRepo/sfg-pet-clinic","sgolik1985@gmail.com");

       return new ApiInfo(
               "Spring Framework",
               "Spring Framework 5",
               "1.0",
               "Term of Service: blah",
               contact,
               "Apach License Version 2.0",
               "https://www.apache.org/licenses/LECENSE-2.0",
               new ArrayList());
    }
}

