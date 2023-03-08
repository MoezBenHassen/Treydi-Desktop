package APIs;

import java.util.Random;

public class CaptchaGenerator {
    public static Captcha getCaptcha(){
        String[] fonts=new String[]{"OCR A Extended","French Script MT","Freestyle Script","Script MT Bold","Lucida Handwriting Italic"};
        String[] colors=new String[]{"red","blue","green"};
        String[] bgColors=new String[]{"pink","aqua","yellow"};
        char data[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
                'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6',
                '7', '8', '9' };
        char index[] = new char[7];

        Random r = new Random();
        int i = 0;

        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
        }

        String number=new String(index);
        String font=fonts[r.nextInt(fonts.length)];
        String color=colors[r.nextInt(colors.length)];
        String bgColor=bgColors[r.nextInt(bgColors.length)];
        Captcha captcha=new Captcha(font,color,bgColor,number);
        return captcha;
    }
}