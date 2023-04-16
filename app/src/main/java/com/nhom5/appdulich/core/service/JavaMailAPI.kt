package com.nhom5.appdulich.core.service

import com.nhom5.appdulich.utils.Const
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

object JavaMailAPI {
    suspend fun sendMail(
        email: String,
        subject: String,
        message: String,
    ) {
        val session: Session

        //Creating properties
        val props = Properties()

        //If you are not using gmail you may need to change the values
        props["mail.smtp.host"] = "smtp.gmail.com"
        props["mail.smtp.socketFactory.port"] = "465"
        props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.port"] = "465"

        //Creating a new session
        session = Session.getDefaultInstance(props,
            object : Authenticator() {
                //Authenticating the password
                override fun getPasswordAuthentication(): PasswordAuthentication {
                    return PasswordAuthentication(Const.EMAIL, Const.PASS)
                }
            })

        //Creating MimeMessage object
        val mm = MimeMessage(session)

        //Setting sender address
        mm.setFrom(InternetAddress(Const.EMAIL))
        //Adding receiver
        mm.addRecipient(Message.RecipientType.TO, InternetAddress(email))
        //Adding subject
        mm.subject = subject
        //Adding message
        mm.setText(message)
        //Sending email
        Transport.send(mm)
    }
}