package com.okysoft.azureadexam.controllers;

import com.okysoft.azureadexam.models.XlsxUser;
import com.okysoft.azureadexam.services.EmailServiceImpl;
import com.okysoft.azureadexam.services.UserCreationServiceImpl;
import com.okysoft.azureadexam.services.XlsxServiceImpl;
import com.okysoft.azureadexam.utils.UserCreationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.util.List;

@RestController
public class AppController {

    Logger logger = LoggerFactory.getLogger(AppController.class);

    @Autowired
    UserCreationServiceImpl userCreationService;

    @Autowired
    XlsxServiceImpl xlsxService;



    @Autowired
    EmailServiceImpl emailService;

    List<XlsxUser> usersFromSheet;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public ResponseEntity<String> readExcel() throws MalformedURLException {

        usersFromSheet = xlsxService.getUserFromSheet();
        logger.info("========== EXCEL LEÍDO ==========");

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/create/accounts",method = RequestMethod.POST)
    public ResponseEntity<String> graphMeApi() throws MalformedURLException {

        for (XlsxUser user : usersFromSheet) {
            userCreationService.createUser(user);
        }
        logger.info("========== CUENTA CREADAS ==========");

        return new ResponseEntity<>("OK", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/send/emails",method = RequestMethod.POST)
    public ResponseEntity<String> sendMail()  {

        String subject = "Invitación de Evaluación | EDIC";

        for(XlsxUser user : usersFromSheet) {
            emailService.sendSimpleMessage(user.getEmail(), subject, "¡Excelente día!\n" +
                    "\n" +
                    "Es un gusto saludarle por este medio, al tiempo que aprovechamos para compartir las indicaciones para que presente su evaluación psicométrica.\n" +
                    "\n" +
                    "Día: Martes 2 de mayo  \n" +
                    "Horario: 10:00 a 12:00 hrs\n" +
                    "Lugar: En línea - requiere de una computadora con buena conexión a internet.\n" +
                    "\n" +
                    "Importante: Su evaluación es personal, debe responder sólo usted ya que no hay respuestas correctas o incorrectas, es decir, cualquier ayuda podría llegar a representar algo negativo o engañoso en su perfil. Le sugerimos hacerlo en un lugar donde pueda estar 100% dedicado a realizarlo, sin distracciones y lo más tranquilo posible.\n" +
                    "\n" +
                    "Indicaciones:\n" +
                    "1. Con posterioridad le llegará a usted un correo indicando su usuario, contraseña y liga de acceso para llevar a cabo su evaluación. \n" +
                    "2. La liga sólo estará activa de 10:00 a 12:00 hrs, no podrá entrar antes ni después de ese horario\n" +
                    "3. Es importante contestar con lo primero que le venga a la mente y sin sobre-pensar las respuestas.\n" +
                    "4. Al finalizar toda su evaluación, asegúrese de dar clic en el botón de “Enviar (Submit)”, de lo contrario sus respuestas no serán enviadas.\n" +
                    "5. Tomar en cuenta: Enviar sus respuestas antes de las 12:00 hrs. La evaluación sólo estará activa de 10:00 a 12:00 hrs. Si no envía sus respuestas antes de las 12:00 hrs todo lo que haya respondido se perderá.\n" +
                    "\n" +
                    "Estaremos muy pendientes de su proceso y totalmente a sus órdenes, si desea contactarnos por cualquier duda de su evaluación, estaremos en la siguiente liga de Zoom:\n" +
                    "\n" +
                    "Tema: Atención a dudas - Evaluación Psicométrica para Mediadores\n" +
                    "Hora: 2 mayo 2023 10:00 a. m. Ciudad de México\n" +
                    "Unirse a la reunión Zoom\n" +
                    "https://zoom.us/j/95423430378\n" +
                    "ID de reunión: 954 2343 0378 \n" +
                    "\n" +
                    "\n" +
                    "Lo único que tiene que hacer es dar clic a la liga de Zoom y nos encontrará en la sala para poder apoyar cualquier situación que se presente. \n" +
                    "\n" +
                    "Sólo estaremos en esta liga el martes 2 de mayo de 10:00 a 12:00 hrs.\n" +
                    "\n" +
                    "Nota: No es necesario entrar a la liga de Zoom, sólo debe entrar si tienen alguna duda.\n" +
                    "\n" +
                    "¡Mucho ánimo en su evaluación!");
        }
        logger.info("========== MAILS ENVIADOS ==========");

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
