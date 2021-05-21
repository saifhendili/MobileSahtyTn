package com.esprit.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources; 
import com.esprit.services.ServiceUtilisateur;


import com.sun.mail.smtp.SMTPTransport;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Account activation UI
 *
 * @author manel
 */
public class ActivateForm extends BaseForm {

    TextField email;
    public ActivateForm(Resources res) {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("IMGLogin");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("Activate");
        
        add(BorderLayout.NORTH, 
                BoxLayout.encloseY(
                        new Label(res.getImage("oublier.png"), "LogoLabel"),
                        new Label("Awsome Thanks!", "LogoLabel")
                )
        );
        
        
        
         email = new TextField("","enter your email",20,TextField.ANY);
        email.setSingleLineTextArea(false);
        
        Button valider = new Button("Ok");
        Label haveAnAcount = new Label("Back to log in?");
        Button signIn = new Button("Renew your password");
        signIn.addActionListener( e-> previous.showBack());
        signIn.setUIID("CenterLink");
        
        Container content = BoxLayout.encloseY(
        
                new FloatingHint(email),
                createLineSeparator(),
                valider,
                FlowLayout.encloseCenter(haveAnAcount,signIn)
        );
        
        content.setScrollableY(true);
        
        add(BorderLayout.CENTER,content);
        
        valider.requestFocus();
        
       valider.addActionListener(e -> {
            
           InfiniteProgress ip = new InfiniteProgress();
            
            final Dialog ipDialog =  ip.showInfiniteBlocking();
      
       
                       
           
           sendMail(res);
            ipDialog.dispose();
            Dialog.show("Mot de passe","Nous avons envoyé le mot de passe a votre e-mail. Veuillez vérifier votre boite de réception",new Command("OK"));
            new SignInForm(res).show();
            refreshTheme();
            
        });
        
        
        
    }
    
    //sendMail
    
    public void sendMail(Resources res) {
       try {
            
            Properties props = new Properties();
                props.put("mail.transport.protocol", "smtp"); //SMTP protocol
		props.put("mail.smtps.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtps.auth", "true"); //enable authentication

            Session session = Session.getInstance(props,null); 
            
            MimeMessage msg = new MimeMessage(session);
            
            msg.setFrom(new InternetAddress("manel.tabbeb@esprit.tn"));
            msg.setRecipients(Message.RecipientType.TO, email.getText().toString());
            msg.setSubject("ParaDaily  : Confirmation of ");
            msg.setSentDate(new Date(System.currentTimeMillis()));
            
           String mp = ServiceUtilisateur.getInstance().getPasswordByEmail(email.getText().toString(), res);
           String txt = "Welcome to ParaDaily : Your password is : "+mp+" ";
           
           
           msg.setText(txt);
           
         SMTPTransport  st = (SMTPTransport)session.getTransport("smtps") ;
            
          st.connect("smtp.gmail.com",465,"manel.tabbeb@esprit.tn","203JFT5462");
           
          st.sendMessage(msg, msg.getAllRecipients());
            
          System.out.println("server response : "+st.getLastServerResponse());
          
        }catch(Exception e ) {
            e.printStackTrace();
        }
         
                  
    }   
}
