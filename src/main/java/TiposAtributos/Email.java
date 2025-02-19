package TiposAtributos;

import Exceptions.EmailException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {

    private String email;

    public Email(String email) throws EmailException {
        setEmail(email);
    }

    private boolean isEmailValido(String email) {
        String emailRegex =
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws EmailException {
        if(! isEmailValido(email))
            throw new EmailException();

        this.email = email;
    }

}
