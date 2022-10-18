package com.receiptmanagement.ReceiptManagement;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.DocumentException;
import com.receiptmanagement.ReceiptManagement.LOGIN.LOGIN;
import com.receiptmanagement.ReceiptManagement.LOGIN.LOGINService;
import com.receiptmanagement.ReceiptManagement.RECEIPT.RECEIPT;
import com.receiptmanagement.ReceiptManagement.RECEIPT.RECEIPTService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// @CrossOrigin(origins= {"*"}, maxAge = 2400, allowCredentials = "false" )
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ReceiptManagementController {
    
    @Autowired
    private RECEIPTService service;

    @Autowired
    private LOGINService loginService;

    @GetMapping("/login/{id}")
    public ResponseEntity<Optional<LOGIN>> login(@PathVariable String id){
        try{
            Optional<LOGIN> login = loginService.getById(id);
            return new ResponseEntity<Optional<LOGIN>>(login, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<Optional<LOGIN>>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/receipt/add")
    public ResponseEntity<String> create(@RequestBody RECEIPT receipt){
        try{
            service.add(receipt);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/receipt/show")
    public ResponseEntity<List<RECEIPT>> show(){
        try{
            List<RECEIPT> res = service.listAll();
            return new ResponseEntity<List<RECEIPT>>(res, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<List<RECEIPT>>(HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/receipt/name/{name}")
    public ResponseEntity<List<RECEIPT>> findByName(@PathVariable String name){
        try{
            List<RECEIPT> res = service.getByName(name);
            return new ResponseEntity<List<RECEIPT>>(res, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<List<RECEIPT>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/receipt/mob/{mob}")
    public ResponseEntity<List<RECEIPT>> findByMobile(@PathVariable String mob){
        try{
            List<RECEIPT> res = service.getByMobile(mob);
            return new ResponseEntity<List<RECEIPT>>(res, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<List<RECEIPT>>(HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/receipt/{id}")
    public ResponseEntity<RECEIPT> get(@PathVariable Long id){
        try{
            RECEIPT receipt = service.getById(id);
            return new ResponseEntity<RECEIPT>(receipt, HttpStatus.OK);
        } catch(NoSuchElementException e){
            return new ResponseEntity<RECEIPT>(HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin(origins = "http://localhost:3000")  
    @GetMapping(value = "/receipt/pdf/{id}")
    public ResponseEntity<String> exportToPdf(HttpServletResponse response, @PathVariable Long id) throws DocumentException, IOException{
        try{
            response.setContentType("application/pdf");
            RECEIPT curReceipt = service.getById(id);
                    
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            
            String headerKey = "Content-Disposition";
            String name = curReceipt.getName();
            String headerValue = "attachment; filename="+name+"_"+currentDateTime+".pdf";
            response.setHeader(headerKey, headerValue);
    
            ReceiptPdfExporter exporter = new ReceiptPdfExporter(); 
            exporter.export(response, curReceipt);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    // @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "/receipt/share/mail/{id}/{mailId}")
    public ResponseEntity<String> sendEmail(@PathVariable("id") Long id, @PathVariable("mailId") String mailId) throws AddressException, MessagingException, IOException {
        
        try{
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            RECEIPT curReceipt = service.getById(id);
            ReceiptPdfExporter exporter = new ReceiptPdfExporter(); 
            exporter.export(outputStream, curReceipt);
            byte[] bytes = outputStream.toByteArray();

            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormat.format(new Date());
            String name = curReceipt.getName()+"_"+currentDateTime+".pdf";
            pdfBodyPart.setFileName(name);

            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("RonWonBot@gmail.com");
            mailSender.setPassword("Hello@12345");
            
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.starttls.enable", "true");
            mailSender.setJavaMailProperties(properties);
            String from = "RonWonBot@gmail.com";
            String to = mailId;
                
            MimeMessage message = mailSender.createMimeMessage();
            message.setSender(new InternetAddress(from));
            message.setSubject("Here's your receipt");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(pdfBodyPart);
            message.setContent(multipart);

            mailSender.send(message);
            return new ResponseEntity<String>("Email Sent",HttpStatus.OK);
        } catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>("not sent",HttpStatus.NOT_FOUND);
        } 
    }
}
