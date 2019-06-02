package br.com.murilo.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.murilo.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configure) {

		/* Via extensao -> URI.json */
		/*
		 * configure.favorParameter(false).ignoreAcceptHeader(false).defaultContentType(
		 * MediaType.APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
		 */

		/* Via query parameter URI?mediaType=xml */
		/*
		 * configure.favorParameter(true).parameterName("mediaType").favorPathExtension(
		 * false).ignoreAcceptHeader(true)
		 * .useRegisteredExtensionsOnly(false).defaultContentType(MediaType.
		 * APPLICATION_JSON) .mediaType("json",
		 * MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML);
		 */

		/* Via header */
		configure.favorParameter(false).favorPathExtension(false).ignoreAcceptHeader(false)
				.useRegisteredExtensionsOnly(false).defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON).mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE_YML);
	}
}
