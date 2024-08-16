package BanMyPham.BUS;

import BanMyPham.DTO.ModelMessage;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ServiceMail {

    public ModelMessage sendMain(String toEmail, String code) {
        ModelMessage ms = new ModelMessage(false, "");
        String from = "BanMyPham"; //Hiển thị ở phần from của email

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");//TLS 587, SSL 465
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        String username = "nanoheheblue@gmail.com";
        String password = "fqyn enyg pzqu yaky";    //  Your email password here
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            //Kiểu nội dung
//            message.addHeader("Content-Type", "text/HTML; charset=UTF-8");

            //Người gửi
            String fromName = "BanMyPham";
            String fromEmail = "nanoheheblue@gmail.com";
            try {
                message.setFrom(new InternetAddress(fromEmail, fromName));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ServiceMail.class.getName()).log(Level.SEVERE, null, ex);
            }

//            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Verify Code");

            //Quy định nhận phản hồi
//            message.setReplyTo(InternetAddress.parse(from,false));

            //Nội dung
            message.setText(code);
            //<editor-fold defaultstate="collapsed" desc="Sử dụng tiếng Việt trong email">
//            String content = "Đây là nội dung: " + code;
//            message.setContent(content, "text/plain; charset=UTF-8");
            //</editor-fold>

            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            e.printStackTrace(); // In ra thông báo lỗi chi tiết
            if (e.getMessage().equals("Invalid Addresses")) {
                ms.setMessage("Invalid email");
            } else {
                ms.setMessage("Error: " + e.getMessage()); // Thêm thông báo lỗi vào đây
            }
        }
        return ms;
    }
}
