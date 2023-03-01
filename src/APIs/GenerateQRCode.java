package APIs;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import Entities.Coupon;
import Services.CouponService;
import Services.UtilisateurService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class GenerateQRCode {
    public void generate(String code) throws IOException {
        String text = code;
        String filePath = "C:\\Users\\admin\\Desktop\\Treydi-Desktop\\src\\APIs\\img\\MyQR.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        Map<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);


        BitMatrix bitMatrix = null;
        try {
            bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500, hints);
        } catch (WriterException e) {
            System.err.println("Error generating QR code: " + e.getMessage());
            return;
        }


        Path path = FileSystems.getDefault().getPath(filePath);
        try {
            MatrixToImageWriter.writeToFile(bitMatrix, "PNG", path.toFile());
        } catch (IOException e) {
            System.err.println("Error writing QR code to file: " + e.getMessage());
        }

        System.out.println("QR code generated successfully.");
    }



    public static void main(String[] args) throws WriterException, IOException {

    }

}