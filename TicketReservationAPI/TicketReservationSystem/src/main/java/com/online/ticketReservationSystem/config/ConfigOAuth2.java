package com.online.ticketReservationSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableAuthorizationServer
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter{
	
	   private String clientId = "pixeltrice";
	   private String clientSecret = "pixeltrice-secret-key";
	   private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
			   "MIIEpAIBAAKCAQEAwNotK/MSDUhxJ1G6039FlElssrcx7l3l8U51p/o/MX896W2K\n" +
			   "P373DINrmJzi2ilupRGthyYpMdO6WlAlPLFy69f/702j7VwfG+ys2uWmfgYNZfPN\n" +
			   "Ofn6YsINkGWQU410ua7tT8j1Y+g277PwAVb+2n4u+hUvDh9gHQvtcQEWx4m51zeE\n" +
			   "i4iYmNzCGilry7JG7n6cY5/G8VlRbi7kUoX/WWJYE3j8m2AsSHuv9UUVf4JmMbOF\n" +
			   "WwJjUJaDB4kqB7n/6jRqvjmvEiiwWpvkuvK/MU8bOHO0RExQvjZCG1HI6Jf6eAyu\n" +
			   "f33GIX0EYrnvAsREzVGxG+2xFCZaHlCc38bLrwIDAQABAoIBADf73hshD/99JSQF\n" +
			   "7NdS3g8DFaP33G3XIlZfsXYXBltHFeD6pKR0JiGi0QxXlt9AIdeXfyl5oYHOdg2b\n" +
			   "sIOi+7qNVMXSYyEXmgxQM4nEGwYAmckb8lq/P7mFncxqP3CR4u24YznDG8Fp+vCw\n" +
			   "6p5o88jX0qlUlvlhkFQ+Typ753H6/0+ZnzA4IMXxwRZ9PjgEZs+xgyJ3qnbf7gu2\n" +
			   "Lb/3pNBYP6cnJq3bdYSEVPYhBgLrw2UecBakJ6SdeTrUMD7NyATl6+j8bJrg2Pfy\n" +
			   "MIWhRJ7JRyJX2Y/hFALh6AnrkThRMliaOwYp26mz/e6e6nbWkqHh2mqumzm5NGuW\n" +
			   "QXMeQ0kCgYEA3rofyOJy5A3yqhBC536/yLpQUKEaZ1xh4+uyN3eOMmdUNoYBhRIo\n" +
			   "i57TK21qqDFNLbqCNLCWxk67Ad8dISiyBk/6z93u48RQ0joA0tIamzzuH/4JAvuw\n" +
			   "O1tiOSSKTerm8RXb27W3sxg8HctIGRybBB8t9z63tRc58HpPw9u9m2UCgYEA3amI\n" +
			   "njwBoQfORDmOnOGS2aY+KbCIdKj/Rg/FktDpOw5TS3ndK2N1PMrzTLIUCyiIwEPR\n" +
			   "6M+3kYhLaBJnA7srrRUlbHDYYise52NKnEC92BbM9AVYuuUUpoy3w9s9eVnVHd3D\n" +
			   "PjBBjHt1hL6yo23S5jkXyVuyX2V1CvC07bgwO4MCgYEArY+gwSwll61+0iOF31uY\n" +
			   "G9WETLCeILmO1suOv0Z9Gq4Go85dVNNHoTWNHSRQnux8Vgwm9uQ9Oh+T/utcicBq\n" +
			   "qhydOGqbhAYxbQEiLybm8N3UOj39y8NSG48kxZ7B3+ITQP9lqpq6oedawWTttBPk\n" +
			   "gJvDeA35njaRQeIuUlPkNzkCgYEAn+taJ17/heANzmcAHusXeXNY0dcM+62ZTkC+\n" +
			   "uCeEvRieu4tWJ6F/ybZaRfEMSOibkKrdXGvP8m00igcMz0JQRnVFJLFdS6X0i9e0\n" +
			   "0npa/PWmND9PPrdbaLWJNwKLnWtUOFHwNIO4JtRDetviIcpwJ6z1Hk7Lp8uY36W9\n" +
			   "wuvp0GUCgYB8/YETGq5CUYj6AMexDwnY0BJKoSvFGPmvrD1ecoejXCOHF5RvRbfq\n" +
			   "a592deVq/QXj7V0BsycWEuR0b83Gu4gXzWvcXQAQGFKH0DYW0JuQr8gDeHws7eu+\n" +
			   "pRlCtY0Wm1ATbgm73QgZRQo2el5eW1v2SWbcHIwdF9Wj7fmvDSooMA==\n" +
			   "-----END RSA PRIVATE KEY-----";
	private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
			"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwNotK/MSDUhxJ1G6039F\n" +
			"lElssrcx7l3l8U51p/o/MX896W2KP373DINrmJzi2ilupRGthyYpMdO6WlAlPLFy\n" +
			"69f/702j7VwfG+ys2uWmfgYNZfPNOfn6YsINkGWQU410ua7tT8j1Y+g277PwAVb+\n" +
			"2n4u+hUvDh9gHQvtcQEWx4m51zeEi4iYmNzCGilry7JG7n6cY5/G8VlRbi7kUoX/\n" +
			"WWJYE3j8m2AsSHuv9UUVf4JmMbOFWwJjUJaDB4kqB7n/6jRqvjmvEiiwWpvkuvK/\n" +
			"MU8bOHO0RExQvjZCG1HI6Jf6eAyuf33GIX0EYrnvAsREzVGxG+2xFCZaHlCc38bL\n" +
			"rwIDAQAB\n" +
			"-----END PUBLIC KEY-----";
	   
	   @Autowired
	   @Qualifier("authenticationManagerBean")
	   private AuthenticationManager authenticationManager;
	   
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   
	   @Bean
	   public JwtAccessTokenConverter tokenEnhancer() {
	      JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
	      converter.setSigningKey(privateKey);
	      converter.setVerifierKey(publicKey);
	      return converter;
	   }
	   
	   @Bean
	   public JwtTokenStore tokenStore() {
	      return new JwtTokenStore(tokenEnhancer());
	   }
	   
	   @Override
	   public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	      endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
	      .accessTokenConverter(tokenEnhancer());
	   }
	   @Override
	   public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		   UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		   CorsConfiguration config = new CorsConfiguration();
		   config.applyPermitDefaultValues();

		   // Maybe there's a way to use config from AuthorizationServerEndpointsConfigurer endpoints?
		   source.registerCorsConfiguration("/oauth/token", config);
		   CorsFilter filter = new CorsFilter(source);
		   security.addTokenEndpointAuthenticationFilter(filter);
		   security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	   }
	   @Override
	   public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	      clients.inMemory().withClient(clientId).secret(passwordEncoder.encode(clientSecret)).scopes("read", "write")
	         .authorizedGrantTypes("password", "refresh_token").accessTokenValiditySeconds(20000)
	         .refreshTokenValiditySeconds(20000);

	   }

}
