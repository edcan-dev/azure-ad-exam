package com.okysoft.azureadexam.utils;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.http.GraphServiceException;
import com.microsoft.graph.models.Directory;
import com.microsoft.graph.models.PasswordProfile;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import com.okysoft.azureadexam.models.XlsxUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserCreationUtil {

    Logger logger = LoggerFactory.getLogger(UserCreationUtil.class);

    private final String CLIENT_SECRET = "Tis8Q~vH4NhLw8uUBgygk7eLhLuXroQy2EbKqb_0";
    private final String APP_CLIENT_ID = "db27ebad-44bb-43b5-b6fa-8eba4f9ab5b4";
    private final String APP_TENANT_ID = "2662a2bd-9c8e-4a7a-a8d3-44d6b39096ed";


    @Autowired
    private Environment env;

    private final String domainName = "@edicmexico.onmicrosoft.com";

    private ClientSecretCredential credential;

    TokenCredentialAuthProvider authProvider;

    GraphServiceClient graphClient;

    public UserCreationUtil() {

        credential = new ClientSecretCredentialBuilder()
                .tenantId(APP_TENANT_ID)
                .clientId(APP_CLIENT_ID)
                .clientSecret(CLIENT_SECRET)
                .build();

        authProvider = new TokenCredentialAuthProvider(List.of("https://graph.microsoft.com/.default"), credential);
        graphClient = GraphServiceClient.builder().authenticationProvider( authProvider ).buildClient();
    }

    public boolean existByUserName(String userName) {
        User user;
        try {
            user = graphClient.users(userName).buildRequest().get();
        } catch (Exception e) {
            return false;
        }
        //assert user != null;
        //System.out.println(user.userPrincipalName);
        return true;
    }

    public  void createUser(XlsxUser xlsxUser) {

        User user = new User();
        user.accountEnabled = true; // Cuenta habilitada
        user.displayName = xlsxUser.getFirstName().concat(" ").concat(xlsxUser.getLastName()); // Nombre completo = Nombre Apellido
        user.surname = xlsxUser.getLastName(); // Apellido
        user.givenName = xlsxUser.getFirstName(); // Nombre
        user.mailNickname = xlsxUser.getFirstName().concat(Character.toString(xlsxUser.getLastName().charAt(0))); // Nombre de Mail =  NombreA
        //user.city = xlsxUser.getCity(); // Lugar de residencia
        //user.jobTitle = xlsxUser.getJobTitle(); // Ocupación

        String generatedUserName = (xlsxUser.getFirstName().concat(xlsxUser.getLastName())).toLowerCase().concat(domainName);
        user.userPrincipalName = generatedUserName; // nombre de usuario = nombreapellido@domain.com
        xlsxUser.setUserName(generatedUserName);

        // user.mail=xlsxUser.getEmail();

        PasswordProfile passwordProfile = new PasswordProfile(); // Políticas de password
        passwordProfile.forceChangePasswordNextSignIn = false;
        passwordProfile.password = xlsxUser.getPassword();

        user.passwordProfile = passwordProfile;

        if(! existByUserName(user.userPrincipalName)) {
            graphClient.users().buildRequest().postAsync(user);

            xlsxUser.setActiveDirectoryUserId(user.id);
            logger.info("===== User: ".concat(xlsxUser.getUserName()).concat(" || ".concat(xlsxUser.getPassword()).concat("=====")));
        }

    }
}
