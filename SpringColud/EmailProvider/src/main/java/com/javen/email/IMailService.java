package com.javen.email;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

public interface IMailService {
    /**
     * 发送文本邮件
     *
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String from,String to, String subject, String content);

    public void sendSimpleMail(String from,String to, String subject, String content, String... cc);

    /**
     * 发送HTML邮件
     *
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    public void sendHtmlMail(String from,String to, String subject, String content) throws MessagingException, javax.mail.MessagingException;

    public void sendHtmlMail(String from,String to, String subject, String content, String... cc);

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @throws MessagingException
     */
    public void sendAttachmentsMail(String from,String to, String subject, String content, String filePath) throws MessagingException;

    public void sendAttachmentsMail(String from,String to, String subject, String content, String filePath, String... cc);

    /**
     * 发送正文中有静态资源的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     * @throws MessagingException
     */
    public void sendResourceMail(String from,String to, String subject, String content, String rscPath, String rscId) throws MessagingException, javax.mail.MessagingException;

    public void sendResourceMail(String from,String to, String subject, String content, String rscPath, String rscId, String... cc);

} 