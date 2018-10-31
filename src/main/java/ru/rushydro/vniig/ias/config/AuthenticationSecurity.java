package ru.rushydro.vniig.ias.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.rushydro.vniig.ias.service.UserDetailsServiceImpl;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yazik on 12.03.2017.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new PasswordEncoder() {


            @Override
            public String encode(CharSequence charSequence) {
                MessageDigest md = null;
                String myHash = null;
                try {
                    md = MessageDigest.getInstance("MD5");
                    md.update(charSequence.toString().getBytes());
                    byte[] digest = md.digest();
                    myHash = DatatypeConverter
                            .printHexBinary(digest);

                    return myHash;
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                MessageDigest md = null;
                String myHash = null;
                try {
                    md = MessageDigest.getInstance("MD5");
                    md.update(charSequence.toString().getBytes());
                    byte[] digest = md.digest();
                    myHash = DatatypeConverter
                            .printHexBinary(digest).toUpperCase();

                    return myHash.equalsIgnoreCase(s);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }


                return false;
            }
        };
    }
}
