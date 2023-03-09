package APIs;

public class Captcha {
    private String font;
    private String color;
    private String bgColor;
    private String genenumber;

    public Captcha(String font, String color, String bgColor, String genenumber) {
        this.font = font;
        this.color = color;
        this.bgColor = bgColor;
        this.genenumber = genenumber;
    }

    public String getFont() {
        return font;
    }

    public String getColor() {
        return color;
    }

    public String getBgColor() {
        return bgColor;
    }

    public String getGenenumber() {
        return genenumber;
    }

    @Override
    public String toString() {
        return "Captcha{" +
                "font='" + font + '\'' +
                ", color='" + color + '\'' +
                ", bgColor='" + bgColor + '\'' +
                ", genenumber='" + genenumber + '\'' +
                '}';
    }
}