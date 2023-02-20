package com.example.musica;

import com.example.musica.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.MultipartConfigElement;

@EnableWebMvc
@EnableConfigurationProperties()
@SpringBootApplication
@CrossOrigin(origins = "*")
public class MusicaApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MusicaApplication.class, args);
	}

	@Bean
	public InternalResourceViewResolver defaultViewResolver() {
	  return new InternalResourceViewResolver();
	}
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(DataSize.ofMegabytes(100L));
		factory.setMaxRequestSize(DataSize.ofMegabytes(100L));
		return factory.createMultipartConfig();
	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.init();
		};
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		exposeDirectory("uploads2", registry);
    }

	public static Path getUploadPath() {
		return Paths.get("uploads2");
	}

	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        String uploadPath = getUploadPath().toFile().getAbsolutePath();
         
        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");
         
        registry.addResourceHandler("/**").addResourceLocations("file:" + uploadPath + "/");
    }

	@Bean
    public WebMvcConfigurer corsConfigurer()
    {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*");
            }
        };
    }

	@Bean
	CharacterEncodingFilter characterEncodingFilter() {
		CharacterEncodingFilter filter = new CharacterEncodingFilter();
		filter.setEncoding("UTF-8");
		filter.setForceEncoding(true);
		return filter;
	}
}
