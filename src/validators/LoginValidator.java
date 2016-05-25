package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("validators.LoginValidator")
public class LoginValidator implements Validator{

    private static final String LOGIN_PATTERN = "[a-zA-Z0-9-._]";

    private Pattern pattern;
    private Matcher matcher;

    public LoginValidator(){
        pattern = Pattern.compile(LOGIN_PATTERN);
    }

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        matcher = pattern.matcher(value.toString());
        if(!matcher.matches()){

            FacesMessage msg =
                    new FacesMessage("Login validation failed.",
                            "Invalid login format.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);

        }

    }
}