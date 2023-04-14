package mx.edu.utez.firstapp.utils;

import mx.edu.utez.firstapp.controllers.Contact.Dtos.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.nio.file.FileSystem;

@Component
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public boolean sendMail(EmailDto email){
        try {
            MimeMessage message = mailSender.createMimeMessage(); //tipo de contenido de respuesta, dentro existe el JSON (extensiones)
            MimeMessageHelper helper = new MimeMessageHelper(message, true); //activar adjuntos
            helper.setTo("raulwilliams13@gmail.com");
            helper.setSubject("Market | nuevo mesaje");
            helper.setText(template(email), true);
            mailSender.send(message);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public String template (EmailDto email){
        return "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta content=\"width=device-width, initial-scale=1\" name=\"viewport\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta content=\"telephone=no\" name=\"format-detection\">\n" +
                "    <title></title>\n" +
                "    <!--[if (mso 16)]>\n" +
                "    <style type=\"text/css\">\n" +
                "    a {text-decoration: none;}\n" +
                "    </style>\n" +
                "    <![endif]-->\n" +
                "    <!--[if gte mso 9]><style>sup { font-size: 100% !important; }</style><![endif]-->\n" +
                "    <!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "    <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG></o:AllowPNG>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "    </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "    <!--[if !mso]><!-- -->\n" +
                "    <link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\">\n" +
                "    <!--<![endif]-->\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "    <div class=\"es-wrapper-color\">\n" +
                "        <!--[if gte mso 9]>\n" +
                "\t\t\t<v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\">\n" +
                "\t\t\t\t<v:fill type=\"tile\" src=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" color=\"#F9F4FF\" origin=\"0.5, 0\" position=\"0.5, 0\"></v:fill>\n" +
                "\t\t\t</v:background>\n" +
                "\t\t<![endif]-->\n" +
                "        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" background=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/rectangle_171_3.png\" style=\"background-position: center top;\">\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"esd-email-paddings\" valign=\"top\">\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"esd-header-popover es-header\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table bgcolor=\"#ffffff\" class=\"es-header-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p40b es-p20r es-p20l\" align=\"left\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"560\" align=\"left\" class=\"esd-container-frame\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-image\" style=\"font-size: 0px;\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img src=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/group.png\" alt style=\"display: block;\" width=\"40\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p30t es-p30b es-p40r es-p40l\" align=\"left\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-image\" style=\"font-size: 0px;\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img src=\"https://mngxea.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/25469811_developer_male_ICK.png\" alt style=\"display: block;\" width=\"280\" class=\"adapt-img\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-text es-m-txt-c es-p20t es-p20b\">\n" +
                "                                                                                        <h1 style=\"line-height: 150%;\"><strong>Time</strong><strong>&nbsp;Saving Software</strong></h1>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"border-width: 2px; border-style: solid; border-color: #4ca2f8; border-radius: 20px; border-collapse: separate; background-image: url(https://mngxea.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png); background-repeat: no-repeat; background-position: left center;\" background=\"https://mngxea.stripocdn.email/content/guids/CABINET_9aa36f49cdb5185ad35ee0f7a5c7d9380ade3ae69ada3493ecaa145d1284bee9/images/group_347_1.png\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr class=\"es-visible-simple-html-only\">\n" +
                "                                                                                    <td align=\"left\" class=\"esd-block-text es-p40 es-m-p20r es-m-p20l\">\n" +
                "                                                                                        <p>Hi <strong>"+email.getFullname()+"</strong>,<br><br>"+email.getComments()+"</p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p40r es-p40l\" align=\"left\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-button es-p20b\">\n" +
                "                                                                                        <!--[if mso]><a href=\"https://viewstripo.email\" target=\"_blank\" hidden>\n" +
                "\t<v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"https://viewstripo.email\" \n" +
                "                style=\"height:51px; v-text-anchor:middle; width:520px\" arcsize=\"50%\" stroke=\"f\"  fillcolor=\"#4ca2f8\">\n" +
                "\t\t<w:anchorlock></w:anchorlock>\n" +
                "\t\t<center style='color:#ffffff; font-family:Poppins, sans-serif; font-size:18px; font-weight:400; line-height:18px;  mso-text-raise:1px'>Personalized Demo</center>\n" +
                "\t</v:roundrect></a>\n" +
                "<![endif]-->\n" +
                "                                                                                        <!--[if !mso]><!-- --><span class=\"msohide es-button-border\" style=\"display: block;\"><a href=\"https://viewstripo.email\" class=\"es-button msohide es-button-1668505687743\" target=\"_blank\" style=\"padding: 15px 5px; display: block;\">Personalized Demo</a></span>\n" +
                "                                                                                        <!--<![endif]-->\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table bgcolor=\"#ffffff\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p40r es-p40l\" align=\"left\" background=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png\" style=\"background-image: url(https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/20347363_v1072014converted_1_GkL.png); background-repeat: no-repeat; background-position: right top;\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"520\" class=\"esd-container-frame\" align=\"center\" valign=\"top\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"left\" class=\"esd-block-image es-p40l es-m-p0l\" style=\"font-size: 0px;\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img src=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/group_345.png\" alt=\"Matthew Nichols\" style=\"display: block;\" width=\"100\" title=\"Matthew Nichols\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"left\" class=\"esd-block-text es-p20t es-p40r es-p40l es-m-p0r es-m-p0l\">\n" +
                "                                                                                        <p>Thanks,<br><strong>Matthew Nichols</strong></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p40b es-p40r es-p20l es-m-p40l\" align=\"left\">\n" +
                "                                                        <!--[if mso]><table width=\"540\" cellpadding=\"0\" cellspacing=\"0\"><tr><td width=\"46\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-left\" align=\"left\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr class=\"es-mobile-hidden\">\n" +
                "                                                                    <td width=\"46\" class=\"es-m-p20b esd-container-frame\" align=\"left\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-spacer\" height=\"40\"></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td><td width=\"10\"></td><td width=\"484\" valign=\"top\"><![endif]-->\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\" align=\"right\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"484\" align=\"left\" class=\"esd-container-frame\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-menu\" esd-tmp-menu-size=\"width|20\" esd-tmp-menu-padding=\"10|5\">\n" +
                "                                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr class=\"links-images-left\">\n" +
                "                                                                                                    <td align=\"left\" valign=\"top\" width=\"100%\" class=\"es-p10t es-p10b es-p5r\" style=\"padding-bottom: 5px;\"><a target=\"_blank\" href=\"tel:+(000)123456789\"><img src=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/envelope_1.png\" alt=\"+ (000) 123 456 789\" title=\"+ (000) 123 456 789\" align=\"absmiddle\" class=\"es-p15r\" width=\"20\">+ (000) 123 456 789</a></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td class=\"esd-block-menu\" esd-tmp-menu-size=\"width|20\" esd-tmp-menu-padding=\"0|5\">\n" +
                "                                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"es-menu\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr class=\"links-images-left\">\n" +
                "                                                                                                    <td align=\"left\" valign=\"top\" width=\"100%\" class=\"es-p10t es-p10b es-p5r\" style=\"padding-bottom: 5px; padding-top: 0px;\"><a target=\"_blank\" href=\"https://\"><img src=\"https://mngxea.stripocdn.email/content/guids/CABINET_b5bfed0b11252243ebfb1c00df0e3977/images/envelope.png\" alt=\"nicholos@email.com\" title=\"nicholos@email.com\" align=\"absmiddle\" class=\"es-p15r\" width=\"20\">nicholos@email.com</a></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                        <!--[if mso]></td></tr></table><![endif]-->\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p20b es-p20r es-p20l\" align=\"left\" bgcolor=\"#77c82a\" style=\"background-color: #77c82a;\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"560\" align=\"left\" class=\"esd-container-frame\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-social\" style=\"font-size:0\">\n" +
                "                                                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-table-not-adapt es-social\">\n" +
                "                                                                                            <tbody>\n" +
                "                                                                                                <tr>\n" +
                "                                                                                                    <td align=\"center\" valign=\"top\" class=\"es-p40r\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img title=\"Facebook\" src=\"https://mngxea.stripocdn.email/content/assets/img/social-icons/circle-white/facebook-circle-white.png\" alt=\"Fb\" height=\"24\"></a></td>\n" +
                "                                                                                                    <td align=\"center\" valign=\"top\" class=\"es-p40r\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img title=\"Twitter\" src=\"https://mngxea.stripocdn.email/content/assets/img/social-icons/circle-white/twitter-circle-white.png\" alt=\"Tw\" height=\"24\"></a></td>\n" +
                "                                                                                                    <td align=\"center\" valign=\"top\" class=\"es-p40r\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img title=\"Instagram\" src=\"https://mngxea.stripocdn.email/content/assets/img/social-icons/circle-white/instagram-circle-white.png\" alt=\"Inst\" height=\"24\"></a></td>\n" +
                "                                                                                                    <td align=\"center\" valign=\"top\"><a target=\"_blank\" href=\"https://viewstripo.email\"><img title=\"Youtube\" src=\"https://mngxea.stripocdn.email/content/assets/img/social-icons/circle-white/youtube-circle-white.png\" alt=\"Yt\" height=\"24\"></a></td>\n" +
                "                                                                                                </tr>\n" +
                "                                                                                            </tbody>\n" +
                "                                                                                        </table>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"background-color: transparent;\" bgcolor=\"rgba(0, 0, 0, 0)\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p20b es-p40r es-p40l\" align=\"left\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"520\" class=\"esd-container-frame\" align=\"left\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-text es-m-txt-c\" esd-links-underline=\"underline\">\n" +
                "                                                                                        <p>6305 Stony Bank, Pelican City, Montana, 59436,US <br></p>\n" +
                "                                                                                        <p><a target=\"_blank\" style=\"text-decoration: underline;\">Unsubscribe</a></p>\n" +
                "                                                                                    </td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer esd-footer-popover\" align=\"center\">\n" +
                "                            <tbody>\n" +
                "                                <tr>\n" +
                "                                    <td class=\"esd-stripe\" align=\"center\">\n" +
                "                                        <table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                "                                            <tbody>\n" +
                "                                                <tr>\n" +
                "                                                    <td class=\"esd-structure es-p20t es-p20b es-p40r es-p40l\" align=\"left\">\n" +
                "                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                            <tbody>\n" +
                "                                                                <tr>\n" +
                "                                                                    <td width=\"520\" class=\"esd-container-frame\" align=\"left\">\n" +
                "                                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
                "                                                                            <tbody>\n" +
                "                                                                                <tr>\n" +
                "                                                                                    <td align=\"center\" class=\"esd-block-image es-infoblock made_with\" style=\"font-size:0\"><a target=\"_blank\" href=\"https://viewstripo.email/?utm_source=templates&utm_medium=email&utm_campaign=customer_service_1&utm_content=time_saving_software\"><img src=\"https://mngxea.stripocdn.email/content/guids/CABINET_09023af45624943febfa123c229a060b/images/7911561025989373.png\" alt width=\"125\" style=\"display: block;\"></a></td>\n" +
                "                                                                                </tr>\n" +
                "                                                                            </tbody>\n" +
                "                                                                        </table>\n" +
                "                                                                    </td>\n" +
                "                                                                </tr>\n" +
                "                                                            </tbody>\n" +
                "                                                        </table>\n" +
                "                                                    </td>\n" +
                "                                                </tr>\n" +
                "                                            </tbody>\n" +
                "                                        </table>\n" +
                "                                    </td>\n" +
                "                                </tr>\n" +
                "                            </tbody>\n" +
                "                        </table>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

}
