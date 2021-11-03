package project.passwordencoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Encoder implements PasswordEncoder {
    private final BCryptPasswordEncoder encoder;

    public Encoder() {
        this.encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence charSequence) {
        return this.encoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return encoder.matches(charSequence, s);
    }
}
